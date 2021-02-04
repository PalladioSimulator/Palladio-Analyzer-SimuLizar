package org.palladiosimulator.simulizar.test.commons.models

import org.palladiosimulator.simulizar.test.commons.models.TestModelBase

class AllocationLookupSyncerTestModels extends TestModelBase {
	
	static class TrivialAllocation extends TestModelBase.Empty {
		public static val RCId = "ResourceContainer1";
		public static val AssemblyCtxId = "0123456789"
		
		override get() {
			return super.get() => [
				val component = rf.createBasicComponent
				repositories.head => [
					components__Repository += component
				]
				
				val assembly = cpf.createAssemblyContext => [
					id = AssemblyCtxId
					encapsulatedComponent__AssemblyContext = component
				]
				system.assemblyContexts__ComposedStructure += assembly
				
				val rc = ref.createResourceContainer => [
					id = RCId
				]
				resourceEnvironment.resourceContainer_ResourceEnvironment += rc
				
				allocation.allocationContexts_Allocation += af.createAllocationContext => [
					assemblyContext_AllocationContext = assembly
					resourceContainer_AllocationContext = rc
				]
			]
		}
	}
	
	static class CompositeComponentAllocation extends TestModelBase.Empty {
		public static val RCId = "ResourceContainer1";
		public static val NestedAssemblyCtxId = "Nested"
		public static val AssemblyCtx1Id = "Nesting1"
		public static val AssemblyCtx2Id = "Nesting2"
		
		override get() {
			return super.get() => [
				val nestedComponent = rf.createBasicComponent
				val component = rf.createCompositeComponent => [
					assemblyContexts__ComposedStructure += cpf.createAssemblyContext => [
						id = NestedAssemblyCtxId
						encapsulatedComponent__AssemblyContext = nestedComponent
					]
				]
				repositories.head => [
					components__Repository += nestedComponent
					components__Repository += component
				]
				
				val assembly1 = cpf.createAssemblyContext => [
					id = AssemblyCtx1Id
					encapsulatedComponent__AssemblyContext = component
				]
				val assembly2 = cpf.createAssemblyContext => [
					id = AssemblyCtx2Id
					encapsulatedComponent__AssemblyContext = component
				]
				system.assemblyContexts__ComposedStructure += assembly1
				system.assemblyContexts__ComposedStructure += assembly2
				
				val rc = ref.createResourceContainer => [
					id = RCId
				]
				resourceEnvironment.resourceContainer_ResourceEnvironment += rc
				
				allocation.allocationContexts_Allocation += af.createAllocationContext => [
					assemblyContext_AllocationContext = assembly1
					resourceContainer_AllocationContext = rc
				]
				allocation.allocationContexts_Allocation += af.createAllocationContext => [
					assemblyContext_AllocationContext = assembly2
					resourceContainer_AllocationContext = rc
				]
			]
		}
	}
	
	static class SubsystemWhiteboxAllocation extends TestModelBase.Empty {
		public static val RCId = "ResourceContainer1";
		public static val NestedAssemblyCtxId = "Nested"
		public static val AssemblyCtx1Id = "Nesting1"
		
		override get() {
			return super.get() => [
				val nestedComponent = rf.createBasicComponent
				val nestedACtx = cpf.createAssemblyContext => [
					id = NestedAssemblyCtxId
					encapsulatedComponent__AssemblyContext = nestedComponent
				]
				val component = ssf.createSubSystem => [
					assemblyContexts__ComposedStructure += nestedACtx 
				]
				repositories.head => [
					components__Repository += nestedComponent
					components__Repository += component
				]
				
				val assembly1 = cpf.createAssemblyContext => [
					id = AssemblyCtx1Id
					encapsulatedComponent__AssemblyContext = component
				]
				system.assemblyContexts__ComposedStructure += assembly1
				
				val rc = ref.createResourceContainer => [
					id = RCId
				]
				resourceEnvironment.resourceContainer_ResourceEnvironment += rc
				
				allocation.allocationContexts_Allocation += af.createAllocationContext => [
					assemblyContext_AllocationContext = nestedACtx
					resourceContainer_AllocationContext = rc
				]
			]
		}
	}
	
	
	
}
