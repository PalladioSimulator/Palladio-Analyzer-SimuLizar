package org.palladiosimulator.simulizar.di.extension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.simulizar.di.base.extension.GenericComponentFactory;
import org.palladiosimulator.simulizar.di.extension.sample.ComponentA;
import org.palladiosimulator.simulizar.di.extension.sample.ComponentB;
import org.palladiosimulator.simulizar.di.extension.sample.ComponentC;
import org.palladiosimulator.simulizar.di.extension.sample.DaggerComponentA;
import org.palladiosimulator.simulizar.di.extension.sample.DaggerComponentA2;
import org.palladiosimulator.simulizar.di.extension.sample.DaggerComponentB;
import org.palladiosimulator.simulizar.di.extension.sample.DaggerComponentC;

import com.google.common.collect.ImmutableList;

class TestGenericComponentFactory {
    
    @Test
    void testNoDependencies() {
        var factory = DaggerComponentA.factory();
        var underTest = new GenericComponentFactory<ComponentA>(factory);
        assertIterableEquals(underTest.getUnfullfilledRequirements(), ImmutableList.of());
    }
    
    @Test
    void testDependenciesToOneComponent() {
        var factory = DaggerComponentB.factory();
        var underTest = new GenericComponentFactory<ComponentB>(factory);
        assertIterableEquals(underTest.getUnfullfilledRequirements(), ImmutableList.of(ComponentA.class));
        var componentA = DaggerComponentA.factory().create();
        underTest.fulfillRequirement(() -> componentA);
        assertIterableEquals(underTest.getUnfullfilledRequirements(), ImmutableList.of());
        var created = underTest.get();
        assertThat(created, isA(ComponentB.class));
    }
    
    @Test
    void testMultipleDependencies() {
        var factory = DaggerComponentC.factory();
        var underTest = new GenericComponentFactory<ComponentC>(factory);
        assertThat(underTest.getUnfullfilledRequirements(), containsInAnyOrder(ComponentA.class, ComponentB.class));
        var componentA = DaggerComponentA.factory().create();
        underTest.fulfillRequirement(() -> componentA);
        assertIterableEquals(underTest.getUnfullfilledRequirements(), ImmutableList.of(ComponentB.class));
        underTest.fulfillRequirement(() -> DaggerComponentB.factory().create(componentA));
        assertIterableEquals(underTest.getUnfullfilledRequirements(), ImmutableList.of());
        var created = underTest.get();
        assertThat(created, isA(ComponentC.class));
        assertThat(created.name(), is("A"));
    }
    
    @Test
    void testDependencyToSuperComponent() {
        var factory = DaggerComponentC.factory();
        var underTest = new GenericComponentFactory<ComponentC>(factory);
        assertThat(underTest.getUnfullfilledRequirements(), containsInAnyOrder(ComponentA.class, ComponentB.class));
        var componentA = DaggerComponentA2.factory().create();
        underTest.fulfillRequirement(() -> componentA);
        assertIterableEquals(underTest.getUnfullfilledRequirements(), ImmutableList.of(ComponentB.class));
        underTest.fulfillRequirement(() -> DaggerComponentB.factory().create(componentA));
        assertIterableEquals(underTest.getUnfullfilledRequirements(), ImmutableList.of());
        var created = underTest.get();
        assertThat(created, isA(ComponentC.class));
        assertThat(created.name(), is("A2"));
    }
    
    

}
