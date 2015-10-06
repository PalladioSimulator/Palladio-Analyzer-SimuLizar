package org.palladiosimulator.simulizar.reconfiguration.qvto.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter;
import org.eclipse.m2m.internal.qvt.oml.expressions.ModelType;
import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.eclipse.m2m.internal.qvt.oml.expressions.util.ExpressionsSwitch;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;

/**
 * Factory class to create {@link TransformationData} objects related to QVTo transformations.
 * 
 * @see TransformationParameterInformation
 * 
 * @author Florian Rosenthal
 *
 */
@SuppressWarnings("restriction")
class TransformationDataFactory {
    private final ResourceSet resourceSet;

    /**
     * Initializes a new instance of the {@link TransformationDataFactory} class.
     */
    TransformationDataFactory() {
        this.resourceSet = new ResourceSetImpl();
    }

    private static final ExpressionsSwitch<EPackage> PARAM_META_MODEL_SWITCH = new ExpressionsSwitch<EPackage>() {

        @Override
        public EPackage caseModelType(ModelType modeltype) {
            return modeltype.getMetamodel().get(0);
        }
    };

    private static final ExpressionsSwitch<OperationalTransformation> OPERATIONAL_TRANSFORMATION_SWITCH = new ExpressionsSwitch<OperationalTransformation>() {

        @Override
        public OperationalTransformation caseOperationalTransformation(OperationalTransformation transformation) {
            return transformation;
        }
    };

    /**
     * Factory method the create {@link TransformationData} for the QVTo transformation specified by
     * the given URI.
     * 
     * @param transformationUri
     *            A {@link URI} that points to a QVTo transformation.
     * @return A {@link TransformationData} instance that encapsulates all data required to deal
     *         with the transformation during Simulizar runs.
     * @throws IllegalArgumentException
     *             In case the given URI does not point to a {@link QVTo} transformation.
     * @throws NullPointerException
     *             In case the given {@code transformationURI} is {@code null}.
     */
    TransformationData createTransformationData(URI transformationUri) {
        // the EObject transformation should be the first in in the content list
        Resource transformationResource = this.resourceSet.getResource(Objects.requireNonNull(transformationUri), true);
        OperationalTransformation transformation = null;
        if (!transformationResource.getContents().isEmpty()) {
            transformation = OPERATIONAL_TRANSFORMATION_SWITCH.doSwitch(transformationResource.getContents().get(0));
        }
        if (transformation == null) {
            throw new IllegalArgumentException(
                    "OperationalTransformation instance could not be retrieved from resource contents.");
        }
        return new TransformationData(transformation, new TransformationExecutor(transformationUri),
                createTransformationParameterInformation(transformation));
    }

    private Collection<TransformationParameterInformation> createTransformationParameterInformation(
            OperationalTransformation transformation) {
        assert transformation != null;

        Collection<ModelParameter> parameters = transformation.getModelParameter();
        List<TransformationParameterInformation> result = new ArrayList<>(parameters.size());
        int index = 0;
        for (ModelParameter parameter : parameters) {
            result.add(new TransformationParameterInformation(PARAM_META_MODEL_SWITCH.doSwitch(parameter.getType()),
                    parameter.getKind(), index++, parameter.getName()));
        }
        return result;
    }
}
