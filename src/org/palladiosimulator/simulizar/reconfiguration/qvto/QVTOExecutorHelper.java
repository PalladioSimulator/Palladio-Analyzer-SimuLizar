package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;

/**
 * A utility class that enables to execute existing transformation in the
 * specified execution context.
 * 
 */
public class QVTOExecutorHelper {
	
	/** The URI of the transformation */
	private final URI transURI;
	
	/** The loaded resources */
	private final Map<URI,Resource> resources = new HashMap<URI, Resource>();
	
	/** The URI of the model parameters */
	private final List<URI> modelURIs = new ArrayList<URI>();
	
	/** The input models */
	private final List<ModelExtent> modelParameters = new ArrayList<ModelExtent>();
	
	/** The URIs of the resources to save */
	private final Set<URI> resourcesToSave = new HashSet<URI>();
	
	/** The config properties */
	private final Map<String,Object> configProperties = new HashMap<String,Object>();
	
	/** Additional metamodels which may be needed by the transformation */
	private final EPackage.Registry additionalMetamodels = EPackage.Registry.INSTANCE;
	
	/** A flag to indicate if models should be autosaved after the transformation */
	private final boolean autoSave;
	
	/**
	 * Default constructor.
	 * @param transURI The transformation URI.
	 * @param autoSave true if the models should be autosaved after the transformation.
	 */
	public QVTOExecutorHelper(URI transURI, boolean autoSave) {
		this.transURI = transURI;
		this.autoSave = autoSave;
	}
	
	/**
	 * Sets the model parameters required by the transformation.
	 * @param URIs
	 */
	public void setModelParameters(URI... URIs) {
		// Create a new fresh resource set
		ResourceSet rs = new ResourceSetImpl();
		
		modelURIs.clear();
		resources.clear();
		modelParameters.clear();
		for (URI uri : URIs) {
			// Load or create resource according to its existence
			boolean exists = uri.isFile() ? checkCreate(uri) : true;
			Resource res;
			if (exists) res = rs.getResource(uri, true);
			else res = rs.createResource(uri);
			
			// Add the resource and the URI
			modelURIs.add(uri);
			resources.put(uri, res);
			
			// Add the model extent
			ModelExtent me = new BasicModelExtent();
			me.setContents(res.getContents());
			modelParameters.add(me);
		}
	}
	
	/**
	 * Sets the list of models to save after the transformation has been executed.
	 * @param URIs the URIs of the resources to save.
	 */
	public void setResourcesToSave(URI... URIs) {
		resourcesToSave.clear();
		for (URI uri : URIs) {
			resourcesToSave.add(uri);
		}
	}
	
	/**
	 * Checks if the resource pointed by an URI does not exists, and hence
	 * must be created from scratch.
	 * @param uri the URI of the resource to check.
	 * @return true if it must be created, false otherwise.
	 */
	private boolean checkCreate(URI uri) { 
		IPath location = new Path(uri.toFileString());
		return location.toFile().exists();
	}
	
	/**
	 * Sets the configuration properties for the transformation.
	 * @param configProperties the configuration properties
	 */
	public void setConfigProperties(Map<String,Object> configProperties) {
		this.configProperties.clear();
		this.configProperties.putAll(configProperties);
	}
	
	/**
	 * Adds a specific metamodel for the execution of the transformation.
	 * @param mmURI the URI of the metamodel.
	 * @param ePackage the metamodel package.
	 */
	public void addSpecificMetamodel(String mmURI, EPackage ePackage) {
		additionalMetamodels.put(mmURI, ePackage);
	}

	/**
	 * Executes the transformation.
	 * @throws CoreException if the transformation failed.
	 */
	public void doExecute() throws CoreException {
		// create executor for the given transformation
		TransformationExecutor executor = new TransformationExecutor(transURI, additionalMetamodels);

		// setup the execution environment details
		ExecutionContextImpl context = new ExecutionContextImpl();
		context.setConfigProperty("keepModeling", true);
		for (String key : configProperties.keySet()) {
			context.setConfigProperty(key, configProperties.get(key));
		}

		// Execute
		ExecutionDiagnostic result = executor.execute(context, modelParameters.toArray(new ModelExtent[0]));

		// check the result for success
		if(result.getSeverity() != Diagnostic.OK) {
			IStatus status = BasicDiagnostic.toIStatus(result);
			throw new CoreException(status);
		} else if (autoSave) saveModels(); 
	}
	
	/**
	 * Saves the models whose saving is required.
	 * @throws CoreException if the operation failed.
	 */
	private void saveModels() throws CoreException {
		Map<Object,Object> options = new HashMap<Object,Object>();
		options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, null);
		
		for (int i = 0; i < resources.size(); i++) {
			ModelExtent me = modelParameters.get(i);
			URI uri = modelURIs.get(i);
			if (!resourcesToSave.contains(uri)) continue;
			
			Resource res = resources.get(uri);
			res.getContents().addAll(me.getContents());
			
			try {
				res.save(options);
			} catch (IOException e) {
				IStatus status = new Status(IStatus.ERROR, "QVTOExecutor", null, e);
				throw new CoreException(status);
			}
		}
	}
}