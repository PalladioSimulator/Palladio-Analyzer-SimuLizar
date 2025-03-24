package org.palladiosimulator.simulizar.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.analyzer.workflow.core.ConstantsContainer;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.monitorrepository.StatisticalCharacterization;
import org.palladiosimulator.monitorrepository.TimeDrivenAggregation;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationContext;
import org.palladiosimulator.pcm.allocation.util.AllocationResourceFactoryImpl;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.util.RepositoryResourceFactoryImpl;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.util.ResourceenvironmentResourceFactoryImpl;
import org.palladiosimulator.pcm.seff.ProbabilisticBranchTransition;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.system.util.SystemResourceFactoryImpl;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOReconfigurator;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.common.collect.Streams;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;
import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
@PluginTestOnly
public class QVToReconfigurationTest {

    private static final String PCM_TEST_MODEL_SEFF_BRANCH_ACTION_ID = "_1P7G0LwGEeSxGbiYbg6Waw";
    private static final String MEASUREMENT_SPECIFICATION_ID = "_sEx-cMLAEeSZr8oGpigbHA";
 
    private final static String REPOSITORY_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/server.repository";
    private final static String RESOURCE_ENVIRONMENT_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/server.resourceenvironment";
    private final static String SYSTEM_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/server.system";
    private final static String ALLOCATION_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/server.allocation";
    private final static String MONITOR_REPOSITORY_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/monitors/server.monitorrepository";

    private final static String TRANSFORMATION_RULES_ADD_DUPLICATED_SERVER_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/rules/addClonedServer";
    private final static String TRANSFORMATION_RULES_ADD_SERVER_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/rules/addNewServer";
    private final static String TRANSFORMATION_RULES_OUTSOURCE_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/rules/outsource";
    private final static String TRANSFORMATION_RULES_SCALE_UP_PATH = "/org.palladiosimulator.simulizar.tests/testmodels/runconfigtest/rules/scaleUp";

    private final static String WF_CONFIGURATION_KEY_ALLOCATION_FILE = "allocationFile";
    private final static String REPOSITORY_EXTENSION = "repository";
    private final static String RESOURCE_ENVIRONMENT_EXTENSION = "resourceenvironment";
    private final static String SYSTEM_EXTENSION = "system";
    private final static String ALLOCATION_EXTENSION = "allocation";

    private final static String BRANCH_2_ENTITY_NAME = "branch2";
    private final static double BRANCH_2_EXPECTED_VALUE_AFTER_OUTSOURCING = 0.1;
    private final static double BRANCH_2_EXPECTED_VALUE_BEFORE_OUTSOURCING = 0.0;
    private final static double MEASUREMENT_BELOW_THRESHOLD = 1.0;
    private final static double MEASUREMENT_OVER_THRESHOLD = 5.0;
    private final static String SERVER_RESOURCE_CONTAINER_NAME = "server";
    private final static double SERVER_EXPECTED_PROCESSING_RATE_AFTER_SCALING = 1100.0;
    private final static double SERVER_EXPECTED_PROCESSING_RATE_BEFORE_SCALING = 1000.0;
    private final static int EXPECTED_NUMBER_OF_SERVERS_BEFORE_ADDING = 1;
    private final static int EXPECTED_NUMBER_OF_SERVERS_AFTER_ADDING = 2;

    private static URI systemURI;
    private static URI resourceEnvironmentURI;
    private static URI repositoryURI;
    private static URI allocationURI;
    private static URI monitorURI;

    private static IResourceTableManager resourceTableManager;

    @BeforeAll
    public static void setUpBeforeClass() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
            .put(REPOSITORY_EXTENSION, new RepositoryResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
            .put(RESOURCE_ENVIRONMENT_EXTENSION, new ResourceenvironmentResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
            .put(SYSTEM_EXTENSION, new SystemResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
            .put(ALLOCATION_EXTENSION, new AllocationResourceFactoryImpl());
        Map<URI, URI> uriMap = URIConverter.URI_MAP;
        uriMap.put(URI.createURI("pathmap://METRIC_SPEC_MODELS/commonMetrics.metricspec"),
                URI.createURI("platform:/plugin/org.palladiosimulator.metricspec.resources/commonMetrics.metricspec"));

        repositoryURI = URI.createPlatformPluginURI(REPOSITORY_PATH, true);
        repositoryURI = CommonPlugin.resolve(repositoryURI);
        resourceEnvironmentURI = URI.createPlatformPluginURI(RESOURCE_ENVIRONMENT_PATH, true);
        resourceEnvironmentURI = CommonPlugin.resolve(resourceEnvironmentURI);
        systemURI = URI.createPlatformPluginURI(SYSTEM_PATH, true);
        systemURI = CommonPlugin.resolve(systemURI);
        allocationURI = URI.createPlatformPluginURI(ALLOCATION_PATH, true);
        allocationURI = CommonPlugin.resolve(allocationURI);
        monitorURI = URI.createPlatformPluginURI(MONITOR_REPOSITORY_PATH, true);
        monitorURI = CommonPlugin.resolve(monitorURI);

        resourceTableManager = new ResourceTableManager();

    }

    
    @Test
    void testPerformOutsourceReconfigurationIfMeasuredValueExceedsThreshold() throws Exception {
        PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_OVER_THRESHOLD, TRANSFORMATION_RULES_OUTSOURCE_PATH, resourceTableManager);
        
        double actualResult = checkOutsourceReconfiguration(pcmResourceSet, BRANCH_2_ENTITY_NAME);
        assertEquals(BRANCH_2_EXPECTED_VALUE_AFTER_OUTSOURCING, actualResult, 0.0, "The branch probability was not changed as expected!");
    }
    
    
    @Test
    void testSkipOutsourceReconfigurationIfMeasuredValueBelowThreshold() throws Exception {
        PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_BELOW_THRESHOLD, TRANSFORMATION_RULES_OUTSOURCE_PATH, resourceTableManager);
        
        double actualResult = checkOutsourceReconfiguration(pcmResourceSet, BRANCH_2_ENTITY_NAME);
        assertEquals(BRANCH_2_EXPECTED_VALUE_BEFORE_OUTSOURCING, actualResult, 0.0, "The branch probability has not remained as it was expected!");
    }
    
    
    @Test
    void testPerformScaleUpReconfigurationIfMeasuredValueExceedsThreshold() throws Exception {
        PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_OVER_THRESHOLD, TRANSFORMATION_RULES_SCALE_UP_PATH, resourceTableManager);
        
        double actualResult = checkScaleUpReconfiguration(pcmResourceSet, SERVER_RESOURCE_CONTAINER_NAME);
        assertEquals(SERVER_EXPECTED_PROCESSING_RATE_AFTER_SCALING, actualResult, 0.0, "Processing resources have not scaled as expected!");
    }
    
    
    @Test
    void testSkipScaleUpReconfigurationIfMeasuredValueBelowThreshold() throws Exception {
        final PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_BELOW_THRESHOLD, TRANSFORMATION_RULES_SCALE_UP_PATH, resourceTableManager);
        
        double actualResult = checkScaleUpReconfiguration(pcmResourceSet, SERVER_RESOURCE_CONTAINER_NAME);
        assertEquals(SERVER_EXPECTED_PROCESSING_RATE_BEFORE_SCALING, actualResult, 0.0, "Processing resources have not remained as it was expected!");
    }
    
    
    @Test
    void testPerformAddNewServerReconfigurationIfMeasuredValueExceedsThreshold() throws Exception {
       PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_OVER_THRESHOLD, TRANSFORMATION_RULES_ADD_SERVER_PATH, resourceTableManager);
        
       int actualResult = checkAddNewServerReconfiguration(pcmResourceSet, "server1", "server2", "client");
       assertEquals(EXPECTED_NUMBER_OF_SERVERS_AFTER_ADDING, actualResult, "The server was not added!");
    }
    
    
    @Test
    void testSkipAddNewServerUpReconfigurationIfMeasuredValueBelowThreshold() throws Exception {
        PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_BELOW_THRESHOLD, TRANSFORMATION_RULES_ADD_SERVER_PATH, resourceTableManager);
        
        int actualResult = checkAddNewServerReconfiguration(pcmResourceSet, "server1", "server2", "client");
        assertEquals(EXPECTED_NUMBER_OF_SERVERS_BEFORE_ADDING, actualResult, "The number of servers is not as expected!");
               
    }
    
    
    @Test
    void testPerformAddClonedServerReconfigurationIfMeasuredValueExceedsThreshold() throws Exception {
        PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_OVER_THRESHOLD, TRANSFORMATION_RULES_ADD_DUPLICATED_SERVER_PATH, resourceTableManager);
        
        int actualResult = checkAddClonedServerReconfiguration(pcmResourceSet, "server1", "client");
        assertEquals(EXPECTED_NUMBER_OF_SERVERS_AFTER_ADDING, actualResult, "The server was not added!");
    }

    
    @Test
    void testSkipAddClonedServerUpReconfigurationIfMeasuredValueBelowThreshold() throws Exception {
        PCMResourceSetPartition pcmResourceSet = runReconfiguration(MEASUREMENT_BELOW_THRESHOLD, TRANSFORMATION_RULES_ADD_DUPLICATED_SERVER_PATH, resourceTableManager);
        
        int actualResult = checkAddClonedServerReconfiguration(pcmResourceSet, "server1", "client");
        assertEquals(EXPECTED_NUMBER_OF_SERVERS_BEFORE_ADDING, actualResult, "The number of servers is not as expected!");
    }
    
    
    private int checkAddNewServerReconfiguration(PCMResourceSetPartition pcmResourceSet, String expectedProvidedAssemblyContextEntityName1, 
            String expectedProvidedAssemblyContextEntityName2, String expectedRequiredAssemblyContextEntityName) {
        final Allocation allocation = pcmResourceSet.getAllocation();
        int numOfServer1Client = 0, numOfServer2Client = 0;
        for (final Connector connector : allocation.getSystem_Allocation().getConnectors__ComposedStructure()) {
            if (connector instanceof AssemblyConnector) {
                final AssemblyConnector assemblyConnector = (AssemblyConnector) connector;
                final AssemblyContext assemblyContextProviding = assemblyConnector.getProvidingAssemblyContext_AssemblyConnector();
                final AssemblyContext assemblyContextRequiring = assemblyConnector.getRequiringAssemblyContext_AssemblyConnector();

                if (assemblyContextProviding.getEncapsulatedComponent__AssemblyContext().getEntityName().equals(expectedProvidedAssemblyContextEntityName1)
                    && assemblyContextRequiring.getEncapsulatedComponent__AssemblyContext().getEntityName().equals(expectedRequiredAssemblyContextEntityName)) {
                    numOfServer1Client++;
                }

                if (assemblyContextProviding.getEncapsulatedComponent__AssemblyContext().getEntityName().equals(expectedProvidedAssemblyContextEntityName2)
                    && assemblyContextRequiring.getEncapsulatedComponent__AssemblyContext().getEntityName().equals(expectedRequiredAssemblyContextEntityName)) {
                    numOfServer2Client++;
                }
            }
        }
        return numOfServer1Client + numOfServer2Client;
    }

    private int checkAddClonedServerReconfiguration(PCMResourceSetPartition pcmResourceSet, String expectedProvidedAssemblyContextEntityName, String expectedRequiredAssemblyContextEntityName) {
        final Allocation allocation = pcmResourceSet.getAllocation();
        int numOfIServerProviders = 0;
        for (Connector connector : allocation.getSystem_Allocation().getConnectors__ComposedStructure()) {
            if (connector instanceof AssemblyConnector) {
                AssemblyConnector assemblyConnector = (AssemblyConnector) connector;
                AssemblyContext assemblyContextProviding = assemblyConnector.getProvidingAssemblyContext_AssemblyConnector();
                AssemblyContext assemblyContextRequiring = assemblyConnector.getRequiringAssemblyContext_AssemblyConnector();

                if (assemblyContextProviding.getEncapsulatedComponent__AssemblyContext().getEntityName().equals(expectedProvidedAssemblyContextEntityName)
                    && assemblyContextRequiring.getEncapsulatedComponent__AssemblyContext().getEntityName().equals(expectedRequiredAssemblyContextEntityName)) {
                    numOfIServerProviders++;
                }
            }
        }
        return numOfIServerProviders;
    }

    /**
     * Performs scaling up with the measurement passed as parameter. If the measurement is over the
     * threshold the scaling up should be performed, otherwise not.
     * @param m
     *            measurement that defines whether the scaling up is performed or not.
     *
     * @return processing resource of the server that is to be scaled up.
     */
    private double checkScaleUpReconfiguration(PCMResourceSetPartition pcmResourceSet, String expectedResourcContainerEntityName) {
        final Allocation allocation = pcmResourceSet.getAllocation();
        final ResourceEnvironment resourceEnvironment = allocation.getTargetResourceEnvironment_Allocation();
        final Iterator<ResourceContainer> iteratorResourceContainer = resourceEnvironment.getResourceContainer_ResourceEnvironment().iterator();
        while (iteratorResourceContainer.hasNext()) {
            final ResourceContainer resourceContainer = iteratorResourceContainer.next();
            if (resourceContainer.getEntityName().equals(expectedResourcContainerEntityName)) {
                final Iterator<ProcessingResourceSpecification> iteratorProcessingResourceSpecification = resourceContainer.getActiveResourceSpecifications_ResourceContainer().iterator();
                while (iteratorProcessingResourceSpecification.hasNext()) {
                    final ProcessingResourceSpecification processingResourceSpecification = iteratorProcessingResourceSpecification.next();
                    return Double.parseDouble(processingResourceSpecification.getProcessingRate_ProcessingResourceSpecification().getSpecification());
                }
            }
        }
        fail("The test reached the end!");
        return Double.NaN;
    }

    /**
     * Performs outsourcing with the measurement passed as parameter. If the measurement is over the
     * threshold the outsourcing should be performed, otherwise not.
     * @return branch probability that is to be increased.
     */
    private double checkOutsourceReconfiguration(PCMResourceSetPartition pcmResourceSet, String expectedBranchEntityName) {
        final TreeIterator<EObject> pcmModelIterator = pcmResourceSet.getAllocation().eAllContents();
        /*
         * Iterate over all the elements of the allocation diagram.
         */
        while (pcmModelIterator.hasNext()) {
            final EObject root = pcmModelIterator.next();
            /*
             * We are interested in AllocationContexts only because we can get to the server which
             * contains the SEFF that is of our interest.
             */
            if (root instanceof AllocationContext) {
                final AllocationContext serverAllocationContext = (AllocationContext) root;
                final AssemblyContext serverAssemblyContext = serverAllocationContext
                    .getAssemblyContext_AllocationContext();
                /*
                 * The server that contains our SEFF is of type BasicComponent.
                 */
                if (serverAssemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof BasicComponent) {
                    final BasicComponent serverBasicComponent = (BasicComponent) serverAssemblyContext
                        .getEncapsulatedComponent__AssemblyContext();
                    final EList<ServiceEffectSpecification> serverSeffs = serverBasicComponent
                        .getServiceEffectSpecifications__BasicComponent();
                    /*
                     * We iterate all the SEFFs within the BasicComponent.
                     */
                    for (final ServiceEffectSpecification seff : serverSeffs) {
                        /*
                         * ResourceDemandingSEFF in particular are of our interest.
                         */
                        if (seff instanceof ResourceDemandingSEFF) {
                            final TreeIterator<EObject> seffIterator = seff.eAllContents();
                            /*
                             * We now iterate every ResourceDemandingSEFF in attempt to find
                             * ProbabilisticBranchTransition.
                             */
                            while (seffIterator.hasNext()) {
                                final EObject seffObject = seffIterator.next();
                                /*
                                 * Once we find our ProbabilisticBranchTransitions we compare their
                                 * values with the expected ones.
                                 */
                                if (seffObject instanceof ProbabilisticBranchTransition) {
                                    final ProbabilisticBranchTransition branchTransition = (ProbabilisticBranchTransition) seffObject;
                                    if (branchTransition.getEntityName().equals(expectedBranchEntityName)) {
                                        return branchTransition.getBranchProbability();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        fail("The test reached the end!");
        return Double.NaN;
    }

    /**
     * Creates a measurement out of parameter "m", reads in the PCM model from the folder
     * "testmodel", performs the QVTo rules that are placed in the folder "testmodel/rules" and
     * returns the resulting PCM model. The PCM model could be changed or not, depending on the
     * parameter "measuredValue".
     *
     * @param measuredValue     measurement.
     * @return The PCM model after the rules from "testmodel/rules" have been applied.
     */
    private PCMResourceSetPartition runReconfiguration(double measuredValue, 
            String reconfigurationRulesFolderPath, IResourceTableManager resourceTableManager) {

        final PCMResourceSetPartition pcmResourceSet = loadPcmResources();
        EObject monitoredElement = assignMonitorToModelElement(pcmResourceSet, PCM_TEST_MODEL_SEFF_BRANCH_ACTION_ID);

        SimuLizarWorkflowConfiguration workflowConfig = createWorkflowConfiguration(allocationURI, reconfigurationRulesFolderPath);
        PCMPartitionManager pcmPartitionManager = loadPcmModelsIntoBlackboard(workflowConfig, pcmResourceSet, reconfigurationRulesFolderPath);

        RuntimeMeasurement responeTimeRuntimeMeasurement = createResponeTimeMeasurement(measuredValue);
        recordRuntimeMeasurement(pcmPartitionManager, responeTimeRuntimeMeasurement);

        executeTransformation(workflowConfig, pcmPartitionManager, monitoredElement);

        return pcmResourceSet;
    }
    
    
    private PCMResourceSetPartition loadPcmResources() {
        final PCMResourceSetPartition pcmResourceSet = new PCMResourceSetPartition();
        pcmResourceSet.loadModel(repositoryURI);
        pcmResourceSet.loadModel(resourceEnvironmentURI);
        pcmResourceSet.loadModel(systemURI);
        pcmResourceSet.loadModel(allocationURI);
        pcmResourceSet.loadModel(monitorURI);
        return pcmResourceSet;
    }
    
    
    private EObject assignMonitorToModelElement(PCMResourceSetPartition pcmResourceSet, String modelElementId) {
        var result = pcmResourceSet.getRepositories().stream().map(EObject::eAllContents).flatMap(Streams::stream)
            .filter(element -> {
                final EAttribute id = element.eClass()
                    .getEIDAttribute();
                final Object idAttribute = element.eGet(id);
                
                return idAttribute.toString().equals(modelElementId);
            })
            .findAny();
        assertTrue(result.isPresent());
        return result.get();
    }

    
    private SimuLizarWorkflowConfiguration createWorkflowConfiguration(URI allocationURI, String reconfigurationRulesFolderPath) {
        Map<String, Object> configuration = new HashMap<String, Object>();
        configuration.put(WF_CONFIGURATION_KEY_ALLOCATION_FILE, Paths.get(allocationURI.path()).toAbsolutePath().toString());

        SimuLizarWorkflowConfiguration swfc = new SimuLizarWorkflowConfiguration(configuration);
        URI reconfRulesURI = URI.createPlatformPluginURI(reconfigurationRulesFolderPath, false);
        swfc.setReconfigurationRulesFolder(reconfRulesURI.toString());
        return swfc;
    }
    

    private PCMPartitionManager loadPcmModelsIntoBlackboard(SimuLizarWorkflowConfiguration workflowConfig, ResourceSetPartition pcmResourceSet, String reconfigurationRulesFolderPath) {
        final MDSDBlackboard blackboard = new MDSDBlackboard();
        blackboard.addPartition(ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID, pcmResourceSet);
        final PCMPartitionManager pcmPartitionManager = new PCMPartitionManager(blackboard, workflowConfig);
        pcmPartitionManager.initialize();
        return pcmPartitionManager;
    }
    
    
    private RuntimeMeasurement createResponeTimeMeasurement(final double responeTimeMeasurement) {
        final MeasurementSpecification measurementSpecification = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
        measurementSpecification.setId(MEASUREMENT_SPECIFICATION_ID);
        measurementSpecification.setMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        StatisticalCharacterization statisticalCharacterization = MonitorRepositoryFactory.eINSTANCE.createArithmeticMean();
        TimeDrivenAggregation timeDrivenAggregation = MonitorRepositoryFactory.eINSTANCE.createTimeDrivenAggregation();
        timeDrivenAggregation.setStatisticalCharacterization(statisticalCharacterization);
        timeDrivenAggregation.setMeasurementSpecification(measurementSpecification);
        timeDrivenAggregation.setWindowIncrement(10d);
        timeDrivenAggregation.setWindowLength(10d);

        final RuntimeMeasurement responeTimeRuntimeMeasurement = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurement();
        responeTimeRuntimeMeasurement.setId("");
        responeTimeRuntimeMeasurement.setMeasuringValue(responeTimeMeasurement);
        responeTimeRuntimeMeasurement.setMeasurementSpecification(measurementSpecification);
        
        return responeTimeRuntimeMeasurement;
    }
    
    
    private void recordRuntimeMeasurement(PCMPartitionManager pcmPartitionManager, RuntimeMeasurement responeTimeRuntimeMeasurement) {
        RuntimeMeasurementModel rmModel = pcmPartitionManager.findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
        rmModel.getMeasurements().add(responeTimeRuntimeMeasurement);
    }
    

    
    private void executeTransformation(SimuLizarWorkflowConfiguration wfConfig, PCMPartitionManager pcmPartitionManager, EObject monitoredElement) {
        QVTOReconfigurator reconfigurator = new QVTOReconfigurator(wfConfig, pcmPartitionManager);
        QVTOReconfigurationLoader reconfigurationLoader = new QVTOReconfigurationLoader();
        reconfigurationLoader.load(wfConfig);
        EList<ModelTransformation<? extends Object>> transformations = new BasicEList<>(reconfigurationLoader.getTransformations());
        boolean checkedAndExceuted = reconfigurator.runExecute(transformations, monitoredElement, resourceTableManager);
        assertTrue(checkedAndExceuted, "Reconfiguration was not executed!");
    }
}