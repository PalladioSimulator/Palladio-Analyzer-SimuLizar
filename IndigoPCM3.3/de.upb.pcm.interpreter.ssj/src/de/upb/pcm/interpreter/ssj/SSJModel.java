/**
 * 
 */
package de.upb.pcm.interpreter.ssj;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * @author Snowball
 *
 */
public class SSJModel {

	private SimuComModel myModel;

	public SSJModel(SimuComModel model) {
		//super(null, model.getConfig().getNameExperimentRun(), false, false);
		this.myModel = model;
		myModel.doInitialSchedules();
	}
}
