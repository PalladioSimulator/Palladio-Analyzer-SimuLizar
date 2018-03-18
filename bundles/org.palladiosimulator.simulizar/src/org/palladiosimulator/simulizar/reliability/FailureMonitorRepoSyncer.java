package org.palladiosimulator.simulizar.reliability;

import java.util.Arrays;
import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.metricspec.util.builder.MetricSetDescriptionBuilder;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.probeframework.probes.EventProbeList;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.simulizar.modelobserver.AbstractModelObserver;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;

public class FailureMonitorRepoSyncer extends AbstractModelObserver<MonitorRepository>{

	@Override
	public void initialize(AbstractSimuLizarRuntimeState runtimeState) {
		Optional.ofNullable(runtimeState.getPCMPartitionManager().<MonitorRepository>findModel(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository()))
		.ifPresent(repo -> initialize(repo, runtimeState));			
		
	}

	@Override
	public void initialize(MonitorRepository model, AbstractSimuLizarRuntimeState runtimeState) {
		super.initialize(model, runtimeState);
		
		//Copied from AbstractMain
		final MetricSetDescription execOverTime = MetricSetDescriptionBuilder.
                newMetricSetDescriptionBuilder().
                name("Execution Result Over Time").
                textualDescription("This metric records execution results over time, i.e., tuples of (execution result, point in time").
                id(EcoreUtil.generateUUID()).
                subsumedMetrics(
                        Arrays.asList(
                        		FailureProbe.getOrCreateMetricDescription(FailureStatistics.getSimFailureTypes(),
                        				runtimeState.getModel().getFailureStatistics().getExecutionResultId(null)),
                                MetricDescriptionConstants.POINT_IN_TIME_METRIC)).
                                build();
		//runtimeState.getMainContext()
		EList<Monitor> monitors = model.getMonitors();
		/*monitors.stream()
		.flatMap(m -> m.getMeasurementSpecifications().stream())
		.forEach(mspec -> {
			MetricSetDescription expected = MetricDescriptionConstants.EXECUTION_RESULT_OVER_TIME_METRIC;
			MetricDescription actual = mspec.getMetricDescription();
			System.out.println(actual.getName());
		});*/
		monitors.stream()
		.filter(m -> m.getMeasurementSpecifications().stream()
					.map(MeasurementSpecification::getMetricDescription)
					.filter( d -> MetricDescriptionConstants.EXECUTION_RESULT_METRIC.getId().equals(d.getId()))
					.findAny().isPresent())
		.forEach(m -> {
			MeasuringPoint mp = m.getMeasuringPoint();
			
			URI targetURI = URI.createURI(mp.getResourceURIRepresentation());	
			String fragment = targetURI.fragment();
			final EObject target = runtimeState.getPCMPartitionManager().getLocalPCMModel().loadModel(targetURI).getEObject(fragment);
			if(target instanceof Identifier) {
				//mp.getResourceURIRepresentation()
				runtimeState.getModel().getProbeFrameworkContext().getCalculatorFactory()
				.buildExecutionResultCalculator(mp, 
						new EventProbeList(
								execOverTime,
	                            new FailureProbe((Identifier)target, runtimeState.getEventDispatcher(), runtimeState.getModel().getFailureStatistics()),
	                            Arrays.asList(
	                                    (TriggeredProbe) new TakeCurrentSimulationTimeProbe(runtimeState.getModel().getSimulationControl())
	                                    )
	                            )
	                    );
			}		
		});
		
	}
	
	

}
