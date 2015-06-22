package org.palladiosimulator.simulizar.reconfiguration;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class ReconfigurationProcess extends SimuComSimProcess {

	private final EObject monitoredElement;
	private final IReconfigurationListener reconfigurationDispatcher;
	private final List<IReconfigurationEngine> reconfigurators;
	private final IModelAccess modelAccess;

	protected ReconfigurationProcess(SimuComModel model, String name, List<IReconfigurationEngine> reconfigurators,
			EObject monitoredElement, IReconfigurationListener reconfigurationDispatcher, IModelAccess modelAccess) {
		super(model, name);
		this.reconfigurators = reconfigurators;
		this.monitoredElement = monitoredElement;
		this.reconfigurationDispatcher = reconfigurationDispatcher;
		this.modelAccess = modelAccess;
	}

	@Override
	protected void internalLifeCycle() {

		Reconfiguration reconfigurationModel = this.modelAccess.getReconfigurationModel();
		EList<Tactic> tactics = reconfigurationModel.getStrategies().get(0).getTactics();

		for (Tactic tactic : tactics) {
			boolean checkResult = false;
			boolean executeResult = false;
			for (IReconfigurationEngine reconfigurator : reconfigurators) {
				double startReconfigurationTime = this.getModel().getSimulationControl().getCurrentSimulationTime();
				reconfigurationDispatcher
						.beginReconfigurationEvent(new BeginReconfigurationEvent(startReconfigurationTime));
				checkResult |= reconfigurator.runCheck(tactic.getCondition(), monitoredElement);
				boolean reconfigResult = false;
				if (checkResult) {
					LOGGER.info("Reconfiguration check was positive. Executing Reconfiguration.");
				} else {
					LOGGER.info("Reconfiguration check was negative.");
				}
				double endReconfigurationTime = this.getModel().getSimulationControl().getCurrentSimulationTime();
				// TODO Christian FIXME pass changes instead of null
				this.reconfigurationDispatcher.reconfigurationExecuted(null);
				reconfigurationDispatcher.endReconfigurationEvent(
						new EndReconfigurationEvent(EventResult.fromBoolean(reconfigResult), endReconfigurationTime));
			}

			if (checkResult) {
				for (IReconfigurationEngine reconfigurator : reconfigurators) {
					double startReconfigurationTime = this.getModel().getSimulationControl().getCurrentSimulationTime();
					reconfigurationDispatcher
							.beginReconfigurationEvent(new BeginReconfigurationEvent(startReconfigurationTime));
					executeResult |= reconfigurator.runExecute(tactic.getAction(), monitoredElement);
					boolean reconfigResult = false;
					if (executeResult) {
						LOGGER.info("Reconfiguration execution succeeded.");
					} else {
						LOGGER.info("Reconfiguration execution failed.");
					}
					double endReconfigurationTime = this.getModel().getSimulationControl().getCurrentSimulationTime();
					// TODO Christian FIXME pass changes instead of null
					this.reconfigurationDispatcher.reconfigurationExecuted(null);
					reconfigurationDispatcher.endReconfigurationEvent(new EndReconfigurationEvent(
							EventResult.fromBoolean(reconfigResult), endReconfigurationTime));
				}
			}

		}

	}
}
