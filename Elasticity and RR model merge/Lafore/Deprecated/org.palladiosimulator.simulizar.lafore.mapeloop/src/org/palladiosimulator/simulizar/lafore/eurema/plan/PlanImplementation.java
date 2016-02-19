package org.palladiosimulator.simulizar.lafore.eurema.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import strategies.StrategiesFactory;
import strategies.StrategiesRepository;
import strategies.Strategy;
import strategies.StrategyType;
import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationsFactory;
import violations.ViolationsRepository;
import violationstrategymappings.ViolationStrategyMapping;
import violationstrategymappings.ViolationStrategyMappingRepository;
import violationstrategymappings.ViolationstrategymappingsFactory;

public class PlanImplementation implements IModelOperation{

	@Override
	public ModelOperationResult run(List<Resource> models) {
		System.out.println("Executing the model operations implementation: "
				+ this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();
		// TODO: implement the plan here!!!

		
		RuntimeViolationsModel vRuntime = ViolationsFactory.eINSTANCE.createRuntimeViolationsModel() ;
		ViolationStrategyMappingRepository vsMappingRepository = ViolationstrategymappingsFactory.eINSTANCE.createViolationStrategyMappingRepository();
		//StrategiesRepository sRepository = StrategiesFactory.eINSTANCE.createStrategiesRepository();
		
		RuntimeStrategiesModel sRuntime = StrategiesFactory.eINSTANCE.createRuntimeStrategiesModel();
		
		for (Resource r : models) {
			for (EObject c : r.getContents()) {
				if (c instanceof RuntimeViolationsModel) {
					vRuntime = (RuntimeViolationsModel)c;
				}
				if (c instanceof ViolationStrategyMappingRepository) {
					vsMappingRepository= (ViolationStrategyMappingRepository)c;
				}
				//if (c instanceof StrategiesRepository) {
				//	sRepository= (StrategiesRepository)c;
				//}
			}
		}
		
		
		// iterate through all violations available in the Runtime violations model
		for (Violation violation : vRuntime.getViolations()) {
			
			List<StrategyType> foundStrategies = new ArrayList<StrategyType>();
			List<Integer> priorities= new ArrayList<Integer>();
			
			// find a mapping to strategy for each violation 
			for(ViolationStrategyMapping vsMap : vsMappingRepository.getVsmappings()){
				if(vsMap.getViolation() == violation.getViolationType())
				{
					foundStrategies.add(vsMap.getStrategies());
					priorities.add(vsMap.getStrategyPriority());
				}
					
			}
			// now add the strategy with highest priority to the Runtime Strategies. If there are more strategies with same priority, add the first one you find
			if(!foundStrategies.isEmpty())
			{
				int maxIndex = 0;
				int maxPriority = priorities.get(maxIndex);
				for(int i : priorities)
				{
					if(i>maxPriority)
					{
						maxPriority=i;
						maxIndex = priorities.indexOf(i);
					}
				}
				Strategy newStrategy = StrategiesFactory.eINSTANCE.createStrategy();
				newStrategy.setStrategyType(foundStrategies.get(maxIndex));
				sRuntime.getStrategies().add(newStrategy);
			}
			
		}
		
		
		if (sRuntime.eResource()!=null)
			output.add(sRuntime.eResource());
		
		ModelOperationResult result = new ModelOperationResult(
				"planned", output);

		return result;
	}
	

}
