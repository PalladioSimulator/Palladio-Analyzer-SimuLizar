package org.palladiosimulator.simulizar.extension.impl;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SimuLizarExtensionRegistryImpl<ExtensionType> {
    private final Set<ExtensionType> extensions;
    
    @Inject
    public SimuLizarExtensionRegistryImpl() {
        extensions = new LinkedHashSet<>();
    }

    public void register(ExtensionType extension) {
        extensions.add(extension);
    }
    
    public Set<ExtensionType> getExtensions() {
        return Collections.unmodifiableSet(extensions);
    }

}
