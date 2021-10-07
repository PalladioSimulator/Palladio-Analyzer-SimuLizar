package org.palladiosimulator.simulizar;

public class ConstantsContainer {
    private ConstantsContainer() {}
    
    /**
     * Parameters with implicit parameter prefix are always passed as input parameters to a newly created child stack.
     * 
     * Similarly, implicit return parameters are always mapped to the same key in the parent stack frame.
     */
    public static final String IMPLICIT_PARAMETER_PREFIX = "__implicit";
    public static final String IMPLICIT_IN_PARAMETER_PREFIX = IMPLICIT_PARAMETER_PREFIX + "_in__";
    public static final String IMPLICIT_OUT_PARAMETER_PREFIX = IMPLICIT_PARAMETER_PREFIX + "_out__";
    public static final String IMPLICIT_INOUT_PARAMETER_PREFIX = IMPLICIT_PARAMETER_PREFIX + "_inout__";
    

}
