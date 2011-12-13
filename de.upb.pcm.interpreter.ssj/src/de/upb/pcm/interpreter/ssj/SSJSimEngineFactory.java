package de.upb.pcm.interpreter.ssj;

import de.uka.ipd.sdq.simucomframework.abstractSimEngine.Entity;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.IEntityDelegate;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.ISimEngineFactory;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.ISimEventDelegate;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.ISimProcessDelegate;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.ISimulationControlDelegate;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.SimEvent;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.SimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SSJSimEngineFactory implements ISimEngineFactory {

	public ISimulationControlDelegate createSimulationControl(SimuComModel model) {
		ISimulationControlDelegate delegate = new SSJExperiment(model);
		model.setSimulationControl(delegate);
		model.setSimulationEngineFactory(this);
		
		return delegate;
	}

	public ISimProcessDelegate createSimProcess(SimProcess myProcess, SimuComModel model, String name) {
		return new SSJSimProcess(
				myProcess,
				name);
	}

	public ISimEventDelegate createSimEvent(SimEvent myEvent, SimuComModel model, String name) {
		return new SSJSimEvent(
				myEvent,
				name);
	}

	public IEntityDelegate createEntity(Entity e, SimuComModel model, String name) {
		return new SSJEntity(
				e,
				name);
	}

}
