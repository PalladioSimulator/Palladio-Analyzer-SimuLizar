package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import strategies.StrategiesFactory;
import strategies.StrategiesRepository;
import strategies.Strategy;
import strategies.StrategyType;
import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationType;
import violations.ViolationsRepository;
import violationstrategymappings.ViolationStrategyMapping;
import violationstrategymappings.ViolationStrategyMappingRepository;

/**
 * This class implements the Plan operation for the LAFORE feedback loop.
 * 
 * @author Goran Piskachev
 * 
 */
public class PlanImplementation implements IModelOperation {

	/**
	 * This class' internal LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(Reconfigurator.class);

	private RuntimeViolationsModel vRun;
	private IModelAccess access;
	private RuntimeStrategiesModel sRun;

	public void setRuntimeViolationsModel(RuntimeViolationsModel v) {
		vRun = v;
	}

	public void setRuntimeStrategiesModel(RuntimeStrategiesModel s) {
		sRun = s;
	}

	public void setModelAccess(IModelAccess modAccess) {
		access = modAccess;
	}

	@Override
	public ModelOperationResult run(List<Resource> models) {
		// System.out.println("Executing the model operations implementation: "
		// + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();
		// TODO: implement the plan here!!!

		// RuntimeViolationsModel vRuntime = null;
		ViolationStrategyMappingRepository vsMappingRepository = null;
		StrategiesRepository sRepository = null;
		ViolationsRepository vRepository = null;

		// output
		// RuntimeStrategiesModel sRuntime =
		// StrategiesFactory.eINSTANCE.createRuntimeStrategiesModel();

		// System.out.println("Input Models:");
		for (Resource r : models) {
			// System.out.println(r.getURI().toString()); // print each model
			for (EObject c : r.getContents()) {
				// if (c instanceof RuntimeViolationsModel) {
				// vRuntime = (RuntimeViolationsModel) c;
				// }
				if (c instanceof ViolationStrategyMappingRepository) {
					vsMappingRepository = (ViolationStrategyMappingRepository) c;
				}
				// if (c instanceof RuntimeStrategiesModel) {
				// sRuntime = (RuntimeStrategiesModel) c;
				// }
				if (c instanceof StrategiesRepository) {
					sRepository = (StrategiesRepository) c;
				}
				if (c instanceof ViolationsRepository) {
					vRepository = (ViolationsRepository) c;
				}
			}
		}

		// iterate through all violations available in the Runtime violations
		// model
		for (Violation violation : vRun.getViolations()) {

			List<StrategyType> foundStrategies = new ArrayList<StrategyType>();
			List<Integer> priorities = new ArrayList<Integer>();

			// find a mapping to strategy for each violation
			for (ViolationStrategyMapping vsMap : vsMappingRepository.getVsmappings()) {
				String id1 = vsMap.getViolation().getId();
				String id2 = violation.getViolationType().getId();
				if (id1.equals(id2)) {
					StrategyType stTmp = getStrategyType(vsMap.getStrategy().getId(), sRepository);
					foundStrategies.add(stTmp);
					priorities.add(vsMap.getStrategyPriority());
				}

			}
			// We add all found strategies. The order is set according to the
			// priorities of the mappings
			while (!foundStrategies.isEmpty()) {
				int maxIndex = 0;
				int maxPriority = priorities.get(maxIndex);
				for (int i : priorities) {
					if (i > maxPriority) {
						maxPriority = i;
						maxIndex = priorities.indexOf(i);
					}
				}
				Strategy newStrategy = StrategiesFactory.eINSTANCE.createStrategy();
				newStrategy.setStrategyType(foundStrategies.get(maxIndex));
				foundStrategies.remove(maxIndex);
				priorities.remove(maxIndex);
				// TODO: Check if the strategy is already there. If yes, do not
				// add it.
				if (!existsInList(newStrategy))
					sRun.getStrategies().add(newStrategy);
			}

		}

		// ResourceSet resourceSet = new ResourceSetImpl();
		// URI platformPluginURI =
		// URI.createFileURI(FileUtil.getAbsoluteFilename("org.palladiosimulator.simulizar",
		// "knowledgeModels/MyRuntimeStrategies.strategies"));
		// Resource resource = resourceSet.createResource(platformPluginURI);
		// resource.getContents().add(sRun);
		//
		// try {
		// resource.save(Collections.emptyMap());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// if (sRuntime.eResource() != null)
		// output.add(sRun.eResource());

		vRun.getViolations().clear();

		ModelOperationResult result = new ModelOperationResult("planned", output);

		return result;
	}

	/**
	 * Method that checks if the strategy is already in the list.
	 * 
	 * @param newStrategy
	 *            the instance of the strategy
	 * 
	 */
	private boolean existsInList(Strategy newStrategy) {
		for (Strategy st : sRun.getStrategies()) {
			if (st.getStrategyType().getEntityName().equals(newStrategy.getStrategyType().getEntityName()))
				return true;
		}
		return false;
	}

	/**
	 * Method that returns the strategy type object from the strategies
	 * repository for a given id.
	 * 
	 * @param id
	 *            the id of the strategy type
	 * @param sRepository
	 *            the strategies repository
	 * 
	 * @return the strategy type object
	 */
	public StrategyType getStrategyType(String id, StrategiesRepository sRepository) {
		for (ServiceEffectSpecification st : sRepository.getServiceEffectSpecifications__BasicComponent()) {
			if (((StrategyType) st).getId().equals(id))
				return (StrategyType) st;
		}
		return null;
	}

	/**
	 * Method that returns the violation type object from the violations
	 * repository for a given id.
	 * 
	 * @param id
	 *            the id of the violation type
	 * @param vRepository
	 *            the violations repository
	 * 
	 * @return the violation type object
	 */
	public ViolationType getViolationType(String id, ViolationsRepository vRepository) {
		for (ViolationType vt : vRepository.getViolationTypes()) {
			if (vt.getId() == id)
				return vt;
		}
		return null;
	}

}
