package de.upb.pcm.interpreter.ssj;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

import umontreal.iro.lecuyer.simevents.Event;
import umontreal.iro.lecuyer.simevents.Simulator;
import umontreal.iro.lecuyer.simevents.eventlist.SplayTree;
import de.uka.ipd.sdq.scheduler.ISchedulingFactory;
import de.uka.ipd.sdq.scheduler.factory.SchedulingFactory;
import de.uka.ipd.sdq.scheduler.resources.active.AbstractActiveResource;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.Condition;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.ISimulationControlDelegate;
import de.uka.ipd.sdq.simucomframework.exceptions.FailureStatistics;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SSJExperiment implements ISimulationControlDelegate {

	private Simulator simulator;
	private ArrayList<Condition> stopConditions = new ArrayList<Condition>();
	private ArrayList<Observer> timeObservers = new ArrayList<Observer>();
	double lastNotificationTime = 0.0;
	private AtomicBoolean isRunning = new AtomicBoolean();
	private SimuComModel model;

	public SSJExperiment(final SimuComModel model) {
		model.setSimulationControl(this);
		simulator = new Simulator();
		simulator.init(new SplayTree());
		SchedulingFactory.setUsedSimulator(simulator);
		ISchedulingFactory.eINSTANCE.resetFactory();

		this.model = model;
		createStartEvent(model).schedule(0);
		createCheckEvent(model).schedule(1);
	}

	public void addStopCondition(Condition condition) {
		stopConditions.add(condition);
	}

	public void checkStopConditions() {
		if (lastNotificationTime != this.getCurrentSimulationTime()) {
			lastNotificationTime = this.getCurrentSimulationTime();
			for (Observer o : timeObservers) {
				o.update(null, lastNotificationTime);
			}
		}
		for (Condition c : stopConditions) {
			if (c.check()) {
				createStopEvent().schedule(0);
				return;
			}
		}
	}

	public void addTimeObserver(Observer observer) {
		timeObservers.add(observer);
	}

	public double getCurrentSimulationTime() {
		return simulator.time();
	}

	public void setMaxSimTime(long simTime) {
		createStopEvent().schedule(simTime);
	}

	protected static Logger logger = Logger.getLogger(SSJExperiment.class
			.getName());

	public void start() {
		this.isRunning.set(true);
		double start = System.nanoTime();
		logger.warn("Starting simulation...");
		simulator.start();
		logger.warn("Simulation terminated. Took "
				+ ((System.nanoTime() - start) / Math.pow(10, 9))
				+ " real time seconds.");
	}

	public void stop() {
		// isRunning indicates that this method has been already executed.
		// The method must only be executed once, so we use an atomic operation
		// to avoid multiple accesses. Setting isRunning to false allows all
		// processes to clean up.
		if (isRunning.compareAndSet(true, false)) {

			logger.info("Simulation stop requested!");

			// This method MUST be called before all resources are deactivated,
			// otherwise new threads might request processing time after the
			// resource has been removed. These threads will not be cleaned up
			// correctly.
			simulator.stop();

			this.model.getResourceRegistry().deactivateAllActiveResources();
			this.model.getResourceRegistry().deactivateAllPassiveResources();
			logger
					.info("Simulation took "
							+ this.model.getSimulationControl()
									.getCurrentSimulationTime()
							+ " simulation seconds");

			AbstractActiveResource.cleanProcesses();

			// Print failure statistics:
			if (this.model.getConfig().getSimulateFailures()) {
				FailureStatistics.getInstance().printFailureStatistics(logger);
			}
			
			// This does some cleanup.
			// TODO: review this, maybe this is obsolete, if the stuff is cleanup on a different place.
			// Currently, this has to be done to cleanup some memory.
			ISchedulingFactory.eINSTANCE.resetFactory();

		}

	}

	public Simulator getSimulator() {
		return simulator;
	}

	public boolean isRunning() {
		return this.isRunning.get();
	}

	private Event createStopEvent() {
		return new Event(simulator) {

			@Override
			public void actions() {
				if (isRunning()) {
					logger.debug("Executing Stop Event");
					stop();
				}
			}

		};
	}

	private Event createStartEvent(final SimuComModel model) {
		return new Event(simulator) {

			@Override
			public void actions() {
				logger.debug("Executing Initial Event");
				model.doInitialSchedules();
			}

		};
	}

	private Event createCheckEvent(final SimuComModel model) {
		return new Event(simulator) {

			@Override
			public void actions() {
				checkStopConditions();
				if (model.getSimulationControl().isRunning())
					this.schedule(1);
			}

		};
	}
}
