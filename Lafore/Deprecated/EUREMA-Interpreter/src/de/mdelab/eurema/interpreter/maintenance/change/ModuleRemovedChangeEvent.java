package de.mdelab.eurema.interpreter.maintenance.change;

/**
 * Change event about the removal of a module to the LD.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class ModuleRemovedChangeEvent extends UpdateChangeEvent {

	/**
	 * The layer from which the module has been removed.
	 */
	private eurema.Layer layer;

	/**
	 * The module removed to the LD.
	 */
	private eurema.Module removedModule;

	/**
	 * Constructor.
	 * 
	 * @param removedModule
	 *            The module removed to the LD.
	 * @param layer
	 *            The layer from which the module has been removed.
	 */
	public ModuleRemovedChangeEvent(eurema.Module removedModule,
			eurema.Layer layer) {
		super();
		this.removedModule = removedModule;
		this.layer = layer;
	}

	/**
	 * @return the module removed to the LD.
	 */
	public eurema.Module getRemovedModule() {
		return this.removedModule;
	}

	/**
	 * @return the layer from which the module has been removed.
	 */
	public eurema.Layer getLayer() {
		return this.layer;
	}

}
