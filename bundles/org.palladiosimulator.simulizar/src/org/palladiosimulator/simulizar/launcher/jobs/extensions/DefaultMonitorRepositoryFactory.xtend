package org.palladiosimulator.simulizar.launcher.jobs.extensions

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPointRepository
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants
import org.palladiosimulator.monitorrepository.MonitorRepository
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint
import org.palladiosimulator.pcmmeasuringpoint.ExternalCallActionMeasuringPoint
import org.palladiosimulator.pcmmeasuringpoint.SystemOperationMeasuringPoint
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint

class DefaultMonitorRepositoryFactory {
	static val mf = MonitorRepositoryFactory.eINSTANCE
	
	static def MonitorRepository createDefaultMonitorRepository(MeasuringPointRepository measuringPointRepo) {
		val result = mf.createMonitorRepository
		measuringPointRepo.measuringPoints.forEach[createDefaultMonitors(result)]
		result
	}
	
	static def dispatch createDefaultMonitors(MeasuringPoint p, MonitorRepository repo) {
		// Default case, do nothing
	}
	
	static def dispatch createDefaultMonitors(UsageScenarioMeasuringPoint p, MonitorRepository repo) {
		repo.monitors += mf.createMonitor => [
			measuringPoint = p;
			measurementSpecifications += mf.createMeasurementSpecification => [
				metricDescription = MetricDescriptionConstants.RESPONSE_TIME_METRIC
				triggersSelfAdaptations = false
				processingType = mf.createFeedThrough
			]
		]
	}
	
	static def dispatch createDefaultMonitors(SystemOperationMeasuringPoint p, MonitorRepository repo) {
		repo.monitors += mf.createMonitor => [
			measuringPoint = p;
			measurementSpecifications += mf.createMeasurementSpecification => [
				metricDescription = MetricDescriptionConstants.RESPONSE_TIME_METRIC
				triggersSelfAdaptations = false
				processingType = mf.createFeedThrough
			]
		]
	}
	
	static def dispatch createDefaultMonitors(ExternalCallActionMeasuringPoint p, MonitorRepository repo) {
		repo.monitors += mf.createMonitor => [
			measuringPoint = p;
			measurementSpecifications += mf.createMeasurementSpecification => [
				metricDescription = MetricDescriptionConstants.RESPONSE_TIME_METRIC
				triggersSelfAdaptations = false
				processingType = mf.createFeedThrough
			]
		]
	}

	static def dispatch createDefaultMonitors(ActiveResourceMeasuringPoint p, MonitorRepository repo) {
		repo.monitors += mf.createMonitor => [
			measuringPoint = p;
			measurementSpecifications += mf.createMeasurementSpecification => [
				metricDescription = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC
				triggersSelfAdaptations = false
				processingType = mf.createFeedThrough
			]
			measurementSpecifications += mf.createMeasurementSpecification => [
				metricDescription = MetricDescriptionConstants.RESOURCE_DEMAND_METRIC
				triggersSelfAdaptations = false
				processingType = mf.createFeedThrough
			]
		]
	}
	
}