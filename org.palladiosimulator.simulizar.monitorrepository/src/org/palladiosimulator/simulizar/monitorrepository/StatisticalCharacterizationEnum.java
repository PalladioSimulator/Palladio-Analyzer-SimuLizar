/**
 */
package org.palladiosimulator.simulizar.monitorrepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Statistical Characterization Enum</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getStatisticalCharacterizationEnum()
 * @model
 * @generated
 */
public enum StatisticalCharacterizationEnum implements Enumerator {
    /**
     * The '<em><b>None</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(0, "None", "None"),

    /**
     * The '<em><b>Median</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #MEDIAN_VALUE
     * @generated
     * @ordered
     */
    MEDIAN(1, "Median", "Median"),

    /**
     * The '<em><b>Arithmetic Mean</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #ARITHMETIC_MEAN_VALUE
     * @generated
     * @ordered
     */
    ARITHMETIC_MEAN(2, "ArithmeticMean", "ArithmeticMean"),

    /**
     * The '<em><b>Geometric Mean</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #GEOMETRIC_MEAN_VALUE
     * @generated
     * @ordered
     */
    GEOMETRIC_MEAN(3, "GeometricMean", "GeometricMean"),

    /**
     * The '<em><b>Harmonic Mean</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #HARMONIC_MEAN_VALUE
     * @generated
     * @ordered
     */
    HARMONIC_MEAN(4, "HarmonicMean", "HarmonicMean");

    /**
     * The '<em><b>None</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>None</b></em>' literal object isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NONE
     * @model name="None"
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 0;

    /**
     * The '<em><b>Median</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Median</b></em>' literal object isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #MEDIAN
     * @model name="Median"
     * @generated
     * @ordered
     */
    public static final int MEDIAN_VALUE = 1;

    /**
     * The '<em><b>Arithmetic Mean</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Arithmetic Mean</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #ARITHMETIC_MEAN
     * @model name="ArithmeticMean"
     * @generated
     * @ordered
     */
    public static final int ARITHMETIC_MEAN_VALUE = 2;

    /**
     * The '<em><b>Geometric Mean</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Geometric Mean</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #GEOMETRIC_MEAN
     * @model name="GeometricMean"
     * @generated
     * @ordered
     */
    public static final int GEOMETRIC_MEAN_VALUE = 3;

    /**
     * The '<em><b>Harmonic Mean</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Harmonic Mean</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #HARMONIC_MEAN
     * @model name="HarmonicMean"
     * @generated
     * @ordered
     */
    public static final int HARMONIC_MEAN_VALUE = 4;

    /**
     * An array of all the '<em><b>Statistical Characterization Enum</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final StatisticalCharacterizationEnum[] VALUES_ARRAY = new StatisticalCharacterizationEnum[] { NONE,
        MEDIAN, ARITHMETIC_MEAN, GEOMETRIC_MEAN, HARMONIC_MEAN, };

    /**
     * A public read-only list of all the '<em><b>Statistical Characterization Enum</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<StatisticalCharacterizationEnum> VALUES = Collections.unmodifiableList(Arrays
            .asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Statistical Characterization Enum</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static StatisticalCharacterizationEnum get(final String literal) {
        for (final StatisticalCharacterizationEnum result : VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Statistical Characterization Enum</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static StatisticalCharacterizationEnum getByName(final String name) {
        for (final StatisticalCharacterizationEnum result : VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Statistical Characterization Enum</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static StatisticalCharacterizationEnum get(final int value) {
        switch (value) {
        case NONE_VALUE:
            return NONE;
        case MEDIAN_VALUE:
            return MEDIAN;
        case ARITHMETIC_MEAN_VALUE:
            return ARITHMETIC_MEAN;
        case GEOMETRIC_MEAN_VALUE:
            return GEOMETRIC_MEAN;
        case HARMONIC_MEAN_VALUE:
            return HARMONIC_MEAN;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private StatisticalCharacterizationEnum(final int value, final String name, final String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLiteral() {
        return this.literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        return this.literal;
    }

} // StatisticalCharacterizationEnum
