# SimuLizar Testing Utilities


The testing framework is based on the [Extension API of JUnit5](https://junit.org/junit5/docs/current/user-guide/#extensions). It supports writing simulation tests for the simulation engine simulizar. It currently consists of several test case annotations providing the following features:

- Loading model files from bundled resources or from distict providers
- Initializing EDP2 in memory repository
- Providing workflow configuration and workflow job
- Running SimuLizar simulation job
- Reading and comparing simulation results from EDP2 measurement series

By annotating the test methods with annotations of the Simulizar Testing utilities, certain initialization and cleanup logic is executed before / after each test case. The initialization logic is modularized and can be customized. Additionally, there are some convenience annotations to simplify often used patterns. The extensions leverage a shared JUnit extension store to register and provide mocks or properly initialized instances of simulation dependencies. 

## JUnit 5 Foundations

JUnit 5 supports providing custom extensions to the test logic by appending test methods or test classes with the annotation `@ExtendWith(SomeExtension.class)`. `SomeExtension` would then need to implement at least one of the Extension API interfaces of JUnit. JUnit5 looks for annotations on test methods and classes directly, but also for annotations present on other annotations. Therefore, by leveraging one of the SimuLizar Testing Utilities annotation, the appropriate extension is registered with the JUnit test runner. The three Extension API interfaces used by the SimuLizar Testing Utilities are:

- `BeforeEachCallback`: Specifies logic which is executed before each of the test cases. This is similar to providing initialization methods in you test class an annotating them with `@BeforeEach`.

- `ParameterResolver`: Supports providing parameters to test cases. The SimuLizar extension facilitates access to central simulation entities, means of looking up model elements from the shared PCMResourceSetPartition, as well as access to experiment results stored in EDP2. 

- `AfterEachCallback`: Append cleanup logic, to be executed after the test code itself was executed.

## Testing Support

The testing support comprises the following aspects:

- Loading simulation models
- Configuring a simulation
- Running a simulation
- Accessing simulation internals or simulation results

### Loading simulation models

Simulation jobs, i. e. SimuLizar jobs, rely on a shared `MDSDBlackboard` which contains the PCM instance to be simulated as a `PCMResourceSetPartition`. In order to run a simulation the blackboard needs to be initialized with PCM instance, which usually happens based on the started Eclipse Launch Configuration. In order to facilitate writing unit tests, we provide two additional annotations, `@PCMInstanceFromSupplier` and `@LoadPCMInstanceFromBundle`. The former should be used for small, programmatically constructed test models, the latter to load mode complex EMF models from a file provided by an eclipse plugin.

`@PCMInstanceFromSupplier` expects a parameter of type `Class<? extends Supplier<PCMResourceSetPartition>>`. Before running the test case the class is instantiated reflectively. The created instance is then requested for a `PCMResourceSetPartition` which will be stored in the central SimuLizar Extension store. Furthermore a `MDSDBlackboard` is initialized, containing the created partition. The blackboard is stored in the central store as well. In order for the class to be instantiable, it needs to provide a no-argument constructor, or a constructor accepting exactly one Parameter of type `org.junit.jupiter.api.extension.ExtensionContext`. Use the latter, if you need to interact with other JUnit extensions through the Extension store.

PCM models can be comfortably constructed using e. g. the Palladio Fluent API or the Xtend programming language. Examples for the latter can be found in the test cases of `ResourceEnvironmentSyncerTest`. A very basic model to build on is the class `TestModelBase.Empty`. It provides a PCMResourceSet with an default set of model resources, i. e. an empty repository, an empty system, and so on. Have a look at `ResourceEnvironmentTestModels.WithTwoContainersAndOneLink` to see how custom model providers can be built upon the default ones.

`@LoadPCMInstanceFromBundle` uses the previously described logic by registering the annotation `@PCMInstanceSupplier(LoadPCMInstanceFromBundleSupplier.class)`. This demostrates well, how JUnit supports meta-present annotations, that is, annotation present on annotations to test cases. LoadPCMInstanceFromBundle expects three parameters and uses the Eclipse Platform API to load the references models. The expected parameters are as follows:

- `bundleName`: the name of the bundle to load the model files from. Can be the name of the current test bundle.
- `basePath`: the relative path within the bundle
- `modelFiles`: an array of file names, which should be read into the resource set partition. 

An example of `@LoadPCMInstanceFromBundle`can be found in the `LinkingResourceSimulationTest`, where model files of the LinkingResourceExample are loaded from the respective plugin.

The models can be accessed from inside the test method, by specifying a parameter of type `MDSDBlackboard` or `PCMResourceSetPartition`. Additionally, by specifying a parameter of an EMF model element type (e. g. `UsageScenario`) the first model element from the resource set which matches the type will be provided. There is some further, more fine-grained traverse-and-filter support to select a particular model element from the partition based on its properties. 

### Configuring a simulation

Simulations are run inside jobs which adhere to the `de.uka.ipd.sdq.workflow.jobs.IJob` interface and are configured based on appropriate configurations of type `de.uka.ipd.sdq.workflow.configuration.IJobConfiguration`. In order to properly run SimuLizar in a test environment, we therefore need to initialize an appropriate workflow configuration as well as an appropriate simulation job. Hence, the initialization is supported by the two annotations `@SimulationConfigSupplier` and `@SimulationJobSupplier`. Similar, to the `@PCMInstanceSupplier` both annotation expect the class of an appropriate provider. The simulation config supplied by the former is stored in the shared Extension store, to be accessed by the simulation job supplier referred to by the latter. 

For the default SimuLizar test scenarios, the `@SimuLizarConfig` annotation provides a simulation config supplier for configurations of type `SimuLizarWorkflowConfiguration`. Currently it can be configured based on three flags (`simulateLinkingResource`, `simulateThroughput` and `simulateReliability`).

A SimuLizar job is available through the `RunSimuLizarSimulationJobSupplier` class. By annotating you test case with `@SimulationJobSupplier(RunSimuLizarSimulationJobSupplier.class)` the appropriate `PCMInterpretationJob` will be created. In order for the job supplier to work, a suitable configuration needs to be available.

### Running a simulation

Usually, simulation tests run a selected experiment and subsequently verify the correctness of the results. To this kind of tests, you can automate the execution of the simulation. If you annotate your test case with `@RunSimulationJob`, a previously created and registered `IJob` is executed. In case the job adheres to the interface `IBlackbloardInteractingJob`, the job is injected with the current `MDSDBlackboard`. When the test method is called, the simulation is finished and the results can be accessed from the in-memory EDP2 repository.

### Verifying simulation results

If the simulation was run through the `@RunSimulationJob` annotation, the simulation result is available as a parameter to the test method. By specifying a parameter of type `ExperimentRun` the test method is provided with the one experiment run created during simulation execution. This resolves you of having to access and navigate the cumbersome EDP2 API. To further facilitate writing checks for measurement series, the class `MeasurementTestUtils` provides you with a set of helpers. Have a look at `LinkingResourceSimulationTest` to see it in action.

### Putting it all together / Convenience

By combining the previously listed annotations most of the simulation tests can be written quite concise. As most test cases run a simulation and subsequently verify its results, we provide a convenience annotation `@RunSimuLizar` to comprise the default case. `@RunSimuLizar` registers the required extension, provides an appropriate configuration and job, and subsequently executes the simulation. Any setting in particular can be customized by providing an annotation of the specialized type on the test case directly. These annotations are the preferred over meta-present annotations. 

A standard SimuLizar test consequently only needs the following annotations:
```
@Test //This is JUnit
@LoadPCMInstanceFromBundle(...) //or similar
@RunSimuLizar
void testSimulation(UsageScenario scenario, ExperimentRun expRun) {
    // Inside your test method you now have access to the UsageScenario from you loaded UsageModel 
    // and the ExperimentRun which holds the EDP2 measurements created during the simulation run.
}
```


## Example
The following example is taken from the `LinkingResourceSimulationTest` and illustrates some of the presented concepts.

```
// This is a JUnit test
@Test 

// We load the model from the packaged example models which are provided with the Palladio Drop.
// Using the bundled models, we can ensure, that the examples properly work with the simulator.
@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/LinkingResource_Test", modelFiles = {
        "default.allocation", "default.measuringpoint", "default.monitorrepository", "default.repository",
        "default.resourceenvironment", "default.slo", "default.system", "default.usagemodel" })

// We override the default simulation config as registered by the next annotation, as we want to activate throughput simulation.
@SimulationConfig(simulateLinkThroughput = true)

// Configure and run SimuLizar
@RunSimuLizar

void testLinkingResourceThroughputSimulation(UsageScenario scenario, ExperimentRun expRun)
        throws JobFailedException, UserCanceledException {
    
    // We want to validate the simulation results by comparing the response time prediction 
    // of the simulated example with our expected results. 
    // Using MeasurementTestUtils.getMeasurementOfAt we can access a series of measurement values
    // recorded for a certain metric at a certain MeasuringPoint. The measuring point is identified
    // by the model element itself, in the following case the UsageScenario.
    var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
            MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
    
    // If there is no measurement series, the simulation failed.
    assertTrue(measurement.isPresent());

    // Check for each value of the measurement series, that it matches certain criteria. In this
    // case, check that all of them are between 184 and 185. The number was determined based on 
    // the deprecated SimuCom simulator.
    MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
            MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
            is(allOf(greaterThan(184.0), lessThan(185.0))));

}
```

