package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.utils.PCMModels;

import de.mdelab.sdm.interpreter.core.variables.Variable;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.pcm.allocation.AllocationPackage;
import de.uka.ipd.sdq.pcm.repository.RepositoryPackage;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage;
import de.uka.ipd.sdq.pcm.system.SystemPackage;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ModelLocation;

public class QVTOExecutor {

    private final GlobalPCMAccess globalPCMAccess;
    private final PRMAccess prmAccess;
	
	private static final String QVTO_FILE_EXTENSION = ".qvto";
	private static final Logger logger = Logger.getLogger(QVTOExecutor.class);
	private List<TransformationExecutor> qvtoRuleSet;

	
	public QVTOExecutor(final IModelAccessFactory modelAccessFactory, SimuComWorkflowConfiguration configuration) {
	     super();
	     this.globalPCMAccess = modelAccessFactory.getGlobalPCMAccess();
	     this.prmAccess = modelAccessFactory.getPRMModelAccess();
	     this.qvtoRuleSet = new LinkedList<TransformationExecutor>();
	     this.loadQvtoRules(configuration);
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
	 * Executes all activities for the given monitored element.
	 * 
	 * @param monitoredElement
	 *            the pcm model element to be monitored.
	 * @return true if at least one reconfiguration's check was positive and it
	 *         reconfigured the model.
	 */
	public boolean executeRules(final EObject monitoredElement) {

		boolean result = false;
		// iterate over all rules and execute them
		for(final TransformationExecutor rule : this.qvtoRuleSet){
			result |= execute(rule);
		}
		
		return result;
	}

	private boolean execute(TransformationExecutor executor) {

		// define the transformation input and outputs
		EList<EObject> runtimeModel = this.prmAccess.getModel().eContents();
		EList<EObject> pcmAllocation = this.globalPCMAccess.getModel().getAllocation().eContents();
		EList<EObject> pcmSystem = this.globalPCMAccess.getModel().getSystem().eContents();
		EList<EObject> pcmResources = this.globalPCMAccess.getModel().getResourceEnvironment().eContents();
		EList<EObject> pcmRepository = this.globalPCMAccess.getModel().getRepository().eContents();
		
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

}
