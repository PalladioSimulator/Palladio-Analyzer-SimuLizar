package de.mdelab.eurema.interpreter.maintenance.change;

/**
 * Change event about the removal of a layer to the LD.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class LayerRemovedChangeEvent extends UpdateChangeEvent {

	/**
	 * The layer removed from the LD.
	 */
	private eurema.Layer removedLayer;

	/**
	 * Constructor.
	 * 
	 * @param removedLayer
	 *            The layer removed from the LD.
	 */
	public LayerRemovedChangeEvent(eurema.Layer removedLayer) {
		super();
		this.removedLayer = removedLayer;
	}

	/**
	 * @return the removed layer from the LD.
	 */
	public eurema.Layer getRemovedLayer() {
		return this.removedLayer;
	}

}
