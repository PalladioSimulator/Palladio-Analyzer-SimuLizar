package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.m2m.internal.qvt.oml.expressions.DirectionKind;
import org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter;
import org.eclipse.m2m.internal.qvt.oml.expressions.ModelType;
import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.eclipse.m2m.internal.qvt.oml.expressions.util.ExpressionsSwitch;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementSwitch;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * QVTo executor helper class that supports executing QVTo reconfiguration rules.
 * 
 * @author Matthias Becker
 * @author Sebastian Lehrig
 * @author Florian Rosenthal
 */
@SuppressWarnings("restriction")
public class QVTOExecutor {

    private final IModelAccess modelAccess;

    private static final String QVTO_FILE_EXTENSION = ".qvto";
    private static final Logger LOGGER = Logger.getLogger(QVTOExecutor.class);
    private final List<TransformationData> qvtoRules;
    // store mapping model type -> model instance
    private final Map<EPackage, EObject> availableModels;

    /**
     * Constructor of the QVTOExecutor
     * 
     * @param modelAccess
     *            ModelAccessFactory giving access to PCM and RuntimeMeasurment models
     * @param configuration
     *            Simulation configuration
     */
    public QVTOExecutor(final IModelAccess modelAccess, final SimuLizarWorkflowConfiguration configuration) {
        super();
        this.modelAccess = modelAccess;
        this.qvtoRules = new ArrayList<>();
        final URI[] qvtoFiles = getQvtoFiles(configuration.getReconfigurationRulesFolder());
        if (qvtoFiles.length > 0) {
            initializeTransformationExecutors(qvtoFiles);
        }
        this.availableModels = collectBlackboardModels();
    }

    private Map<EPackage, EObject> collectBlackboardModels() {
        final Map<EPackage, EObject> result = new HashMap<>();
        final MDSDBlackboard blackboard = this.modelAccess.getBlackboard();
        EObject currentModel = this.modelAccess.getRuntimeMeasurementModel();
        if (currentModel != null) {
            result.put(MODELTYPE_RETRIEVER.doSwitch(currentModel), currentModel);
        }
        currentModel = this.modelAccess.getUsageEvolutionModel();
        if (currentModel != null) {
            result.put(MODELTYPE_RETRIEVER.doSwitch(currentModel), currentModel);
        }
        for (final Resource pcmResource : this.modelAccess.getGlobalPCMModel().getResourceSet().getResources()) {
            // we want the root of the model, the root EObject
            final List<EObject> contents = pcmResource.getContents();
            if (!contents.isEmpty()) {
                currentModel = contents.get(0);
                if (currentModel != null) {
                    result.put(MODELTYPE_RETRIEVER.doSwitch(currentModel), currentModel);
                }
            }
        }
        // now collect all models that were added to blackboard via extension point
        for (final String partitionId : ExtensionHelper.getAttributes(SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_BLACKBOARD_PARTITION_ID_ATTRIBUTE)) {
            final ResourceSetPartition partition = blackboard.getPartition(partitionId);
            if (partition != null) {
                final ResourceSet resourceSet = partition.getResourceSet();
                final List<Resource> resources = resourceSet.getResources();
                // handle case when no model has been specified, i.e., resources is an empty list
                if (!resources.isEmpty()) {
                    currentModel = resources.get(0).getContents().get(0);
                    if (currentModel != null) {
                        result.put(MODELTYPE_RETRIEVER.doSwitch(currentModel), currentModel);
                    }
                }
            }
        }
        return result;
    }

    private final static EcoreSwitch<EPackage> MODELTYPE_RETRIEVER = new EcoreSwitch<EPackage>() {

        @Override
        public EPackage caseEPackage(final EPackage ePackage) {
            // we found the model type, just return
            return ePackage;
        }

        @Override
        public EPackage caseEClass(final EClass eClass) {
            // from meta class to containing object, should be an EPackage
            return doSwitch(eClass.eContainer());
        }

        @Override
        public EPackage defaultCase(final EObject eObject) {
            // go on with the meta class
            return doSwitch(eObject.eClass());
        }
    };

    /**
     * Executes all QVTo rules found in the configured reconfiguration rule folder.
     * 
     * @param monitoredElement
     *            the monitored PCM model element.
     * @return true if at least one reconfiguration was executed successfully
     */
    public boolean executeRules(final EObject monitoredElement) {
        boolean result = false;
        for (final TransformationData transformationData : this.qvtoRules) {
            result |= executeTransformation(transformationData);
        }
        return result;
    }

    /**
     * Gets the QVTO files within the specified path.
     * 
     * @param path
     *            Path to reconfiguration rules.
     * @return The QVTO files within the given path. Returns an empty array in case no files are
     *         found.
     */
    private URI[] getQvtoFiles(final String path) {
        if (path.equals("")) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No path to QVTo rules given.");
            }
            return new URI[0];
        }

        final URI[] uris = FileHelper.getURIs(path, QVTO_FILE_EXTENSION);

        if (uris.length == 0) {
            LOGGER.info("No QVTo rules found, QVTo reconfigurations disabled.");
        }

        return uris;
    }

    private static final ExpressionsSwitch<OperationalTransformation> OPERATIONAL_TRANSFORMATION_SWITCH = new ExpressionsSwitch<OperationalTransformation>() {

        @Override
        public OperationalTransformation caseOperationalTransformation(final OperationalTransformation transformation) {
            return transformation;
        }
    };

    private void initializeTransformationExecutors(final URI[] transformationUris) {
        assert transformationUris != null;
        final ResourceSet resourceSet = new ResourceSetImpl();
        for (final URI transformationUri : transformationUris) {
            LOGGER.info("Found reconfiguration rule \"" + transformationUris + "\"");
            // the EObject transformation should be the first in in the content list
            final Resource transformationResource = resourceSet.getResource(transformationUri, true);
            final OperationalTransformation transformation = OPERATIONAL_TRANSFORMATION_SWITCH
                    .doSwitch(transformationResource.getContents().get(0));
            if (transformation == null) {
                throw new IllegalStateException(
                        "OperationalTransformation instance could not be retrieved from resource contents.");
            }
            this.qvtoRules.add(new TransformationData(transformation, new TransformationExecutor(transformationUri),
                    retrieveTransformationParameterInformation(transformation)));
        }
    }

    private static final ExpressionsSwitch<EPackage> PARAM_META_MODEL_SWITCH = new ExpressionsSwitch<EPackage>() {

        @Override
        public EPackage caseModelType(final ModelType modeltype) {
            return modeltype.getMetamodel().get(0);
        }
    };

    private Collection<TransformationParameterInformation> retrieveTransformationParameterInformation(
            final OperationalTransformation transformation) {
        assert transformation != null;
        final List<ModelParameter> parameters = transformation.getModelParameter();
        final List<TransformationParameterInformation> result = new ArrayList<>(parameters.size());
        int index = 0;
        for (final ModelParameter parameter : parameters) {
            result.add(new TransformationParameterInformation(PARAM_META_MODEL_SWITCH.doSwitch(parameter.getType()),
                    parameter.getKind(), index++));
        }
        return result;
    }

    private EObject getAvailableModelByEPackage(final EPackage ePackage) {
        assert ePackage != null;

        for (final EPackage modelPackage : this.availableModels.keySet()) {
            // use namespace URIs as criterion for equality
            if (modelPackage.getNsURI().equals(ePackage.getNsURI())) {
                return this.availableModels.get(modelPackage);
            }
        }
        return null;
    }

    // this switch encapsulates the special treatment of the RuntimeMeasurementModel
    // to incorporate other special cases, use nested switches within the 'defaultCase(EObject)'
    // method
    private static final RuntimeMeasurementSwitch<ModelExtent> CREATE_NON_EMPTY_MODEL_EXTENT_SWITCH = new RuntimeMeasurementSwitch<ModelExtent>() {

        // special treatment for RuntimeMeasurementModel: directly pass contained measurements to
        // model extent
        @Override
        public ModelExtent caseRuntimeMeasurementModel(final RuntimeMeasurementModel runtimeMeasurementModel) {
            return new BasicModelExtent(runtimeMeasurementModel.getMeasurements());
        }

        // default case to handle all other models
        // to incorporate other special treatments, call nested switches inside this method
        @Override
        public ModelExtent defaultCase(final EObject model) {
            final BasicModelExtent result = new BasicModelExtent();
            result.add(model);
            return result;
        }
    };

    private boolean executeTransformation(final TransformationData transformationData) {
        assert transformationData != null && transformationData.getTransformationExecutor() != null;

        final ModelExtent[] modelExtents = new ModelExtent[transformationData.getParameterCount()];
        for (final TransformationParameterInformation paramInfo : transformationData.getParameterInformation()) {
            if (paramInfo.getParameterDirectionKind() != DirectionKind.OUT) {
                // fill with one the corresponding available model
                final EObject sourceModel = getAvailableModelByEPackage(paramInfo.getParameterType());
                if (sourceModel == null) {
                    throw new IllegalStateException("No model available for " + (paramInfo.getParameterIndex() + 1)
                            + ". parameter of QVTo transformation '"
                            + transformationData.getAssociatedTransformation().getName() + "'");
                }
                modelExtents[paramInfo.getParameterIndex()] = CREATE_NON_EMPTY_MODEL_EXTENT_SWITCH
                        .doSwitch(sourceModel);
            } else {
                // use empty model extent
                modelExtents[paramInfo.getParameterIndex()] = new BasicModelExtent();
            }
        }
        // setup the execution environment details ->
        // configuration properties, LOGGER, monitor object etc.
        final ExecutionContextImpl executionContext = new ExecutionContextImpl();
        // context.setConfigProperty("keepModeling", true);
        executionContext.setLog(new QVTOReconfigurationLogger(QVTOExecutor.class));
        // now run the transformation assigned to the executor with the given
        // input and output and execution context
        final ExecutionDiagnostic result = transformationData.getTransformationExecutor().execute(executionContext,
                modelExtents);
        // check the result for success
        if (result.getSeverity() == Diagnostic.OK) {
            LOGGER.log(Level.DEBUG, "Rule successfully executed with message: " + result.getMessage());
            return true;
        } else {
            LOGGER.log(Level.WARN, "Rule application failed with message: " + result.getMessage());
            return false;
        }
    }

    /**
     * Convenience class to store required information about a QVTo transformation:
     * <ul>
     * <li>the corresponding {@link OperationalTransformation} model object</li>
     * <li>the {@link TransformationExecutor} that will execute the transformation</li>
     * <li>information about its parameters in terms of a collection of
     * {@link TransformationParameterInformation}</li>
     * </ul>
     * 
     * @author Florian Rosenthal
     *
     */
    private static class TransformationData {

        private final OperationalTransformation associatedTransformation;
        private final TransformationExecutor transformationExecutor;
        private final Collection<TransformationParameterInformation> parameterInformation;

        private TransformationData(final OperationalTransformation transformation,
                final TransformationExecutor executor, final Collection<TransformationParameterInformation> paramInfo) {
            this.associatedTransformation = transformation;
            this.transformationExecutor = executor;
            this.parameterInformation = paramInfo;
        }

        private int getParameterCount() {
            return this.parameterInformation.size();
        }

        private Iterable<TransformationParameterInformation> getParameterInformation() {
            return this.parameterInformation;
        }

        private OperationalTransformation getAssociatedTransformation() {
            return this.associatedTransformation;
        }

        private TransformationExecutor getTransformationExecutor() {
            return this.transformationExecutor;
        }
    }

    /**
     * Convenience class to store required information (type, kind and index) about parameters of a
     * QVTo transformation
     * 
     * @author Florian Rosenthal
     *
     */
    private static class TransformationParameterInformation {

        private final EPackage parameterType;
        private final DirectionKind parameterDirectionKind;
        private final int parameterIndex;

        private static final String FORMAT_STRING = "param: index = %d, type = %s, direction = %s";

        private TransformationParameterInformation(final EPackage paramType, final DirectionKind paramDirectionKind,
                final int paramIndex) {
            this.parameterType = paramType;
            this.parameterDirectionKind = paramDirectionKind;
            this.parameterIndex = paramIndex;
        }

        private EPackage getParameterType() {
            return this.parameterType;
        }

        private DirectionKind getParameterDirectionKind() {
            return this.parameterDirectionKind;
        }

        private int getParameterIndex() {
            return this.parameterIndex;
        }

        @Override
        public String toString() {
            return String.format(FORMAT_STRING, this.parameterIndex, this.parameterType.getName(),
                    this.parameterDirectionKind.getName());
        }
    }
}
