package de.mdelab.eurema.interpreter.maintenance.change;

/**
 * Change event about the addition of a layer to the LD.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class LayerAddedChangeEvent extends UpdateChangeEvent {

	/**
	 * The new layer added to the LD.
	 */
	private eurema.Layer addedLayer;

	/**
	 * Constructor.
	 * 
	 * @param newLayer
	 *            the new layer added to the LD.
	 */
	public LayerAddedChangeEvent(eurema.Layer newLayer) {
		super();
		this.addedLayer = newLayer;
	}

	/**
	 * @return the new layer added to the LD.
	 */
	public eurema.Layer getAddedLayer() {
		return this.addedLayer;
	}

}
