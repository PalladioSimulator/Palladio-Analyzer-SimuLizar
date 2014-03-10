package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import org.palladiosimulator.simulizar.access.GlobalPCMAccess;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ModelLocation;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

public class QVTOExecutor {

    private final GlobalPCMAccess globalPCMAccess;
    private final PRMAccess prmAccess;
	
	private static final String QVTO_FILE_EXTENSION = ".qvto";
	private static final Logger logger = Logger.getLogger(QVTOExecutor.class);
	private List<TransformationExecutor> qvtoRuleSet;
	private MDSDBlackboard blackboard;

	
	public QVTOExecutor(final IModelAccessFactory modelAccessFactory, SimuComWorkflowConfiguration configuration, MDSDBlackboard blackboard) {
	     super();
	     this.globalPCMAccess = modelAccessFactory.getGlobalPCMAccess();
	     this.prmAccess = modelAccessFactory.getPRMModelAccess();
	     this.qvtoRuleSet = new LinkedList<TransformationExecutor>();
	     this.loadQvtoRules(configuration);
	     this.blackboard = blackboard;
	}
	
	void loadQvtoRules(SimuComWorkflowConfiguration configuration) {
		
		String path = (String) configuration.getAttributes().get(SimulizarConstants.RECONFIGURATION_RULES_FOLDER);
		
	       if (!path.equals("")) {
	           
	        	//add file protocol only if necessary
	        	String filePath = path;
	        	File folder = null;
	        	if (!path.startsWith("platform:")) {
	        		filePath = "file:///" + filePath;
	        		
	        		URI pathToQvtoRules = URI.createURI(filePath);
	                folder = new File(pathToQvtoRules.toFileString());
	        	}
	        	else {
	        	    try {
	                    URL pathURL = FileLocator.resolve(new URL(path));
	                    String folderString = pathURL.toExternalForm().replace("file:", "");
	                    folder = new File(folderString);
	                } catch (IOException e) {
	                    logger.warn ("No QVTo rules found, QVTo reconfigurations disabled.", e);
	                    return;
	                }
	        	}
	            
	        	if (!folder.exists()) {
	        	    logger.warn("Folder "+folder+" does not exist. No reconfiguration rules will be loaded.");
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
	                	logger.info("Found reconfiguration rule" + file.getPath());
	                	URI transformationURI = URI.createFileURI(file.getPath());
	                	TransformationExecutor transformationExecutor = new TransformationExecutor(transformationURI);
	                    this.qvtoRuleSet.add(transformationExecutor);
	                }
	            } else {
	                logger.warn ("No QVTo rules found, QVTo reconfigurations disabled.");
	            }
	        }
	}
	
	
	/**
	 * Executes all QVTo rules for the given monitored element.
	 * 
	 * @param monitoredElement
	 *         the pcm model element to be monitored.
	 * @return true if at least one reconfiguration's check was positive and it
	 *         reconfigured the model.
	 */
	public boolean executeRules(final EObject monitoredElement) {

		boolean result = false;
		// iterate over all rules and execute them
		for(final TransformationExecutor rule : this.qvtoRuleSet){
			result |= execute(rule, monitoredElement);
		}
		
		return result;
	}

	private boolean execute(TransformationExecutor executor, final EObject monitoredElement) {
		
		// define the transformation input and outputs
		EList<PCMModelElementMeasurement> runtimeModel = this.prmAccess.getModel().getPcmModelElementMeasurements();
		EList<EObject> pcmAllocation = getRequiredModels("allocation").getContents();
		EList<EObject> pcmSystem = getRequiredModels("system").getContents();
		EList<EObject> pcmResources = getRequiredModels("resourcetype").getContents();
		EList<EObject> pcmRepository = getRequiredModels("repository").getContents();
		
		// create the input and inout extents with its initial contents
		ModelExtent input = new BasicModelExtent(runtimeModel);
		ModelExtent inoutRepository = new BasicModelExtent(pcmRepository);
		ModelExtent inoutSystem = new BasicModelExtent(pcmSystem);
		ModelExtent inoutAllocation = new BasicModelExtent(pcmAllocation);
		ModelExtent inoutResources = new BasicModelExtent(pcmResources);
		
		// setup the execution environment details ->
		// configuration properties, logger, monitor object etc.
		ExecutionContextImpl context = new ExecutionContextImpl();
		//context.setConfigProperty("keepModeling", true);
		context.setLog(new QVTOReconfigurationLogger(QVTOExecutor.class));

		// run the transformation assigned to the executor with the given
		// input and output and execution context
		ExecutionDiagnostic result = executor.execute(context, input, inoutRepository, inoutSystem, inoutAllocation, inoutResources);
		
		// check the result for success
		if (result.getSeverity() == Diagnostic.OK) {
			logger.log(Level.INFO, "Rule application successfull with message: " + result.getMessage() );			
			return true;
		} else {
			logger.log(Level.WARN, "Rule application failed with message: " + result.getMessage());
			return false;
		}
	}
	
	  /**
     * Build the location objects out of the blackboards PCM model partition.
     * 
     * @param blackboard The blackboard to work with.
     * @return The prepared model locations for the PCM models.
     */
    private Resource getRequiredModels(String model) {
    	       
        // find the models in the blackboard
        String pcmModelPartitionId = LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID;
        ResourceSetPartition partition = this.blackboard.getPartition(pcmModelPartitionId);
        partition.resolveAllProxies();
        for (Resource r : partition.getResourceSet().getResources()) {
            URI modelURI = r.getURI();
            String fileExtension = modelURI.fileExtension();
            
            if(fileExtension.equals(model)){
            	return r;
            }
        }
        
        return null;
    }

}
