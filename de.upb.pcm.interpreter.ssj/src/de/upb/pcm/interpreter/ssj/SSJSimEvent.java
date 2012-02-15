package de.upb.pcm.interpreter.ssj;

import umontreal.iro.lecuyer.simevents.Event;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.IEntityDelegate;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.ISimEventDelegate;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.SimEvent;

public class SSJSimEvent extends Event implements ISimEventDelegate {
	
	SimEvent myAbstractEvent = null;
	IEntityDelegate who = null;
	
	public SSJSimEvent(SimEvent myEvent, String name) {
		// super(owner, name, false);
		super(((SSJExperiment)myEvent.getModel().getSimulationControl()).getSimulator());
		this.myAbstractEvent = myEvent;
	}

	public void schedule(de.uka.ipd.sdq.simucomframework.abstractSimEngine.IEntityDelegate resource, double time) {
		who = resource;
		((SSJEntity)who).isScheduled = true;
		((SSJEntity)who).nextEventForThisEntity = this;
		this.schedule(time);
	}

	@Override
	public void actions() {
		// Check stop conditions when an event happens...
		((SSJExperiment)myAbstractEvent.getModel().getSimulationControl()).checkStopConditions();
		
		((SSJEntity)who).isScheduled = false;
		((SSJEntity)who).nextEventForThisEntity = null;
		myAbstractEvent.eventRoutine(((SSJEntity)who).getEntity());
	}

	@Override
	public void removeEvent() {
		this.cancel();
	}
}
