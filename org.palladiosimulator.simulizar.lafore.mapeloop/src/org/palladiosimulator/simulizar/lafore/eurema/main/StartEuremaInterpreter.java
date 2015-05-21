package org.palladiosimulator.simulizar.lafore.eurema.main;


import measurements.util.MeasurementsResourceFactoryImpl;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.monitorrepository.util.MonitorRepositoryResourceFactoryImpl;

import strategies.util.StrategiesResourceFactoryImpl;
import violations.util.ViolationsResourceFactoryImpl;
import vsmappings.util.VsmappingsResourceFactoryImpl;
import de.mdelab.eurema.interpreter.EuremaInterpreter;
import de.mdelab.eurema.interpreter.EuremaInterpreterFactory;
import de.mdelab.eurema.interpreter.EventQueue;
import de.uka.ipd.sdq.pcm.allocation.util.AllocationResourceFactoryImpl;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryResourceFactoryImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.util.ResourceenvironmentResourceFactoryImpl;
import de.uka.ipd.sdq.pcm.system.util.SystemResourceFactoryImpl;
import eurema.EuremaFactory;

public class StartEuremaInterpreter {

	public static void main(String[] args) {
		registerModels();
		
		EuremaInterpreter interpreter = EuremaInterpreterFactory.getInstance();
		EventQueue q = interpreter
				.execute("../org.palladiosimulator.simulizar.lafore.LoopAndZnn/Lafore.eurema");

		eurema.Event startingEvent = EuremaFactory.eINSTANCE.createEvent();
		startingEvent.setName("StartMape");
		eurema.EventType startingEventType = EuremaFactory.eINSTANCE.createEventType();
		startingEventType.setType("StartMapeEvent");
		startingEvent.setType(startingEventType);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ============================================================

		System.out.println("==============================");
		System.out.println();
		System.out.println("Added StartMape event.");
		System.out.println();
		System.out.println("==============================");

		q.add(startingEvent);


	}
	
	public static void registerModels(){
		
		final Resource.Factory repositoryFactory = new RepositoryResourceFactoryImpl();
        final Resource.Factory resourceEnvironmentFactory = new ResourceenvironmentResourceFactoryImpl();
        final Resource.Factory systemFactory = new SystemResourceFactoryImpl();
        final Resource.Factory allocationFactory = new AllocationResourceFactoryImpl();
        
        final Resource.Factory monitorrepositoryFactory = new MonitorRepositoryResourceFactoryImpl();
        
        final Resource.Factory measurementsFactory = new MeasurementsResourceFactoryImpl();
        final Resource.Factory violationsFactory = new ViolationsResourceFactoryImpl();
        final Resource.Factory strategiesFactory = new StrategiesResourceFactoryImpl();
        final Resource.Factory vsmappingsFactory = new VsmappingsResourceFactoryImpl();
        
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("repository", repositoryFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("resourceenvironment",
                resourceEnvironmentFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("system", systemFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("allocation", allocationFactory);
        
        
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("monitorrepository", monitorrepositoryFactory);
        EPackage.Registry.INSTANCE.put("http://palladiosimulator.org/MonitorRepository/1.0", monitorrepositoryFactory);
        
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("measurements", measurementsFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("violations", violationsFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("strategies", strategiesFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("vsmappings", vsmappingsFactory);
	}

}
