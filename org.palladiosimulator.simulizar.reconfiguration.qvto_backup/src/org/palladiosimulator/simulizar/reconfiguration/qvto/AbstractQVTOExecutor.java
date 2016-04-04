package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContext;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.eclipse.m2m.qvt.oml.util.Log;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementSwitch;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.QvtoModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.TransformationParameterInformation;
/**
 * This class is intended to be the base of all classes that wish to execute QVTo transformations.
 * The set of transformations that can be executed are passed to each instance upon construction in
 * terms of a {@link TransformationCache}, as well as the set of model that can serve as
 * transformation parameters.<br>
 * Subclasses can re-implement the steps of the {@link #executeTransformation(TransformationData)}
 * template method to adapt the process of execution.
 * 
 * @author Florian Rosenthal
 *
 */
public abstract class AbstractQVTOExecutor {

	private static final Logger LOGGER = Logger.getLogger(AbstractQVTOExecutor.class);
    // store mapping model type -> model instance
    private final QVToModelCache availableModels;
    private final ModelTransformationCache transformationCache;

 // this switch encapsulates the special treatment of the RuntimeMeasurementModel
    // to incorporate other special cases, use nested switches within the 'defaultCase(EObject)'
    // method
    private static final RuntimeMeasurementSwitch<Collection<EObject>> CREATE_NON_EMPTY_MODEL_ELEMENTS_SWITCH = new RuntimeMeasurementSwitch<Collection<EObject>>() {

        // special treatment for RuntimeMeasurementModel: directly pass contained measurements to
        // model extent
        @Override
        public Collection<EObject> caseRuntimeMeasurementModel(RuntimeMeasurementModel runtimeMeasurementModel) {
            return new ArrayList<EObject>(runtimeMeasurementModel.getMeasurements());
        }

        // default case to handle all other models
        // to incorporate other special treatments, call nested switches inside this method
        @Override
        public Collection<EObject> defaultCase(EObject model) {
            return Collections.singletonList(model);
        }
    };
    
    /**
     * Accumulates the model elements and finally creates a basic model extent
     */
    private static final Collector<EObject, List<EObject>, ModelExtent> BASIC_MODEL_EXTENT_COLLECTOR = 
            Collector.of(ArrayList<EObject>::new, 
                         (List<EObject> acc, EObject t) -> acc.add(t), 
                         (List<EObject> l, List<EObject> r) -> {l.addAll(r); return l;},
                         BasicModelExtent::new);

    /**
     * Initializes a new instance of the {@link AbstractQVTOExecutor} class with the given
     * parameters.
     * 
     * @param knownTransformations
     *            An {@link TransformationCache} which contains all transformation that can be
     *            executed by this instance, might be empty.
     * @param knownModels
     *            A {@link QVToModelCache} that contains all models that can serve as a
     *            transformation parameter.
     * @throws NullPointerException
     *             If either parameter is {@code null}.
     */
    protected AbstractQVTOExecutor(ModelTransformationCache knownTransformations, QVToModelCache knownModels) {
    	this.transformationCache = Objects.requireNonNull(knownTransformations);
        this.availableModels = Objects.requireNonNull(knownModels);
    }
    
    /**
     * Gets the underlying transformation cache used by this instance.
     * 
     * @return The {@link TransformationCache} which contains all transformations that can be
     *         executed by this instance.
     */
    protected ModelTransformationCache getAvailableTransformations() {
        return this.transformationCache;
    }

    /**
     * Gets the underlying model cache used by this instance.
     * 
     * @return The {@link QVToModelCache} which contains all models that can serve as parameters.
     */
    protected QVToModelCache getAvailableModels() {
        return this.availableModels;
    }
    
        /**
     * Attempts to execute the transformation that corresponds to the given URI.
     * 
     * @param transformationURI
     *            An {@link URI} that points to a QVTo transformation.
     * @return A boolean that indicates whether the transformation succeeded.
     * @throws NullPointerException
     *             In case the given URI is {@code null}.
     * @throws IllegalArgumentException
     *             In case the transformation is not known, i.e., not stored in the internal cache.
     * @see #executeTransformation(TransformationData)
     * @see #AbstractQVTOExecutor(TransformationCache, QVToModelCache)
     */
    public boolean executeTransformation(URI transformationURI) {
        Optional<QvtoModelTransformation> data = this.transformationCache.get(Objects.requireNonNull(transformationURI));
        return executeTransformation(data.orElseThrow(
                () -> new IllegalArgumentException("Given transformation not present in transformation cache.")));
    }
    
    /**
     * Template method to execute a QVTo transformation. Within this method, the following
     * (primitive) steps are conducted:
     * <ol>
     * <li>The required model {@link ModelExtent ModelExtents} are created:
     * {@link #setupModelExtents(TransformationData)}</li>
     * <li>The {@link ExecutionContext} is setup: {@link #setupExecutionContext()}</li>
     * <li>The transformation is executed:
     * {@link #doExecution(TransformationExecutor, ExecutionContext, ModelExtent[])}</li>
     * <li>The {@link ExecutionDiagnostic} that describes the execution result is processed:
     * {@link #handleExecutionResult(ExecutionDiagnostic)}</li>
     * </ol>
     * <br>
     * Note, that all of the steps are implemented by this class, but are open to re-implementation
     * by subclasses (apart from the execution step).
     * 
     * @param transformationData
     *            The {@link TransformationData} which describes the transformation to be executed.
     * @return The result of the last step, i.e., a boolean that indicates whether the
     *         transformation succeeded.
     * @throws NullPointerException
     *             In case {@code transformationData == null}
     */
    public final boolean executeTransformation(QvtoModelTransformation modelTransformation) {
        ModelExtent[] modelExtents = setupModelExtents(Objects.requireNonNull(modelTransformation));
        ExecutionContext executionContext = setupExecutionContext();
        // now run the transformation assigned to the executor with the given
        // input and output and execution context
        ExecutionDiagnostic result = doExecution(modelTransformation, executionContext, modelExtents);
        // check the result for success
        return handleExecutionResult(result);
    }

    /**
     * Executes a transformation.
     * 
     * @param data
     *            The {@link TransformationData} that represents the transformation to execute.
     * @param context
     *            The {@link ExecutionContext} to use for execution, result of
     *            {@link #setupExecutionContext()}
     * @param params
     *            The {@link ModelExtent}s that hold the parameters of the transformation, result of
     *            {@link #setupModelExtents(TransformationData)}.
     * @return An {@link ExecutionDiagnostic} which indicates the execution result status.
     * @see #executeTransformation(TransformationData)
     */
    protected final ExecutionDiagnostic doExecution(QvtoModelTransformation modelTransformation, ExecutionContext context,
            ModelExtent[] params) {
        return modelTransformation.getTransformationExecutor().execute(context, params);
    }

    /**
     * Last step of the {@link #executeTransformation(TransformationData)} template method.
     * Processes the result status of the execution and transforms it into a boolean value.
     * 
     * @param executionResult
     * @return {@code true} if the execution of the transformation is considered successful,
     *         {@code false} otherwise.
     * @see #doExecution(TransformationData, ExecutionContext, ModelExtent[])
     */
    protected boolean handleExecutionResult(ExecutionDiagnostic executionResult) {
        if (executionResult.getSeverity() == Diagnostic.OK) {
            LOGGER.log(Level.DEBUG, "Rule successfully executed with message: " + executionResult.getMessage());
            return true;
        } else {
            LOGGER.log(Level.WARN, "Rule application failed with message: " + executionResult.getMessage());
            return false;
        }
    }

    /**
     * This method is called prior to
     * {@link #doExecution(TransformationData, ExecutionContext, ModelExtent[])} within the
     * {@link #executeTransformation(TransformationData)} template method. It creates the execution
     * context to be used for execution of the transformation. In particular, {@link #createLog()}
     * is called to obtain the {@link Log} to be in use.
     * 
     * @return A fully-fledged {@link ExecutionContext}.
     * @see #doExecution(TransformationData, ExecutionContext, ModelExtent[])
     */
    protected ExecutionContext setupExecutionContext() {
        // setup the execution environment details ->
        // configuration properties, LOGGER, monitor object etc.
        ExecutionContextImpl result = new ExecutionContextImpl();
        // result.setConfigProperty("keepModeling", true);
        result.setLog(createLog());
        return result;
    }

    /**
     * Creates the Log that shall be used during execution of the transformation. This method is
     * called within {@link #setupExecutionContext()}.
     * 
     * @return The {@link Log} to use during the execution of the transformation.<br>
     *         This default implementation always returns
     *         {@code new QVTOReconfigurationLogger(getClass())}.
     */
    protected Log createLog() {
        return new QVTOReconfigurationLogger(getClass());
    }

    /**
     * First step of the {@link #executeTransformation(TransformationData)} template method.
     * Examines the required transformation parameters and creates appropriate model extents.
     * 
     * @param modelTransformation
     *            The {@link TransformationData} that represents the transformation to be executed.
     * @return An array of {@link ModelExtent ModelExtents}, one for each parameter, in order of
     *         appearance.
     * @throws IllegalStateException
     *             In case no fitting model could be found to create a model extent for an 'in' or
     *             'inout' parameter.
     * @see #doExecution(TransformationData, ExecutionContext, ModelExtent[])
     */
    protected ModelExtent[] setupModelExtents(QvtoModelTransformation transformation) {
        assert transformation != null && transformation.getTransformationExecutor() != null;

        ModelExtent[] modelExtents = new ModelExtent[transformation.getParameterCount()];
        // prepare the in/inout params first
        for (TransformationParameterInformation inParams : transformation.getInParameters()) {
            Collection<EObject> sourceModel = this.availableModels.getModelsByType(inParams.getParameterType());
            if (sourceModel.size() < 1) {
                throw new IllegalStateException("No model in QVTo model cache for "
                        + (inParams.getParameterIndex() + 1) + ". parameter of transformation '"
                        + transformation.getTransformationName() + "'");
            }
            modelExtents[inParams.getParameterIndex()] = sourceModel.stream()
                    .map(CREATE_NON_EMPTY_MODEL_ELEMENTS_SWITCH::doSwitch)
                    .flatMap(Collection::stream)
                    .collect(BASIC_MODEL_EXTENT_COLLECTOR);
        }
        // now the pure out params, they need empty model extents
        transformation.getPureOutParameters().stream()
                .mapToInt(TransformationParameterInformation::getParameterIndex)
                .forEach(index -> modelExtents[index] = new BasicModelExtent());

        return modelExtents;
    }
}
