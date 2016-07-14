package org.palladiosimulizar.aggregation.probeframework;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulizar.aggregation.aggregators.FixedSizeMeasurementsAggregator;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class AggregatorsProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass FIXED_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.FIXED_SIZE_AGGREGATION;
    private static final EClass VARIABLE_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.VARIABLE_SIZE_AGGREGATION;

    private RegisterCalculatorFactoryDecorator calculatorFactory;
    private RuntimeMeasurementModel runtimeMeasurementModel;
    private SimuComModel model;

    @Override
    public void setProbeFrameworkListener(AbstractProbeFrameworkListener listener) {
        super.setProbeFrameworkListener(listener);
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
        this.model = getProbeFrameworkListener().getSimuComModel();
        this.runtimeMeasurementModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
    }

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();

        Collection<MeasurementSpecification> fixedSizeAggregationMeasurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(FIXED_SIZE_AGGREGATION_ECLASS);
        Collection<MeasurementSpecification> variableSizeAggregationMeasurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(VARIABLE_SIZE_AGGREGATION_ECLASS);

        fixedSizeAggregationMeasurementSpecs.forEach(this::initFixedSizeAggregation);

    }

    private void initFixedSizeAggregation(MeasurementSpecification measurementSpecification) {
        MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();
        NumericalBaseMetricDescription expectedMetric = (NumericalBaseMetricDescription) measurementSpecification
                .getMetricDescription();
        // TODO FR move parts from FixedSizemeasurementsAggregator class here
        new FixedSizeMeasurementsAggregator(measurementSpecification, this.calculatorFactory, this.model);

    }
}
