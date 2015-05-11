package de.mdelab.eurema.interpreter.maintenance.change;

/**
 * Change event about the addition of a module to the LD.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class ModuleAddedChangeEvent extends UpdateChangeEvent {

	/**
	 * The layer to which the module has been added.
	 */
	private eurema.Layer layer;

	/**
	 * The module added to the LD.
	 */
	private eurema.Module addedModule;

	/**
	 * Constructor.
	 * 
	 * @param addedModule
	 *            The module added to the LD.
	 * @param layer
	 *            the layer to which the module has been added.
	 */
	public ModuleAddedChangeEvent(eurema.Module addedModule, eurema.Layer layer) {
		super();
		this.addedModule = addedModule;
		this.layer = layer;
	}

	/**
	 * @return the module added to the LD.
	 */
	public eurema.Module getAddedModule() {
		return this.addedModule;
	}

	/**
	 * @return the layer to which the module has been added.
	 */
	public eurema.Layer getLayer() {
		return this.layer;
	}

}
