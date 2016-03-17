package org.palladiosimulator.simulizar.reconfiguration.qvto.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.palladiosimulator.analyzer.workflow.jobs.PreparePCMBlackboardPartitionJob;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.pcm.resourcetype.ResourcetypePackage;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * This cache implementation is used to store models (e.g., PCM models or runtime measurement
 * models) that can be parameters of QVTo transformations. To store a model in the cache, its
 * corresponding {@link EPackage} (its meta-model) is used as tag.
 * 
 * @author Florian Rosenthal, Sebastian Krach
 *
 */
public class QVToModelCache {

    // use a map: EPackage, i.e, meta-model serves as key/tag
    private final Map<EPackage, Set<EObject>> cache;
    
    private final IModelAccess modelAccess;

    // put EClass objects of blackboard models that are not intended to be transformation parameters
    // here
    private static final EClass[] MODEL_ECLASS_BLACKLIST = { ResourcetypePackage.Literals.RESOURCE_REPOSITORY,
            MetricSpecPackage.Literals.METRIC_DESCRIPTION_REPOSITORY };

    // switch to determine the meta-model/EPackage of a model
    private static final EcoreSwitch<EPackage> MODELTYPE_RETRIEVER = new EcoreSwitch<EPackage>() {

        @Override
        public EPackage caseEPackage(EPackage ePackage) {
            // we found the model type, just return
            return ePackage;
        }

        @Override
        public EPackage caseEClass(EClass eClass) {
            // from meta class to containing object, should be an EPackage
            return doSwitch(eClass.eContainer());
        }

        @Override
        public EPackage defaultCase(EObject eObject) {
            // go on with the meta class
            return doSwitch(eObject.eClass());
        }
    };

    /**
     * Initializes a new instance of the {@link QVToModelCache} class.
     * 
     * @param modelAccess
     *            An {@link IModelAccess} implementation that is used to access the globally
     *            available models such as the PCM models.
     * @throws NullPointerException
     *             In case {@code modelAccess == null}.
     * @see IModelAccess#getBlackboard()
     * @see IModelAccess#getGlobalPCMModel()
     */
    public QVToModelCache(IModelAccess modelAccess) {
        this.cache = new HashMap<>();
        this.modelAccess = Objects.requireNonNull(modelAccess, "modelAccess must not be null.");
        storeBlackboardModels();
    }

    /**
     * Copy constructor.
     * 
     * @param from
     *            The instance to copy.
     * @see #snapshot()
     */
    private QVToModelCache(QVToModelCache from) {
        this.cache = new HashMap<>();
        this.modelAccess = from.modelAccess;
        Objects.requireNonNull(from);
        from.cache.values().stream().flatMap(Collection::stream).forEach(this::storeModel);
    }

    /**
     * Stores the given model in the cache. If a model of the same type (denoted by the meta-model,
     * i.e., the {@link EPackage} that corresponds to the model) is already present, it will be
     * overwritten. <br>
     * In case {@code null} is passed, this method does nothing.
     * 
     * @param modelInstance
     *            An {@link EObject} representing a model.
     */
    public void storeModel(EObject modelInstance) {
        if (modelInstance != null) {
            EPackage metaModel = MODELTYPE_RETRIEVER.doSwitch(modelInstance);
            
            // The following is to circumvent problems of providing EPackages as model instances 
            // to transformations, as the meta model instances are identified using their namespace
            // uri, which is the same for the EPackage instance and the model instance.
            // Simplified: We do not want to transform meta models.
            if (modelInstance.equals(metaModel))
                return;
            
            //Optional.ofNullable(this.namespaceIndex.get(metaModel.getNsURI())
            Optional.ofNullable(this.cache.get(metaModel))
                .orElseGet(() -> 
                {
                    this.cache.put(metaModel, new HashSet<>());
                    return this.cache.get(metaModel);
                }).add(modelInstance);
        }
    }

    /**
     * Stores the model found in the partition of the blackboard that is identified by the given id.
     * If a model of the same type (denoted by the meta-model, i.e., the {@link EPackage} that
     * corresponds to the model) is already present, it will be overwritten.<br>
     * If the given id does not identify a partition, or the requested partition is empty, nothing
     * happens.
     * 
     * @param partitionId
     *            A String which identifies a {@link ResourceSetPartition} of the the global
     *            {@link MDSDBlackboard}.
     * @throws NullPointerException
     *             In case {@code partitionId == null}.
     * @see #storeModel(EObject)
     */
    public final void storeModelFromBlackboardPartition(String partitionId) {
        MDSDBlackboard blackboard = this.modelAccess.getBlackboard();
        if (blackboard.hasPartition(Objects.requireNonNull(partitionId, "partitionId must not be null."))) {
            ResourceSetPartition partition = blackboard.getPartition(partitionId);
            if (partition != null) {
                List<Resource> resources = partition.getResourceSet().getResources();
                // handle case when no model has been specified, i.e., resources is an empty list
                if (!resources.isEmpty()) {
                    EObject currentModel = resources.get(0).getContents().get(0);
                    storeModel(currentModel);
                }
            }
        }
    }

    /**
     * Removes all of the currently stored models which are instances of the meta-model represented by the
     * given ePackage.<br>
     * In case {@code null} is passed, this method does nothing.
     * 
     * @param ePackage
     *            An {@link EPackage} that describes a meta-model.
     */
    public void removeModelOfType(EPackage metaModel) {
        if (metaModel != null) {
            this.cache.remove(metaModel);
        }
    }

    /**
     * Removes the given model from the cache, if present.<br>
     * This method does nothing, if {@code null} is passed or the given model is not cached.
     * 
     * @param model
     *            The {@link EObject} to remove from the cache.
     */
    public void removeModel(EObject model) {
        if (model != null) {
            this.cache.get(MODELTYPE_RETRIEVER.doSwitch(model)).remove(model);
        }
    }

    /**
     * Clears the cache, that is, all models are removed.
     */
    public void clear() {
        this.cache.clear();
    }

    /**
     * Creates a snapshot of the current state of the cache.<br>
     * More precisely, this method creates an instance that contains the same models as this one.
     * 
     * @return A {@link QVToModelCache} which is a snapshot of the current state of this instance.
     */
    public QVToModelCache snapshot() {
        return new QVToModelCache(this);
    }

    /**
     * Gets the currently stored model that is an instance of the meta-model represented by the
     * given ePackage.
     * 
     * @param ePackage
     *            An {@link EPackage} that describes a meta-model.
     * @return The model, contained in an {@link Optional} and represented as an {@link EObject},
     *         that is an instance of the given meta-model, or an empty {@link Optional} if none
     *         could be found.
     * @throws NullPointerException
     *             In case {@code ePackage == null}.
     */
    public Collection<EObject> getModelsByType(EPackage ePackage) {
        String namespace = Objects.requireNonNull(ePackage.getNsURI());
        Collection<EPackage> res =  this.cache.keySet().stream()
                .filter(key -> key.getNsURI().equals(namespace)).collect(Collectors.toList());
        Collection<Set<EObject>> result = res.stream()
                .map(this.cache::get).collect(Collectors.toList());
        return result.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * Gets whether a model of the meta-model represented by the given ePackage is currently in
     * store.
     * 
     * @param ePackage
     *            An {@link EPackage} that describes a meta-model.
     * @return {@code true} if a model of the given type is stored, {@code false} otherwise.
     * @throws NullPointerException
     *             In case {@code ePackage == null}.
     * 
     * @see #getModelsByType(EPackage)
     */
    public boolean containsModelOfType(EPackage ePackage) {
        return this.cache.containsKey(Objects.requireNonNull(ePackage));
    }

    private void storeBlackboardModels() {
        assert this.modelAccess != null;

        storeModel(this.modelAccess.getRuntimeMeasurementModel());
        // now store the all pcm models (we want the root of each model, the root EObject)
        this.modelAccess.getGlobalPCMModel().getResourceSet().getResources().stream().map(Resource::getContents)
                .filter(contents -> !contents.isEmpty() && !isBlacklisted(contents.get(0)))
                .forEach(contents -> storeModel(contents.get(0)));
        // now collect all models that were added to blackboard via extension point
        ExtensionHelper
                .getAttributes(SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
                        SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE,
                        SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_BLACKBOARD_PARTITION_ID_ATTRIBUTE)
                .forEach(this::storeModelFromBlackboardPartition);
    }

    private static boolean isBlacklisted(EObject model) {
        assert model != null;

        return model.eResource().getURI()
                .equals(PreparePCMBlackboardPartitionJob.PCM_PALLADIO_PRIMITIVE_TYPE_REPOSITORY_URI)
                || Arrays.stream(MODEL_ECLASS_BLACKLIST).anyMatch(bannedEClass -> bannedEClass == model.eClass());
    }
}
