package org.palladiosimulator.simulizar.reconfiguration.qvto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
 * @author Florian Rosenthal
 *
 */
public class QVToModelCache {

    // use a map: EPackage, i.e, meta-model serves as key/tag
    private final Map<EPackage, EObject> cache;
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
        this.cache.putAll(from.cache);
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
            // if (this.cache.containsKey(metaModel)) {
            // throw new IllegalArgumentException("Already one instance of meta-model " +
            // metaModel.getName()
            // + " in store.");
            // }
            this.cache.put(metaModel, modelInstance);
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
     * Removes the currently stored model that is an instance of the meta-model represented by the
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
        if (model != null && this.cache.containsValue(model)) {
            removeModelOfType(MODELTYPE_RETRIEVER.doSwitch(model));
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
     * @return The model, represented as an {@link EObject} that is an instance of the given
     *         meta-model, or {@code null} if none could be found.
     * @throws NullPointerException
     *             In case {@code ePackage == null}.
     */
    public Optional<EObject> getModelByType(EPackage ePackage) {
        String namespace = Objects.requireNonNull(ePackage.getNsURI());
        for (EPackage key : this.cache.keySet()) {
            if (key.getNsURI().equals(namespace)) {
                return Optional.of(this.cache.get(key));
            }
        }
        return null;
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
     * @see #getModelByType(EPackage)
     */
    public boolean containsModelOfType(EPackage ePackage) {
        return this.cache.containsKey(Objects.requireNonNull(ePackage));
    }

    private Map<EPackage, EObject> storeBlackboardModels() {
        assert this.modelAccess != null;

        Map<EPackage, EObject> result = new HashMap<>();
        storeModel(this.modelAccess.getRuntimeMeasurementModel());
        for (Resource pcmResource : this.modelAccess.getGlobalPCMModel().getResourceSet().getResources()) {
            // we want the root of the model, the root EObject
            List<EObject> contents = pcmResource.getContents();
            if (!contents.isEmpty() && !isBlacklisted(contents.get(0))) {
                storeModel(contents.get(0));
            }
        }
        // now collect all models that were added to blackboard via extension point
        for (String partitionId : ExtensionHelper.getAttributes(SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_BLACKBOARD_PARTITION_ID_ATTRIBUTE)) {
            storeModelFromBlackboardPartition(partitionId);
        }
        return result;
    }

    private static boolean isBlacklisted(EObject model) {
        if (model.eResource().getURI()
                .equals(PreparePCMBlackboardPartitionJob.PCM_PALLADIO_PRIMITIVE_TYPE_REPOSITORY_URI)) {
            return true;
        }
        EClass eClass = model.eClass();
        for (EClass bannedEClass : MODEL_ECLASS_BLACKLIST) {
            if (eClass == bannedEClass) {
                return true;
            }
        }
        return false;
    }
}
