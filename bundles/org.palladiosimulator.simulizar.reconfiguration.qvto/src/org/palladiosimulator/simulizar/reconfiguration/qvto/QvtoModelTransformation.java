package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.PredicateUtils;
import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl;

@SuppressWarnings("restriction")
public class QvtoModelTransformation extends ModelTransformationImpl<OperationalTransformation> {
    private final QVTOTransformationExecutor transformationExecutor;
    private final Collection<TransformationParameterInformation> inParams;
    private final Collection<TransformationParameterInformation> outParams;
    
    private static final Predicate<TransformationParameterInformation> PURE_OUT_PARAM_PREDICATE = new Predicate<TransformationParameterInformation>() {

        @Override
        public boolean evaluate(TransformationParameterInformation transformationParameterInformation) {
            return transformationParameterInformation.isOutParameter();
        }
    };

    private static final Predicate<TransformationParameterInformation> IN_INOUT_PARAM_PREDICATE = PredicateUtils
            .notPredicate(PURE_OUT_PARAM_PREDICATE);

    public QvtoModelTransformation(OperationalTransformation transformation, QVTOTransformationExecutor executor,
            Collection<TransformationParameterInformation> paramInfo) {
        this.modelTransformation = transformation;
        this.transformationExecutor = executor;
        this.inParams = Collections.unmodifiableCollection(CollectionUtils.select(paramInfo, IN_INOUT_PARAM_PREDICATE));
        this.outParams = Collections
                .unmodifiableCollection(CollectionUtils.select(paramInfo, PURE_OUT_PARAM_PREDICATE));
    }
    
    /**
     * Gets the number of parameters the associated QVTo transformation has.
     * 
     * @return A nonnegative integer indicating the number of parameters.
     */
    public int getParameterCount() {
        return this.inParams.size() + this.outParams.size();
    }

    /**
     * Gets all transformation parameters that are marked as 'out' params, i.e., all parameters that
     * are preceded by the out keyword.
     * 
     * @return An UNMODIFIABLE {@link Collection} of the 'out' params, in order of appearance.
     */
    public Collection<TransformationParameterInformation> getPureOutParameters() {
        return this.outParams;
    }

    /**
     * Gets all transformation parameters that are marked as 'in' or 'inout' params, i.e., all
     * parameters that are preceded by either the 'in' or the 'inout' keyword.
     * 
     * @return An UNMODIFIABLE {@link Collection} of the 'in'/'inout' params, in order of
     *         appearance.
     */
    public Collection<TransformationParameterInformation> getInParameters() {
        return this.inParams;
    }

    /**
     * Gets the name of the associated QVTo transformation.
     * 
     * @return A {@link String} that contains the name of the transformation.
     */
    public String getTransformationName() {
        return this.modelTransformation.getName();
    }

    /**
     * Gets the transformation executor that will be used to execute the associated QVT0
     * transformation.
     * 
     * @return The {@link QVTOTransformationExecutor} that will execute the transformation.
     */
    public QVTOTransformationExecutor getTransformationExecutor() {
        return this.transformationExecutor;
    }
    
}
