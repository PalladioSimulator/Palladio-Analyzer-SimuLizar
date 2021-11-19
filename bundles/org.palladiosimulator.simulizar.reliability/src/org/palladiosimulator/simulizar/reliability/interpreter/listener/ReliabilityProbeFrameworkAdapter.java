package org.palladiosimulator.simulizar.reliability.interpreter.listener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.probeframework.calculator.DefaultCalculatorProbeSets;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.probes.SupplementaryMeasurementAttachingProbe;
import org.palladiosimulator.simulizar.entity.InterpretableLocationReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.listener.InterpreterResultListener;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reliability.probe.ExecutionResultTypeProbe;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

@RuntimeExtensionScope
public class ReliabilityProbeFrameworkAdapter implements InterpreterResultListener, IModelObserver {
    
    private final ResourceSetPartition globalPartition;
    private final Map<String, ExecutionResultTypeProbe> probeRegister;
    private final IGenericCalculatorFactory calculatorFactory;
    private final Provider<TakeCurrentSimulationTimeProbe> simulationTimeProbeFactory;

    @Inject
    public ReliabilityProbeFrameworkAdapter(@Global PCMResourceSetPartition globalPartition,
            IGenericCalculatorFactory calculatorFactory, Provider<TakeCurrentSimulationTimeProbe> simulationTimeProbeFactory) {
        this.globalPartition = globalPartition;
        this.calculatorFactory = calculatorFactory;
        this.simulationTimeProbeFactory = simulationTimeProbeFactory;   
        probeRegister = new HashMap<>();
    }
    
    @Override
    public void initialize() {
        var adapter = new EContentAdapter() {
            public void notifyChanged(org.eclipse.emf.common.notify.Notification notification) {
                //TODO implement on change
            };
        };
        globalPartition.getElement(MonitorRepositoryPackage.Literals.MONITOR_REPOSITORY).forEach(repo -> {
            repo.eAdapters().add(adapter);
            addMonitorRepository((MonitorRepository) repo);
        });
    }
    
    protected void addMonitorRepository(MonitorRepository repo) {
        repo.getMonitors().stream().flatMap(monitor -> 
            monitor.getMeasurementSpecifications().stream().filter(spec -> MetricDescriptionConstants.EXECUTION_RESULT_TYPE_TUPLE.getId().equals(spec.getMetricDescription().getId()))
        ).forEach(this::addMeasurementSpec);
        
    }
    
    protected void addMeasurementSpec(MeasurementSpecification spec) {
        var measuringPoint = spec.getMonitor().getMeasuringPoint();
        var measurementIdentifier = MonitorRepositoryUtil.getMeasurementIdentifier(measuringPoint);
        var probe = new ExecutionResultTypeProbe();
        var composedProbe = new SupplementaryMeasurementAttachingProbe(MetricDescriptionConstants.EXECUTION_RESULT_TYPE_TUPLE, 
                probe, Collections.singletonList(simulationTimeProbeFactory.get()));
        calculatorFactory.buildCalculator(MetricDescriptionConstants.EXECUTION_RESULT_TYPE_TUPLE, measuringPoint, 
                DefaultCalculatorProbeSets.createSingularProbeConfiguration(composedProbe));
        this.probeRegister.put(measurementIdentifier, probe);
    }
    
    @Override
    public void interpretationFinished(InterpretableLocationReference interpretableLocation, InterpreterResult result, InterpreterDefaultContext context) {
        var probe = probeRegister.get(interpretableLocation.getLocationIdentifier());
        if (probe != null) {
            probe.measureExecutionResultType(result, context.getThread().getRequestContext());
        }
    }
    
    @Override
    public void unregister() {
        probeRegister.clear();
    }

}
