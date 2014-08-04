package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

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
        this.loadQvtoRules(configuration);
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
        // iterate over all rules and execute them
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
    private void loadQvtoRules(final SimuLizarWorkflowConfiguration configuration) {
        final String path = configuration.getReconfigurationRulesFolder();

        if (!path.equals("")) {

            final File folder = getFolder(path);
            final File[] files = getQVToFiles(folder);
            loadQVTRules(files);
        }
    }

    /**
     * @param files
     */
    private void loadQVTRules(final File[] files) {
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
     * @param folder
     * @return
     */
    private File[] getQVToFiles(File folder) {
        if (folder == null || !folder.exists()) {
            LOGGER.warn("Folder " + folder + " does not exist. No reconfiguration rules will be loaded.");
            return new File[0];
        }
        final File[] files = folder.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith(QVTO_FILE_EXTENSION);
            }
        });
        return files;
    }

    /**
     * @param path
     * @return
     */
    private File getFolder(String path) {
        // add file protocol only if necessary
        String filePath = path;
        File folder = null;
        if (!path.startsWith("platform:")) {
            filePath = "file:///" + filePath;

            URI pathToQvtoRules = URI.createURI(filePath);
            folder = new File(pathToQvtoRules.toFileString());
        } else {
            try {
                URL pathURL = FileLocator.resolve(new URL(path));
                String folderString = pathURL.toExternalForm().replace("file:", "");
                folder = new File(folderString);
            } catch (IOException e) {
                LOGGER.warn("No QVTo rules found, QVTo reconfigurations disabled.", e);
            }
        }
        return folder;
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
        List<PCMModelElementMeasurement> runtimeModel = this.modelAccess.getPRMModel().getPcmModelElementMeasurements();
        List<EObject> pcmAllocation = Arrays.asList((EObject) this.modelAccess.getGlobalPCMModel().getAllocation());
        List<EObject> pcmSystem = Arrays.asList((EObject) this.modelAccess.getGlobalPCMModel().getSystem());
        List<EObject> pcmResources = Arrays.asList((EObject) this.modelAccess.getGlobalPCMModel()
                .getResourceTypeRepository());
        List<EObject> pcmRepository = new ArrayList<EObject>();
        pcmRepository.addAll(this.modelAccess.getGlobalPCMModel().getRepositories());

        // create the input and inout extents with its initial contents
        ModelExtent input = new BasicModelExtent(runtimeModel);
        ModelExtent inoutRepository = new BasicModelExtent(pcmRepository);
        ModelExtent inoutSystem = new BasicModelExtent(pcmSystem);
        ModelExtent inoutAllocation = new BasicModelExtent(pcmAllocation);
        ModelExtent inoutResources = new BasicModelExtent(pcmResources);

        // setup the execution environment details ->
        // configuration properties, LOGGER, monitor object etc.
        ExecutionContextImpl exContext = new ExecutionContextImpl();
        // context.setConfigProperty("keepModeling", true);
        exContext.setLog(new QVTOReconfigurationLogger(QVTOExecutor.class));

        // run the transformation assigned to the executor with the given
        // input and output and execution context
        ExecutionDiagnostic result = executor.execute(exContext, input, inoutRepository, inoutSystem, inoutAllocation,
                inoutResources);

        // check the result for success
        if (result.getSeverity() == Diagnostic.OK) {
            LOGGER.log(Level.INFO, "Rule application successfull with message: " + result.getMessage());
            return true;
        } else {
            LOGGER.log(Level.WARN, "Rule application failed with message: " + result.getMessage());
            return false;
        }
    }
}
