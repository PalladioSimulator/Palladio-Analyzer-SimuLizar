package org.palladiosimulator.simulizar.slidingwindow.tests.utils;

import java.util.Observer;

import de.uka.ipd.sdq.simulation.abstractsimengine.AbstractSimEntityDelegator;
import de.uka.ipd.sdq.simulation.abstractsimengine.AbstractSimProcessDelegator;
import de.uka.ipd.sdq.simulation.abstractsimengine.IEntity;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEvent;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimProcess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimRunnable;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.SimCondition;

final class SimEngineFactoryMock implements ISimEngineFactory {
	
	@Override
	public void setModel(ISimulationModel model) {
			
	}

	@Override
	public ISimulationControl createSimulationControl() {
		return new ISimulationControl() {
			
			private double simTime = Double.MAX_VALUE;
			
			@Override
			public void stop() {
			}
			
			@Override
			public void start() {
			}
			
			@Override
			public void setMaxSimTime(long simTime) {
				this.simTime = simTime;
			}
			
			@Override
			public boolean isRunning() {
				//this mock is always running, whether or not it is has been started
				return true;
			}
			
			@Override
			public double getCurrentSimulationTime() {
				//always return the specified max simulation time here
				return simTime;
			}
			
			@Override
			public void addTimeObserver(Observer observer) {
			}
			
			@Override
			public void addStopCondition(SimCondition maxMeasurementsStopCondition) {
			}
		};
	}

	@Override
	public ISimProcess createSimProcess(
			AbstractSimProcessDelegator myProcess, String name) {
		return null;
	}

	@Override
	public <E extends IEntity> ISimEvent<E> createSimEvent(
			ISimRunnable<E> myEvent, String name) {
		return new ISimEvent<E>() {

			@Override
			public void schedule(E entity, double delay) {
			}

			@Override
			public void removeEvent() {
			}

			@Override
			public double scheduledAtTime() {
				return 0;
			}
		};
	}

	@Override
	public IEntity createEntity(AbstractSimEntityDelegator e, String name) {
		return null;
	}

}
