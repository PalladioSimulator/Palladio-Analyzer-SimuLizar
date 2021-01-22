package org.palladiosimulator.simulizar.di.extension;

import java.util.function.Function;

public class ExtensionContribution<ExtensionType, ExtensionComponentType> {
    private final Class<ExtensionType> cls;
    private final Function<ExtensionComponentType, ExtensionType> supplier;

    public ExtensionContribution(Class<ExtensionType> cls, Function<ExtensionComponentType, ExtensionType> supplier) {
        this.cls = cls;
        this.supplier = supplier;
    }
    
    public Class<ExtensionType> getExtensionType() {
        return cls;
    }
    
    public ExtensionType contribute(ExtensionComponentType component) {
        return supplier.apply(component);
    }
    
    public static <ET, ECT> ExtensionContribution<ET, ECT> of(Class<ET> extensionType, Function<ECT, ET> supplier) {
        return new ExtensionContribution<ET, ECT>(extensionType, supplier);
    }
    

}
