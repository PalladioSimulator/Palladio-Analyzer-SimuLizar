package de.mdelab.eurema.interpreter.models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.Metamodel;

/**
 * Repository maintaining resources of EUREMA models and runtime models used
 * within the EUREMA model.
 * 
 * <p>
 * The term 'resource' refers to a materialized form of a model in the sense of
 * an <tt>XMI</tt> file or the loaded <tt>EObject</tt>s while 'model' refers to
 * a more general notion including the concept of a model.
 * </p>
 * 
 * @author thomas vogel
 * @version 0.03
 * 
 */
public class ModelRepository {

	/**
	 * The model repository singleton.
	 */
	public final static ModelRepository INSTANCE = new ModelRepository();

	/**
	 * Resource set maintaining the <tt>EMF</tt> resources of the models.
	 */
	private ResourceSet resourceSet;

	/**
	 * The {@code eurema.ModelResourceSet} as defined by the EUREMA metamodel
	 * containing a list of {@code eurema.ModelResource}s, each describing the
	 * name and URI of the corresponding EMF model.
	 */
	private eurema.ModelResourceSet eModelResourceSet;

	public eurema.ModelResourceSet geteModResourceSet() {
		return eModelResourceSet;
	}

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(ModelRepository.class);

	/**
	 * Constructor.
	 */
	private ModelRepository() {

	}

	/**
	 * Initializes the model repository.
	 * 
	 * @param resourceSet
	 *            the <tt>EMF</tt> resource set.
	 * @param eModelResourceSet
	 *            initial set of {@code eurema.ModelResource}s.
	 */
	public void initialize(ResourceSet resourceSet, eurema.ModelResourceSet eModelResourceSet) {
		logger.debug("Initializing the EUREMA model repository...");
		if (resourceSet == null) {
			throw new EuremaInterpreterException(
					"Failure in initializing the EUREMA model repository. " + "The EMF resource set must not be null.");
		}
		if (eModelResourceSet == null) {
			throw new EuremaInterpreterException("Failure in initializing the EUREMA model repository. "
					+ "The eurema.ModelResourceSet part of the eurema.Architecture must not be null.");
		}
		this.resourceSet = resourceSet;
		this.eModelResourceSet = eModelResourceSet;
	}

	/**
	 * Loads a model based on its URI.
	 * 
	 * @param uri
	 *            URI of the model to be loaded
	 * @return the loaded model, or <code>null</code> if no model can be
	 *         retrieved based on the <code>uri</code>.
	 */
	public Model loadModel(String uri) {
		if (uri == null || uri.equals("")) {
			throw new ModelRepositoryException(
					"Paramter \"uri\" to load a model must not be null or the empty String.");
		}

		// loadOnDemand must be set to <code>false</code>
		URI modelURI = URI.createURI(uri);
		logger.debug("Load resource: " + modelURI.toString());
		Resource emfResource = this.resourceSet.getResource(modelURI, false);
		eurema.ModelResource eModelResource = this.retrieveModelResourceByURI(modelURI.toString());

		if (emfResource == null & eModelResource == null) {
			return null;
		} else if (emfResource != null & eModelResource != null) {
			return new Model(eModelResource, emfResource);
		} else {
			throw new ModelRepositoryException("Inconsistent model repository.\nModelResource: " + eModelResource
					+ "\nEMF Resource: " + emfResource);
		}
	}

	/**
	 * Loads a model based on its URI. If there is no model with the
	 * <code>uri</code>, a model with the specified <code>uri</code> and
	 * <code>name</code> is created.
	 * 
	 * @param uri
	 *            URI of the model to be loaded
	 * @param name
	 *            the name of the model to be created if necessary
	 * @return the loaded model with the <code>uri</code>, or a newly created
	 *         model if no model can be retrieved from the <code>uri</code>.
	 */
	public Model loadModelOnDemand(String uri, String name) {
		if (uri == null || uri.equals("") || name == null || name.equals("")) {
			throw new ModelRepositoryException("Paramter \"uri\" or \"name\" to load a model "
					+ "on demand must not be null or the empty String.");
		}
		// HACK: absolute file paths to resolve proxies!

		String filename = new File(uri).getAbsolutePath();
		URI modelURI = URI.createFileURI(filename);

		logger.debug("Load resource on demand: " + modelURI.toString());
		Resource emfResource = this.resourceSet.getResource(modelURI, true);
		eurema.ModelResource modelResource = this.retrieveModelResourceByURI(modelURI.toString());

		if (modelResource == null) {
			modelResource = this.createModelResource(modelURI.toString(), name);
			this.eModelResourceSet.getResources().add(modelResource);
		}

		return new Model(modelResource, emfResource);
	}

	/**
	 * Creates a new model and adds it to the repository.
	 * 
	 * @param uri
	 *            the URI of the model to be created
	 * @param name
	 *            the name of the model to be created
	 * @return the created model
	 */
	public Model createModel(String uri, String name) {
		if (uri == null || uri.equals("") || name == null || name.equals("")) {
			throw new ModelRepositoryException(
					"Paramter \"uri\" or \"name\" to create a " + "model must not be null or the empty String.");
		}
		URI u = URI.createURI(uri);
		logger.debug("Create resource with URI: " + u.toString());
		return this.createModel(u, name);
	}

	/**
	 * Creates a new model and adds it to the repository.
	 * 
	 * @param uri
	 *            the URI of the model to be created
	 * @param name
	 *            the name of the model to be created
	 * @return the created model
	 */
	private Model createModel(URI uri, String name) {
		if (uri == null || name == null || name.equals("")) {
			throw new ModelRepositoryException("Paramter \"uri\" must not be null or \"name\" must "
					+ "not be null or the empty String in order to create a model.");
		}
		if (this.containsModel(uri)) {
			throw new ModelRepositoryException("Error in creating a model. The URI " + uri + " is already used.");
		} else {
			Resource emfResource = this.resourceSet.createResource(uri);
			eurema.ModelResource modelResource = this.createModelResource(uri.toString(), name);
			this.eModelResourceSet.getResources().add(modelResource);
			return new Model(modelResource, emfResource);
		}
	}

	/**
	 * Creates an instance of {@code eurema.ModelResource}.
	 * 
	 * @param uri
	 *            the URI of the model resource
	 * @param name
	 *            the name of the model resource
	 * @return instance of {@code eurema.ModelResource}.
	 */
	private eurema.ModelResource createModelResource(String uri, String name) {
		if (uri == null || uri.equals("") || name == null || name.equals("")) {
			throw new ModelRepositoryException("Paramters \"uri\" or \"name\" to create a "
					+ "model resource must not be null or the empty String.");
		}
		eurema.ModelResource modelResource = Metamodel.INSTANCE.getEuremaFactory().createModelResource();
		modelResource.setURI(uri);
		modelResource.setName(name);
		return modelResource;
	}

	/**
	 * Retrieves the model resource from the repository based on its URI.
	 * 
	 * @param uri
	 *            the URI of the model resource
	 * @return the model resource if it found by its URI, otherwise
	 *         <code>null</code>.
	 */
	private eurema.ModelResource retrieveModelResourceByURI(String uri) {
		if (uri == null || uri.equals("")) {
			throw new ModelRepositoryException(
					"Paramters \"uri\" to retrieve a model " + "resource must not be null or the empty String.");
		}
		eurema.ModelResource modelResource = null;
		for (eurema.ModelResource r : this.eModelResourceSet.getResources()) {
			String rUri = r.getURI();
			if (uri.equals(rUri)) {
				modelResource = r;
				break;
			}
		}
		return modelResource;
	}

	/**
	 * Adds a mapping/delegation from one URI to another one.
	 * 
	 * @param source
	 *            the source URI of the mapping
	 * 
	 * @param target
	 *            the target URI of the mapping
	 */
	@Deprecated
	public void addURIMapping(String source, String target) {
		this.resourceSet.getURIConverter().getURIMap().put(URI.createURI(source), URI.createURI(target));
	}

	/**
	 * Loads an <tt>EPackage</tt> from the given URI.
	 * 
	 * @param nsURI
	 *            the namespace URI of the package to be loaded
	 * @return the loaded <tt>EPackage</tt>
	 */
	public EPackage getPackage(String nsURI) {
		if (nsURI == null || nsURI.equals("")) {
			throw new ModelRepositoryException(
					"Paramter \"nsUri\" to load an EPackage must " + "not be null or the empty String.");
		}
		Object ePackage = this.resourceSet.getPackageRegistry().get(nsURI);
		if (ePackage instanceof EPackage) {
			return (EPackage) ePackage;
		} else {
			return null;
		}
	}

	/**
	 * Registers an <tt>EPackage</tt>.
	 * 
	 * @param nsURI
	 *            the namespace URI of the <tt>EPackage</tt>
	 * @param ePackage
	 *            the <tt>EPackage</tt> instance
	 * @return the previous <tt>EPackage</tt> associated with the
	 *         <code>nsURI</code>, or <code>null</code> if there was no EPackage
	 *         for the <code>nsURI</code> before. (<code>null</code> as a
	 *         returned result can also indicate that the map previously
	 *         associated null with key, if the implementation supports null
	 *         values.)
	 */
	public Object registerPackage(String nsURI, EPackage ePackage) {
		return this.resourceSet.getPackageRegistry().put(nsURI, ePackage);
	}

	/**
	 * Registers an <tt>EPackage</tt> with its namespace URI
	 * <code>ePackage.getNsURI()</code>.
	 * 
	 * @param ePackage
	 *            the <tt>EPackage</tt> instance
	 * @return the previous <tt>EPackage</tt> associated with the
	 *         <code>ePackage.getNsURI()</code>, or <code>null</code> if there
	 *         was no <tt>EPackage</tt> for the <code>ePackage.getNsURI()</code>
	 *         before. (<code>null</code> as a returned result can also indicate
	 *         that the map previously associated null with key, if the
	 *         implementation supports null values.)
	 */
	public Object registerPackage(EPackage ePackage) {
		return this.resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
	}

	/**
	 * Persists an <tt>EMF</tt> resource to disk.
	 * 
	 * @param resource
	 *            the <tt>EMF</tt> resource to save to disk.
	 */
	public void saveResourceToDisk(Resource resource) {
		if (resource == null) {
			throw new ModelRepositoryException("Paramter \"resource\" to save a resource must not be null.");
		}
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		try {
			resource.save(options);
		} catch (IOException e) {
			throw new RuntimeException("Error in saving resource to disk.", e);
		}
	}

	/**
	 * Persists a model resource to disk.
	 * 
	 * @param modelResource
	 *            the resource to save to disk.
	 */
	public void saveResourceToDisk(Model modelResource) {
		if (modelResource == null) {
			throw new ModelRepositoryException("Paramter \"modelResource\" to save a resource must not be null.");
		}
		this.saveResourceToDisk(modelResource.getEmfResource());
	}

	/**
	 * Checks whether an <tt>EMF</tt> resource is contained in the model
	 * repository based on its URI.
	 * 
	 * @param uri
	 *            the URI of the <tt>EMF</tt> resource to check the containment
	 *            of the resource in the repository
	 * @return <code>true</code> if the resource is part of the repository, else
	 *         <code>false</code>
	 */
	public boolean containsEMFResource(URI uri) {
		if (uri == null) {
			throw new ModelRepositoryException("Paramter \"uri\" to check containment of "
					+ "an EMF resource in the resource set must not be null.");
		}
		Resource r = this.resourceSet.getResource(uri, false);
		return r != null;
	}

	public void removeModelResource(URI uri) {
		if (uri == null) {
			throw new ModelRepositoryException("Paramter \"uri\" to check containment of "
					+ "a model resource in the EUREMA model must not be null.");
		}

		EObject mod = this.retrieveModelResourceByURI(uri.toString());
		if (mod != null)
			EcoreUtil.remove(mod);

	}

	/**
	 * Checks whether a model resource is contained in the EUREMA model as a
	 * {@code eurema.ModelResource} element based on its URI.
	 * 
	 * @param uri
	 *            the URI of the model resource whose containment should be
	 *            checked.
	 * @return <code>true</code> if there is an instance of
	 *         {@code eurema.ModelResource} with the specified URI in the EUREMA
	 *         model, else <code>false</code>.
	 */
	public boolean containsModelResource(URI uri) {
		if (uri == null) {
			throw new ModelRepositoryException("Paramter \"uri\" to check containment of "
					+ "a model resource in the EUREMA model must not be null.");
		}
		EObject modelResource = this.retrieveModelResourceByURI(uri.toString());
		return modelResource != null;
	}

	/**
	 * Checks whether a model is contained in the model repository based on its
	 * URI.
	 * 
	 * @param uri
	 *            the URI of the model whose containment in the repository
	 *            should be checked.
	 * @return <code>true</code> if there is a model with the specified
	 *         <code>uri</code> contained in the repository, else
	 *         <code>false</code>
	 */
	public boolean containsModel(URI uri) {
		if (uri == null) {
			throw new ModelRepositoryException(
					"Paramter \"uri\" to check containment of " + "a model in the repository must not be null.");
		}
		boolean emfContained = this.containsEMFResource(uri);
		boolean modelContained = this.containsModelResource(uri);
		if (emfContained & modelContained) {
			return true;
		} else if (!emfContained & !modelContained) {
			return false;
		} else {
			throw new ModelRepositoryException(
					"Inconsistent model repository: URI " + uri.toString() + " is used\n" + " * in the EURAMA model? "
							+ modelContained + "\n" + " * in the EMF Resource Set? " + emfContained);
		}
	}

	/**
	 * Adds an <tt>EMF</tt> resource as a model to the repository.
	 * 
	 * @param resource
	 *            the resource to be added.
	 * @param name
	 *            the name of the model
	 * @return the added model
	 */
	public Model addModel(Resource resource, String name) {
		if (resource == null || name == null || name.equals("")) {
			throw new ModelRepositoryException("Paramter \"resource\" must not be null or \"name\" must "
					+ "not be null or the empty String in order to " + "add a model to the repository.");
		}
		URI resourceURI = resource.getURI();
		logger.debug("Add the following resource to the repository: " + resourceURI.toString());
		if (this.containsModel(resourceURI)) {
			eurema.ModelResource eModelResource = this.retrieveModelResourceByURI(resourceURI.toString());
			return new Model(eModelResource, resource);
		} else {
			eurema.ModelResource modelResource = this.createModelResource(resourceURI.toString(), name);
			this.eModelResourceSet.getResources().add(modelResource);
			this.resourceSet.getResources().add(resource);
			return new Model(modelResource, resource);
		}
	}

	/**
	 * Returns a String representation of the resources contained in this
	 * repository.
	 * 
	 * @return a String listing the resources' URIs contained in this
	 *         repository.
	 */
	public String listResources() {
		StringBuffer sb = new StringBuffer("EUREMA model repository:\n");
		sb.append("\nResources in the EMF resource set:\n");
		int i = 1;
		for (Resource r : this.resourceSet.getResources()) {
			sb.append(" (" + i + ") " + r.getURI().toString() + "\n");
			i++;
		}
		sb.append("\nResources in the EUREMA model:\n");
		int j = 1;
		for (eurema.ModelResource modelResource : this.eModelResourceSet.getResources()) {
			String modelResourceURI = modelResource.getURI();
			sb.append(" (" + j + ") " + modelResourceURI + "\n");
			j++;
		}

		return sb.toString();
	}

}
