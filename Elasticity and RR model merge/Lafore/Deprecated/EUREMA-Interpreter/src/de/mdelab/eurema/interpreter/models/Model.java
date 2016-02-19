package de.mdelab.eurema.interpreter.models;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Represents a model resource contained in the {@code ModelRepository} and to
 * be used within EUREMA.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public class Model {

	/**
	 * The {@code eurema.ModelResource} element of the model in the EUREMA
	 * model.
	 */
	private eurema.ModelResource eModelResource;

	/**
	 * The <tt>EMF</tt> resource of the model.
	 */
	private Resource emfResource;

	/**
	 * Constructor.
	 * 
	 * @param eModelResource
	 *            the {@code eurema.ModelResource} element.
	 * @param emfResource
	 *            the <tt>EMF</tt> resource of the model.
	 */
	public Model(eurema.ModelResource eModelResource, Resource emfResource) {
		String modelResourceURI = eModelResource.getURI();
		String emfResourceURI = emfResource.getURI().toString();
		if (modelResourceURI == null
				|| !modelResourceURI.equals(emfResourceURI)) {
			throw new ModelRepositoryException(
					"Inconsistent model: URIs in the EMF resource ("
							+ emfResourceURI + ") and in the EUREMA model ("
							+ modelResourceURI + ") are different.");
		}
		this.eModelResource = eModelResource;
		this.emfResource = emfResource;
	}

	/**
	 * Returns the {@code eurema.ModelResource} element of the model in the
	 * EUREMA model.
	 * 
	 * @return the {@code eurema.ModelResource} element of the model in the
	 *         EUREMA model.
	 */
	public eurema.ModelResource getModelResource() {
		return this.eModelResource;
	}

	/**
	 * Returns the <tt>EMF</tt> resource of the model.
	 * 
	 * @return the <tt>EMF</tt> resource of the model.
	 */
	public Resource getEmfResource() {
		return this.emfResource;
	}

}
