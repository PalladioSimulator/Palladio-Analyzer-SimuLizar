package org.palladiosimulator.simulizar.lafore.eurema.main;


import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.monitorrepository.util.MonitorRepositoryResourceFactoryImpl;
//import org.palladiosimulator.simulizar.lafore.interpreter.starter.SimulizarLaforeEventQueue;
//import org.palladiosimulator.simulizar.lafore.interpreter.starter.SimulizarLaforeInterpreter;
//import org.palladiosimulator.simulizar.lafore.interpreter.starter.SimulizarLaforeInterpreterFactory;

import strategies.util.StrategiesResourceFactoryImpl;
import violations.util.ViolationsResourceFactoryImpl;
import violationstrategymappings.util.ViolationstrategymappingsResourceFactoryImpl;
//import de.mdelab.eurema.interpreter.EuremaInterpreter;
//import de.mdelab.eurema.interpreter.EuremaInterpreterFactory;
//import de.mdelab.eurema.interpreter.EventQueue;
import de.uka.ipd.sdq.pcm.allocation.util.AllocationResourceFactoryImpl;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryResourceFactoryImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.util.ResourceenvironmentResourceFactoryImpl;
import de.uka.ipd.sdq.pcm.system.util.SystemResourceFactoryImpl;
import eurema.EuremaFactory;

/**
 * This class is just for testing the interpreter independent of SimuLizar. 
 * @author Goran Piskachev
 *
 */
public class StartEuremaInterpreter {

	public void startLafore() {
		//registerModels();
		
	/*	
		EuremaInterpreter interpreter = EuremaInterpreterFactory.getInstance();
		EventQueue q = interpreter
				.execute("E:\\Edu\\UPB\\MA thesis\\EclipseLafore\\ws\\org.palladiosimulator.simulizar.lafore.mapeloop\\EuremaLaforeMapeLoop\\Lafore.eurema"); 
				//.execute("platform:/resource/org.palladiosimulator.simulizar.lafore.mapeloop/EuremaLaforeMapeLoop/Lafore.eurema");
				
		
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
*/

	}
	
	public static void registerModels(){
		
		final Resource.Factory repositoryFactory = new RepositoryResourceFactoryImpl();
        final Resource.Factory resourceEnvironmentFactory = new ResourceenvironmentResourceFactoryImpl();
        final Resource.Factory systemFactory = new SystemResourceFactoryImpl();
        final Resource.Factory allocationFactory = new AllocationResourceFactoryImpl();
        
        final Resource.Factory monitorrepositoryFactory = new MonitorRepositoryResourceFactoryImpl();
        

        final Resource.Factory violationsFactory = new ViolationsResourceFactoryImpl();
        final Resource.Factory strategiesFactory = new StrategiesResourceFactoryImpl();
        final Resource.Factory vsmappingsFactory = new ViolationstrategymappingsResourceFactoryImpl();
        
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("repository", repositoryFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("resourceenvironment",
                resourceEnvironmentFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("system", systemFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("allocation", allocationFactory);
        
        
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("monitorrepository", monitorrepositoryFactory);
        EPackage.Registry.INSTANCE.put("http://palladiosimulator.org/MonitorRepository/1.0", monitorrepositoryFactory);
        

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("violations", violationsFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("strategies", strategiesFactory);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("vsmappings", vsmappingsFactory);
	}

}
