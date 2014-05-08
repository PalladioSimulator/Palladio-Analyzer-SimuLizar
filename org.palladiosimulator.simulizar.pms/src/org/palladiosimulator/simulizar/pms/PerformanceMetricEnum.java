/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Performance Metric Enum</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see org.palladiosimulator.simulizar.pms.PmsPackage#getPerformanceMetricEnum()
 * @model
 * @generated
 */
public enum PerformanceMetricEnum implements Enumerator {
    /**
     * The '<em><b>Waiting Time</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #WAITING_TIME_VALUE
     * @generated
     * @ordered
     */
    WAITING_TIME(0, "WaitingTime", "WaitingTime"),

    /**
     * The '<em><b>Response Time</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #RESPONSE_TIME_VALUE
     * @generated
     * @ordered
     */
    RESPONSE_TIME(1, "ResponseTime", "ResponseTime"),

    /**
     * The '<em><b>Utilization</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #UTILIZATION_VALUE
     * @generated
     * @ordered
     */
    UTILIZATION(2, "Utilization", "Utilization"),

    /**
     * The '<em><b>Arrival Rate</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #ARRIVAL_RATE_VALUE
     * @generated
     * @ordered
     */
    ARRIVAL_RATE(3, "ArrivalRate", "ArrivalRate"),

    /**
     * The '<em><b>Throughput</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #THROUGHPUT_VALUE
     * @generated
     * @ordered
     */
    THROUGHPUT(4, "Throughput", "Throughput");

    /**
     * The '<em><b>Waiting Time</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Waiting Time</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #WAITING_TIME
     * @model name="WaitingTime"
     * @generated
     * @ordered
     */
    public static final int WAITING_TIME_VALUE = 0;

    /**
     * The '<em><b>Response Time</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Response Time</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #RESPONSE_TIME
     * @model name="ResponseTime"
     * @generated
     * @ordered
     */
    public static final int RESPONSE_TIME_VALUE = 1;

    /**
     * The '<em><b>Utilization</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Utilization</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #UTILIZATION
     * @model name="Utilization"
     * @generated
     * @ordered
     */
    public static final int UTILIZATION_VALUE = 2;

    /**
     * The '<em><b>Arrival Rate</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Arrival Rate</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ARRIVAL_RATE
     * @model name="ArrivalRate"
     * @generated
     * @ordered
     */
    public static final int ARRIVAL_RATE_VALUE = 3;

    /**
     * The '<em><b>Throughput</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Throughput</b></em>' literal object isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #THROUGHPUT
     * @model name="Throughput"
     * @generated
     * @ordered
     */
    public static final int THROUGHPUT_VALUE = 4;

    /**
     * An array of all the '<em><b>Performance Metric Enum</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final PerformanceMetricEnum[] VALUES_ARRAY = new PerformanceMetricEnum[] { WAITING_TIME,
            RESPONSE_TIME, UTILIZATION, ARRIVAL_RATE, THROUGHPUT, };

    /**
     * A public read-only list of all the '<em><b>Performance Metric Enum</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<PerformanceMetricEnum> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Performance Metric Enum</b></em>' literal with the specified literal
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static PerformanceMetricEnum get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PerformanceMetricEnum result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Performance Metric Enum</b></em>' literal with the specified name. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static PerformanceMetricEnum getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PerformanceMetricEnum result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Performance Metric Enum</b></em>' literal with the specified integer
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static PerformanceMetricEnum get(int value) {
        switch (value) {
        case WAITING_TIME_VALUE:
            return WAITING_TIME;
        case RESPONSE_TIME_VALUE:
            return RESPONSE_TIME;
        case UTILIZATION_VALUE:
            return UTILIZATION;
        case ARRIVAL_RATE_VALUE:
            return ARRIVAL_RATE;
        case THROUGHPUT_VALUE:
            return THROUGHPUT;
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
    private PerformanceMetricEnum(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // PerformanceMetricEnum
