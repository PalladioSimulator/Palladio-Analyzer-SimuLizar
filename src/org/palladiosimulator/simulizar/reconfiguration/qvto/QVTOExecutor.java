package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.prm.PRMMeasurement;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.utils.FileUtil;

/**
 * QVTo executor helper class that supports executing QVTo reconfiguration rules.
 * 
 * @author Matthias Becker, Sebastian Lehrig
 */
public class QVTOExecutor {

    private final IModelAccess modelAccess;

    private static final String QVTO_FILE_EXTENSION = ".qvto";
    private static final Logger LOGGER = Logger.getLogger(QVTOExecutor.class);
    private final List<TransformationExecutor> qvtoRuleSet;

    /**
     * Constructor of the QVTOExecutor
     * 
     * @param modelAccess
     *            ModelAccessFactory giving access to PCM and PRM models
     * @param configuration
     *            Simulation configuration
     */
    public QVTOExecutor(final IModelAccess modelAccess, final SimuLizarWorkflowConfiguration configuration) {
        super();
        this.modelAccess = modelAccess;
        this.qvtoRuleSet = new LinkedList<TransformationExecutor>();
        this.loadQvtFiles(configuration);
    }

    /**
     * Executes all QVTo rules found in the configured reconfiguration rule folder.
     * 
     * @param monitoredElement
     *            the monitored PCM model element.
     * @return true if at least one reconfiguration was executed successfully
     */
    public boolean executeRules(final EObject monitoredElement) {
        boolean result = false;
        for (final TransformationExecutor rule : this.qvtoRuleSet) {
            result |= execute(rule);
        }
        return result;
    }

    /**
     * 
     * @param configuration
     *            Simulation configuration
     */
    private void loadQvtFiles(final SimuLizarWorkflowConfiguration configuration) {
        final String path = configuration.getReconfigurationRulesFolder();

        if (!path.equals("")) {

            final File folder = FileUtil.getFolder(path);
            final File[] files = FileUtil.getFiles(folder, QVTO_FILE_EXTENSION);
            createTransformationExecutors(files);
        }
    }

    /**
     * Load the QVTo reconfiguration rules from the files
     * 
     * FIXME: Remove this and load reconfiguration rules into a blackboard.
     * 
     * @param files
     *            that contain the QVTo reconfiguration rules
     */
    private void createTransformationExecutors(final File[] files) {
        if (files != null && files.length > 0) {
            for (final File file : files) {
                LOGGER.info("Found reconfiguration rule \"" + file.getPath() + "\"");
                URI transformationURI = URI.createFileURI(file.getPath());
                TransformationExecutor transformationExecutor = new TransformationExecutor(transformationURI);
                this.qvtoRuleSet.add(transformationExecutor);
            }
        } else {
            LOGGER.warn("No QVTo rules found, QVTo reconfigurations disabled.");
        }
    }

    /**
     * Executes the QVTo rule given as a parameter
     * 
     * @param executor
     *            the QVTo rule TransformationExecutor
     * @return true if transformation was executed successfully
     */
    private boolean execute(final TransformationExecutor executor) {

        // define the transformation input and outputs
        List<PRMMeasurement> runtimeModel = this.modelAccess.getPRMModel().getMeasurements();
        List<EObject> pcmAllocation = Arrays.asList((EObject) this.modelAccess.getGlobalPCMModel().getAllocation());

        // create the input and inout extents with its initial contents
        ModelExtent inRuntimeModel = new BasicModelExtent(runtimeModel);
        ModelExtent inoutAllocation = new BasicModelExtent(pcmAllocation);

        // setup the execution environment details ->
        // configuration properties, LOGGER, monitor object etc.
        ExecutionContextImpl exContext = new ExecutionContextImpl();
        // context.setConfigProperty("keepModeling", true);
        exContext.setLog(new QVTOReconfigurationLogger(QVTOExecutor.class));

        // run the transformation assigned to the executor with the given
        // input and output and execution context
        ExecutionDiagnostic result = executor.execute(exContext, inRuntimeModel, inoutAllocation);

        // check the result for success
        if (result.getSeverity() == Diagnostic.OK) {
            LOGGER.log(Level.DEBUG, "Rule successfully executed with message: " + result.getMessage());
            return true;
        } else {
            LOGGER.log(Level.WARN, "Rule application failed with message: " + result.getMessage());
            return false;
        }
    }
}
