package org.palladiosimulizar.aggregation.probeframework;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;

public class AggregatorsProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass FIXED_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.FIXED_SIZE_AGGREGATION;
    private static final EClass VARIABLE_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.VARIABLE_SIZE_AGGREGATION;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();

        Collection<MeasurementSpecification> fixedSizeAggregationMeasurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(FIXED_SIZE_AGGREGATION_ECLASS);
        Collection<MeasurementSpecification> variableSizeAggregMeasurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(VARIABLE_SIZE_AGGREGATION_ECLASS);

    }
}
