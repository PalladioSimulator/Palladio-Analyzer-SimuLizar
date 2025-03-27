package org.palladiosimulator.simulizar.di.extension;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.simulizar.di.base.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.sample.ComponentA;
import org.palladiosimulator.simulizar.di.extension.sample.ComponentB;
import org.palladiosimulator.simulizar.di.extension.sample.ComponentC;
import org.palladiosimulator.simulizar.di.extension.sample.DaggerComponentA;
import org.palladiosimulator.simulizar.di.extension.sample.DaggerComponentB;
import org.palladiosimulator.simulizar.di.extension.sample.DaggerComponentC;
import org.palladiosimulator.simulizar.di.extension.sample.ModuleA;

import com.google.common.collect.ImmutableSet;

import dagger.Component;

class TestExtensionComponentDepencencyResolution {

    @Test
    void testNoExtensionNoErrors() {
        var underTest = new ExtensionComponentDependencyResolution(ImmutableSet.of(), ImmutableSet.of());
        assertThat(underTest.getExtensionComponents(), is(empty()));
    }
    
    @Test
    void testSingleComponentNoDependencies() throws Exception {
        var underTest = new ExtensionComponentDependencyResolution(ImmutableSet.of(), 
                ImmutableSet.of(DaggerComponentA.factory()));
        var result = underTest.getExtensionComponents();
        assertThat(result, containsInAnyOrder(instanceOf(ComponentA.class)));
    }
    
    @Test
    void testSimpleComponentDependencies() throws Exception {
        var underTest = new ExtensionComponentDependencyResolution(ImmutableSet.of(), 
                ImmutableSet.of(DaggerComponentA.factory(), DaggerComponentB.factory()));
        var result = underTest.getExtensionComponents();
        assertThat(result, containsInAnyOrder(instanceOf(ComponentA.class), instanceOf(ComponentB.class)));
    }
    
    @Test
    void testBootstrappedComponentDependencies() throws Exception {
        var underTest = new ExtensionComponentDependencyResolution(ImmutableSet.of(DaggerComponentA.create()), 
                ImmutableSet.of(DaggerComponentB.factory(), DaggerComponentC.factory()));
        var result = underTest.getExtensionComponents();
        assertThat(result, containsInAnyOrder(instanceOf(ComponentB.class), instanceOf(ComponentC.class)));
    }
    
    @Test
    void testMissingDependenciesDoNotCauseErrors() throws Exception {
        var underTest = new ExtensionComponentDependencyResolution(ImmutableSet.of(), 
                ImmutableSet.of(DaggerComponentB.factory(), DaggerComponentC.factory()));
        var result = underTest.getExtensionComponents();
        assertThat(result, is(iterableWithSize(0)));
        
        underTest = new ExtensionComponentDependencyResolution(ImmutableSet.of(),
                ImmutableSet.of(DaggerComponentA.factory(), DaggerComponentC.factory()));
        result = underTest.getExtensionComponents();
        assertThat(result, containsInAnyOrder(instanceOf(ComponentA.class)));
    }
    
    // We need to introduce the dependency in a subclass, as otherwise dagger will detect the cycle during compile-time
    @Component(dependencies = ComponentB.class, modules = ModuleA.class)
    public static interface AWithDependencyToB extends ComponentA {
        @Component.Factory
        public static interface Factory extends ExtensionComponent.Factory {
            AWithDependencyToB create(ComponentB b);
        }
    }
    
    @Test
    void testCyclesAreDetected() throws Exception {
        var underTest = new ExtensionComponentDependencyResolution(ImmutableSet.of(), 
                ImmutableSet.of(DaggerComponentB.factory(), DaggerTestExtensionComponentDepencencyResolution_AWithDependencyToB.factory()));
        
        Assertions.assertThrows(IllegalStateException.class, () -> {
            underTest.getExtensionComponents();
        });
    }

}
