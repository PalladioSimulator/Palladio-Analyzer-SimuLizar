package org.palladiosimulator.simulizar.launcher.jobs.extensions

import org.eclipse.emf.common.notify.Notifier
import org.eclipse.emf.ecore.resource.ResourceSet
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPointRepository
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory
import org.palladiosimulator.pcm.repository.OperationProvidedRole
import org.palladiosimulator.pcm.repository.OperationSignature
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification
import org.palladiosimulator.pcm.seff.ExternalCallAction
import org.palladiosimulator.pcm.system.System
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall
import org.palladiosimulator.pcm.usagemodel.UsageScenario
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory

class DefaultMeasuringPointRepositoryFactory {
	static val mf = MeasuringpointFactory.eINSTANCE;
	static val pmf = PcmmeasuringpointFactory.eINSTANCE;
	
	static def MeasuringPointRepository createDefaultRepository(ResourceSet resourceSet) {
		val result = mf.createMeasuringPointRepository
		resourceSet.allContents.forEach[it.createMeasuringPoint(result)]
		result
	}
	
	static def dispatch createMeasuringPoint(Notifier notifier, MeasuringPointRepository repository) {
		// Do nothing
	}
	
	static def dispatch createMeasuringPoint(UsageScenario scenario, MeasuringPointRepository repository) {
		repository.measuringPoints += pmf.createUsageScenarioMeasuringPoint => [
			usageScenario = scenario
		]
	} 
	
	static def dispatch createMeasuringPoint(ProcessingResourceSpecification procSpec, MeasuringPointRepository repository) {
		repository.measuringPoints += pmf.createActiveResourceMeasuringPoint => [
			activeResource = procSpec
			replicaID = 0
		]
	}
	
	static def dispatch createMeasuringPoint(ExternalCallAction call, MeasuringPointRepository repository) {
		repository.measuringPoints += pmf.createExternalCallActionMeasuringPoint => [
			externalCall = call
		]
	}
	
	static def dispatch createMeasuringPoint(EntryLevelSystemCall call, MeasuringPointRepository repository) {
		repository.measuringPoints += pmf.createEntryLevelSystemCallMeasuringPoint => [
			entryLevelSystemCall = call
		]
	}
	
	static def dispatch createMeasuringPoint(System sys, MeasuringPointRepository repository) {
		sys.providedRoles_InterfaceProvidingEntity.filter(OperationProvidedRole).forEach[provided |
			provided.providedInterface__OperationProvidedRole.signatures__OperationInterface.filter(OperationSignature).forEach[signature | 
				repository.measuringPoints += pmf.createSystemOperationMeasuringPoint => [
					operationSignature = signature
					role = provided
					system = sys
				]
			]
		]	
	}
}