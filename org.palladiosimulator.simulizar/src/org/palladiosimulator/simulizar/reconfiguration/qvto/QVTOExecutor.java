package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

/**
 * QVTo executor helper class that supports executing QVTo reconfiguration rules.
 * 
 * @author Matthias Becker
 *
 */
public class QVTOExecutor {

    private final PRMAccess prmAccess;

    private static final String QVTO_FILE_EXTENSION = ".qvto";
    private static final Logger LOGGER = Logger.getLogger(QVTOExecutor.class);
    private final List<TransformationExecutor> qvtoRuleSet;
    private final Map<String, Resource> pcmModelMap;

    /**
     * Constructor of the QVTOExecutor
     * 
     * @param modelAccessFactory
     *            ModelAccessFactory giving access to PCM and PRM models
     * @param configuration
     *            Simulation configuration
     * @param blackboard
     *            MDSDBlackboard storing the PCM models
     */
    public QVTOExecutor(final IModelAccessFactory modelAccessFactory, SimuComWorkflowConfiguration configuration,
            MDSDBlackboard blackboard) {
        super();
        this.prmAccess = modelAccessFactory.getPRMModelAccess();
        this.qvtoRuleSet = new LinkedList<TransformationExecutor>();
        this.loadQvtoRules(configuration);
        this.pcmModelMap = getRequiredModels(blackboard);
    }

    /**
     * 
     * @param configuration
     *            Simulation configuration
     */
    void loadQvtoRules(SimuComWorkflowConfiguration configuration) {

        String path = (String) configuration.getAttributes().get(SimulizarConstants.RECONFIGURATION_RULES_FOLDER);

        if (!path.equals("")) {

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
                    return;
                }
            }

            if (!folder.exists()) {
                LOGGER.warn("Folder " + folder + " does not exist. No reconfiguration rules will be loaded.");
                return;
            }
            final File[] files = folder.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(final File dir, final String name) {
                    return name.endsWith(QVTO_FILE_EXTENSION);
                }
            });
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
     * Executes the QVTo rule given as a parameter
     * 
     * @param executor
     *            the QVTo rule TransformationExecutor
     * @return true if transformation was executed successfully
     */
    private boolean execute(TransformationExecutor executor) {

        // define the transformation input and outputs
        EList<PCMModelElementMeasurement> runtimeModel = this.prmAccess.getModel().getPcmModelElementMeasurements();
        EList<EObject> pcmAllocation = this.pcmModelMap.get("allocation").getContents();
        EList<EObject> pcmSystem = this.pcmModelMap.get("system").getContents();
        EList<EObject> pcmResources = this.pcmModelMap.get("resourcetype").getContents();
        EList<EObject> pcmRepository = this.pcmModelMap.get("repository").getContents();

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
            LOGGER.log(Level.INFO,
                    "Rule application successfull with message: " + result.getMessage());
            return true;
        } else {
            LOGGER.log(Level.WARN, "Rule application failed with message: " + result.getMessage());
            return false;
        }
    }

    /**
     * Get the required PCM models from the blackboard.
     * 
     * @param blackboard
     *            MDSDBlackboard where PCM models are stored
     * @return Map of PCM models with file extension as key.
     */
    private Map<String, Resource> getRequiredModels(MDSDBlackboard blackboard) {

        Map<String, Resource> modelsMap = new HashMap<String, Resource>();

        // find the models in the blackboard
        String pcmModelPartitionId = LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID;
        ResourceSetPartition partition = blackboard.getPartition(pcmModelPartitionId);
        partition.resolveAllProxies();
        for (Resource r : partition.getResourceSet().getResources()) {
            URI modelURI = r.getURI();
            String fileExtension = modelURI.fileExtension();

            if (!modelsMap.containsKey(fileExtension)) {
                modelsMap.put(fileExtension, r);
            }
        }

        return modelsMap;
    }

}
