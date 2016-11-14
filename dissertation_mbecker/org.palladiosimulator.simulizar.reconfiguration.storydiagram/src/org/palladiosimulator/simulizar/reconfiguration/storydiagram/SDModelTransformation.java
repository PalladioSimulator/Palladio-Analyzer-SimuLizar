package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcorePackage;
import org.palladiosimulator.pcm.allocation.AllocationPackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcm.system.SystemPackage;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl;
import org.storydriven.storydiagrams.activities.Activity;

public class SDModelTransformation extends ModelTransformationImpl<Activity> {

	private static final String USAGE_MODEL = "usageModel";
	private static final EClass USAGE_MODEL_ECLASS = UsagemodelPackage.eINSTANCE.getUsageModel();
	private static final String SYSTEM_MODEL = "systemModel";
	private static final EClass SYSTEM_MODEL_ECLASS = SystemPackage.eINSTANCE.getSystem();
	private static final String REPOSITORY_MODEL = "repositoryModel";
	private static final EClass REPOSITORY_MODEL_ECLASS = RepositoryPackage.eINSTANCE.getRepository();
	private static final String ALLOCATION_MODEL = "allocationModel";
	private static final EClass ALLOCATION_MODEL_ECLASS = AllocationPackage.eINSTANCE.getAllocation();
	private static final String RESOURCE_ENVIRONMENT_MODEL = "resourceEnvironmentModel";
	private static final EClass RESOURCE_ENVIRONMENT_MODEL_ECLASS = ResourceenvironmentPackage.eINSTANCE
            .getResourceEnvironment();
	private static final String PRM_MODEL = "runtimeMeasurementModel";
	private static final String MONITORED_ELEMENT = "monitoredElement";
	private static final EClass PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS = RuntimeMeasurementPackage.eINSTANCE
            .getRuntimeMeasurementModel();
	private static final EClass EOBJECT_ECLASS = EcorePackage.eINSTANCE.getEObject();

	public SDModelTransformation(Activity activity) {

		this.modelTransformation = ActivityLoader.createBindings(activity,
				new String[] { USAGE_MODEL, SYSTEM_MODEL, REPOSITORY_MODEL, ALLOCATION_MODEL,
						RESOURCE_ENVIRONMENT_MODEL, PRM_MODEL, MONITORED_ELEMENT },
				new EClassifier[] { USAGE_MODEL_ECLASS, SYSTEM_MODEL_ECLASS, REPOSITORY_MODEL_ECLASS,
						ALLOCATION_MODEL_ECLASS, RESOURCE_ENVIRONMENT_MODEL_ECLASS,
						PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS, EOBJECT_ECLASS });
	}
}
