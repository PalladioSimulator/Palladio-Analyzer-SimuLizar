package org.palladiosimulator.simulizar.di.modules.stateless.core;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.ForkedBehaviorProcessFactory;
import org.palladiosimulator.simulizar.interpreter.impl.ForkedBehaviorProcessFactoryImpl;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;

import dagger.Binds;
import de.uka.ipd.sdq.simucomframework.core.resources.IAssemblyAllocationLookup;

@dagger.Module
public interface SimuLizarRuntimeBindingsModule {
    
    @Binds IAssemblyAllocationLookup<EntityReference<ResourceContainer>> bindAssemblyAllocationLookup(AssemblyAllocationManager manager);

}
