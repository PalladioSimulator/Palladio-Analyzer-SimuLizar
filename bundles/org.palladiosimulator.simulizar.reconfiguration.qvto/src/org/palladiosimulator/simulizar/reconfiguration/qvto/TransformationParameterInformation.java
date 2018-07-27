package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.m2m.internal.qvt.oml.expressions.DirectionKind;

/**
 * Convenience class to store required information (type, kind, index and name) about parameters of
 * a QVTo transformation
 * 
 * @see TransformationData
 * 
 * @author Florian Rosenthal
 *
 */
@SuppressWarnings("restriction")
public final class TransformationParameterInformation {

    private final EPackage parameterType;
    private final DirectionKind parameterDirectionKind;
    private final int parameterIndex;
    private final String parameterName;

    private static final String FORMAT_STRING = "param: index = %d, name= %s, type = %s, direction = %s";

    public TransformationParameterInformation(EPackage paramType, DirectionKind paramDirectionKind, int paramIndex,
            String paramName) {
        this.parameterType = paramType;
        this.parameterDirectionKind = paramDirectionKind;
        this.parameterIndex = paramIndex;
        this.parameterName = paramName;
    }

    /**
     * Gets the type of the parameter, i.e., its corresponding meta-model package.
     * 
     * @return The {@link EPackage} which denotes the type of the transformation parameter.
     */
    public EPackage getParameterType() {
        return this.parameterType;
    }

    private DirectionKind getParameterDirectionKind() {
        return this.parameterDirectionKind;
    }

    /**
     * Indicates whether the parameter is marked with the 'out' keyword, i.e, has implicit return
     * type semantics.
     * 
     * @return {@code true} in case the parameter is an 'out' parameter, {@code false} otherwise.
     */
    public boolean isOutParameter() {
        return getParameterDirectionKind() == DirectionKind.OUT;
    }

    /**
     * Gets the index of the parameter in the corresponding QVTo transformation. <br>
     * Note, that the first parameter has index {@code 0} and so forth.
     * 
     * @return The index of the parameter, expressed by a nonnegative integer.
     */
    public int getParameterIndex() {
        return this.parameterIndex;
    }

    /**
     * Gets the name of the parameter.
     * 
     * @return A {@link String} that contains the parameter's name.
     */
    public String getParameterName() {
        return this.parameterName;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.parameterIndex, this.parameterName, this.parameterType.getName(),
                getParameterDirectionKind().getName());
    }
}
