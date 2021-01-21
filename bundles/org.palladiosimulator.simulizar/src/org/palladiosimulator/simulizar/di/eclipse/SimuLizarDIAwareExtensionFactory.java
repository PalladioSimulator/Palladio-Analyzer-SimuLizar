package org.palladiosimulator.simulizar.di.eclipse;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Supplier;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.palladiosimulator.simulizar.Activator;

public class SimuLizarDIAwareExtensionFactory implements IExecutableExtensionFactory, IExecutableExtension {

    private static ThreadLocal<Deque<Object>> componentCache = 
            ThreadLocal.withInitial(() -> new LinkedList<>());
    
    public void registerComponent(Object component) {
        componentCache.get().addFirst(component);
    }
    
    public <T> void unregister(Object component) {
        componentCache.get().removeFirstOccurrence(component);
    }
    
    public void cleanup() {
        componentCache.remove();
    }
    
    protected Supplier<Object> getExtensionFactory(Class<?> extensionClass) throws CoreException {
        var componentStack = componentCache.get();
        
        for (var component : componentStack) {
            var providerMethod = Arrays.stream(component.getClass().getMethods())
                .filter(m -> extensionClass.isAssignableFrom(m.getReturnType())
                        && m.getParameterCount() == 0)
                .findAny();
            
            if (providerMethod.isPresent()) {
                try {
                    var res = providerMethod.get().invoke(component);
                    return () -> res;
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, 
                            e.getMessage(), e));
                }
            }
        }
        throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
                "The class could not be resolved using the currently registered extension components"));
        
    }
    
    Supplier<Object> factorySupplier;
    
    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
            throws CoreException {
        var className = data.toString();
        Class<?> cls;
        try {
            cls = Platform.getBundle(config.getContributor().getName()).loadClass(className);
        } catch (ClassNotFoundException | InvalidRegistryObjectException e) {
            throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, 
                    e.getMessage(), e));
        }
        factorySupplier = getExtensionFactory(cls);
    }

    @Override
    public Object create() throws CoreException {
        return factorySupplier.get();
    }

}
