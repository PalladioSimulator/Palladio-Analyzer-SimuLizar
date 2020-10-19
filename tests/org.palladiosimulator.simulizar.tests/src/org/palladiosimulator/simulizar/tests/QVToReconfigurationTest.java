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
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
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
import org.palladiosimulator.simulizar.reconfiguration.qvto.QvtoReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class QVToReconfigurationTest {

    private final static String REPOSITORY_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/server.repository";
    private final static String RESOURCE_ENVIRONMENT_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/server.resourceenvironment";
    private final static String SYSTEM_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/server.system";
    private final static String ALLOCATION_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/server.allocation";
    private final static String PMS_MODEL_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/server.monitorrepository";
    private final static String TRANSFORMATION_RULES_ADD_DUPLICATED_SERVER_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/rules/addClonedServer";
    private final static String TRANSFORMATION_RULES_ADD_SERVER_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/rules/addNewServer";
    private final static String TRANSFORMATION_RULES_OUTSOURCE_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/rules/outsource";
    private final static String TRANSFORMATION_RULES_SCALE_UP_PATH = "/org.palladiosimulator.simulizar.tests/testmodel/rules/scaleUp";
    private final static String ALLOCATION_FILE_CONFIGURATION_KEY = "allocationFile";
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
    private static URI pmsURI;
    
    private static IResourceTableManager resourceTableManager;

    @BeforeAll
    public static void setUpBeforeClass() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(REPOSITORY_EXTENSION,
                new RepositoryResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(RESOURCE_ENVIRONMENT_EXTENSION,
                new ResourceenvironmentResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(SYSTEM_EXTENSION,
                new SystemResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(ALLOCATION_EXTENSION,
                new AllocationResourceFactoryImpl());
        Map<URI, URI> uriMap = URIConverter.URI_MAP;
        uriMap.put(URI.createURI(" pathmap://METRIC_SPEC_MODELS/commonMetrics.metricspec"),
                URI.createURI("platform:/plugin/org.palladiosimulator.metricspec.resources/commonMetrics.metricspec"));

        repositoryURI = URI.createPlatformPluginURI(REPOSITORY_PATH, true);
        repositoryURI = CommonPlugin.resolve(repositoryURI);
        resourceEnvironmentURI = URI.createPlatformPluginURI(RESOURCE_ENVIRONMENT_PATH, true);
        resourceEnvironmentURI = CommonPlugin.resolve(resourceEnvironmentURI);
        systemURI = URI.createPlatformPluginURI(SYSTEM_PATH, true);
        systemURI = CommonPlugin.resolve(systemURI);
        allocationURI = URI.createPlatformPluginURI(ALLOCATION_PATH, true);
        allocationURI = CommonPlugin.resolve(allocationURI);
        pmsURI = URI.createPlatformPluginURI(PMS_MODEL_PATH, true);
        pmsURI = CommonPlugin.resolve(pmsURI);
        
        resourceTableManager = new ResourceTableManager();

    }

    @Test
    public void test() {
        reconfigurationTests();
    }

    private void reconfigurationTests() {
        assertEquals(BRANCH_2_EXPECTED_VALUE_AFTER_OUTSOURCING,
                outsource(MEASUREMENT_OVER_THRESHOLD), 0.0, "The branch probability was not changed as expected!");
        assertEquals(
                BRANCH_2_EXPECTED_VALUE_BEFORE_OUTSOURCING, outsource(MEASUREMENT_BELOW_THRESHOLD), 0.0, "The branch probability has not remained as it was expected!");

        assertEquals(SERVER_EXPECTED_PROCESSING_RATE_AFTER_SCALING,
                scaleUp(MEASUREMENT_OVER_THRESHOLD), 0.0, "Processing resources have not scaled as expected!");
        assertEquals(
                SERVER_EXPECTED_PROCESSING_RATE_BEFORE_SCALING, scaleUp(MEASUREMENT_BELOW_THRESHOLD), 0.0, "Processing resources have not remained as it was expected!");

        assertEquals(EXPECTED_NUMBER_OF_SERVERS_AFTER_ADDING,
                addNewServer(MEASUREMENT_OVER_THRESHOLD), 0.0, "The server was not added!");
        assertEquals(EXPECTED_NUMBER_OF_SERVERS_BEFORE_ADDING,
                addNewServer(MEASUREMENT_BELOW_THRESHOLD), 0.0, "The number of servers is not as expected!");

        assertEquals(EXPECTED_NUMBER_OF_SERVERS_AFTER_ADDING,
                addClonedServer(MEASUREMENT_OVER_THRESHOLD), 0.0, "The server was not added!");
        assertEquals(EXPECTED_NUMBER_OF_SERVERS_BEFORE_ADDING,
                addClonedServer(MEASUREMENT_BELOW_THRESHOLD), 0.0, "The number of servers is not as expected!");
    }

    private int addNewServer(final double m) {
        final PCMResourceSetPartition pcmResourceSet = readPcmModelAndApplyTransformationRules(m, TRANSFORMATION_RULES_ADD_SERVER_PATH, resourceTableManager);

        final Allocation allocation = pcmResourceSet.getAllocation();
        int numOfServer1Client = 0, numOfServer2Client = 0;
        for (final Connector connector : allocation.getSystem_Allocation().getConnectors__ComposedStructure()) {
            if (connector instanceof AssemblyConnector) {

                final AssemblyConnector assemblyConnector = (AssemblyConnector) connector;
                final AssemblyContext assemblyContextProviding = assemblyConnector
                        .getProvidingAssemblyContext_AssemblyConnector();
                final AssemblyContext assemblyContextRequiring = assemblyConnector
                        .getRequiringAssemblyContext_AssemblyConnector();

                if (assemblyContextProviding.getEncapsulatedComponent__AssemblyContext().getEntityName()
                        .equals("server1")
                        && assemblyContextRequiring.getEncapsulatedComponent__AssemblyContext().getEntityName()
                                .equals("client")) {
                    numOfServer1Client++;
                }

                if (assemblyContextProviding.getEncapsulatedComponent__AssemblyContext().getEntityName()
                        .equals("server2")
                        && assemblyContextRequiring.getEncapsulatedComponent__AssemblyContext().getEntityName()
                                .equals("client")) {
                    numOfServer2Client++;
                }
            }
        }
        return numOfServer1Client + numOfServer2Client;
    }

    private int addClonedServer(final double m) {
        final PCMResourceSetPartition pcmResourceSet = readPcmModelAndApplyTransformationRules(m, TRANSFORMATION_RULES_ADD_DUPLICATED_SERVER_PATH, resourceTableManager);

        final Allocation allocation = pcmResourceSet.getAllocation();
        int numOfIServerProviders = 0;
        for (final Connector connector : allocation.getSystem_Allocation().getConnectors__ComposedStructure()) {
            if (connector instanceof AssemblyConnector) {

                final AssemblyConnector assemblyConnector = (AssemblyConnector) connector;
                final AssemblyContext assemblyContextProviding = assemblyConnector
                        .getProvidingAssemblyContext_AssemblyConnector();
                final AssemblyContext assemblyContextRequiring = assemblyConnector
                        .getRequiringAssemblyContext_AssemblyConnector();

                if (assemblyContextProviding.getEncapsulatedComponent__AssemblyContext().getEntityName()
                        .equals("server1")
                        && assemblyContextRequiring.getEncapsulatedComponent__AssemblyContext().getEntityName()
                                .equals("client")) {
                    numOfIServerProviders++;
                }
            }
        }
        return numOfIServerProviders;
    }

    /**
     * Performs scaling up with the measurement passed as parameter. If the measurement is over the
     * threshold the scaling up should be performed, otherwise not.
     *
     * @param m
     *            measurement that defines whether the scaling up is performed or not.
     * @return processing resource of the server that is to be scaled up.
     */
    private double scaleUp(final double m) {
        final PCMResourceSetPartition pcmResourceSet = readPcmModelAndApplyTransformationRules(m, TRANSFORMATION_RULES_SCALE_UP_PATH, resourceTableManager);

        final Allocation allocation = pcmResourceSet.getAllocation();
        final ResourceEnvironment resourceEnvironment = allocation.getTargetResourceEnvironment_Allocation();
        final Iterator<ResourceContainer> iteratorResourceContainer = resourceEnvironment
                .getResourceContainer_ResourceEnvironment().iterator();
        while (iteratorResourceContainer.hasNext()) {
            final ResourceContainer resourceContainer = iteratorResourceContainer.next();
            if (resourceContainer.getEntityName().equals(SERVER_RESOURCE_CONTAINER_NAME)) {
                final Iterator<ProcessingResourceSpecification> iteratorProcessingResourceSpecification = resourceContainer
                        .getActiveResourceSpecifications_ResourceContainer().iterator();
                while (iteratorProcessingResourceSpecification.hasNext()) {
                    final ProcessingResourceSpecification processingResourceSpecification = iteratorProcessingResourceSpecification
                            .next();
                    return Double.parseDouble(processingResourceSpecification
                            .getProcessingRate_ProcessingResourceSpecification().getSpecification());
                }
            }
        }
        fail("The test reached the end!");
        return Double.NaN;
    }

    /**
     * Performs outsourcing with the measurement passed as parameter. If the measurement is over the
     * threshold the outsourcing should be performed, otherwise not.
     *
     * @param m
     *            measurement that defines whether the outsourcing is performed or not.
     * @return branch probability that is to be increased.
     */
    private double outsource(final double m) {
        final PCMResourceSetPartition pcmResourceSet = readPcmModelAndApplyTransformationRules(m, TRANSFORMATION_RULES_OUTSOURCE_PATH, resourceTableManager);
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
                                    if (branchTransition.getEntityName().equals(BRANCH_2_ENTITY_NAME)) {
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
     * parameter "m".
     *
     * @param m
     *            measurement.
     * @return The PCM model after the rules from "testmodel/rules" have been applied.
     */
    private PCMResourceSetPartition readPcmModelAndApplyTransformationRules(final double m,
            final String reconfigurationRulesFolderPath, IResourceTableManager resourceTableManager) {
        /*
         * Create a measurement.
         */
        final MeasurementSpecification measurementSpecification = MonitorRepositoryFactory.eINSTANCE
                .createMeasurementSpecification();
        measurementSpecification.setId("_sEx-cMLAEeSZr8oGpigbHA");
        measurementSpecification.setMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        StatisticalCharacterization statisticalCharacterization = MonitorRepositoryFactory.eINSTANCE
                .createArithmeticMean();
        TimeDrivenAggregation timeDrivenAggregation = MonitorRepositoryFactory.eINSTANCE.createTimeDrivenAggregation();
        timeDrivenAggregation.setStatisticalCharacterization(statisticalCharacterization);
        timeDrivenAggregation.setMeasurementSpecification(measurementSpecification);
        timeDrivenAggregation.setWindowIncrement(10d);
        timeDrivenAggregation.setWindowLength(10d);

        final RuntimeMeasurement responeTimeRuntimeMeasurement = RuntimeMeasurementFactory.eINSTANCE
                .createRuntimeMeasurement();
        responeTimeRuntimeMeasurement.setId("");
        responeTimeRuntimeMeasurement.setMeasuringValue(m);
        responeTimeRuntimeMeasurement.setMeasurementSpecification(measurementSpecification);

        URI reconfRulesURI = URI.createPlatformPluginURI(reconfigurationRulesFolderPath, false);

        /*
         * Read in the PCM model.
         */
        final PCMResourceSetPartition pcmResourceSet = new PCMResourceSetPartition();
        pcmResourceSet.loadModel(repositoryURI);
        pcmResourceSet.loadModel(resourceEnvironmentURI);
        pcmResourceSet.loadModel(systemURI);
        pcmResourceSet.loadModel(allocationURI);
        final TreeIterator<EObject> pcmModelIterator = pcmResourceSet.getRepositories().get(0).eAllContents();
        EObject monitoredElement = null;
        while (pcmModelIterator.hasNext()) {
            final EObject element = pcmModelIterator.next();
            final EAttribute id = element.eClass().getEIDAttribute();
            final Object idAttribute = element.eGet(id);
            if (idAttribute.toString().equals("_1P7G0LwGEeSxGbiYbg6Waw")) {
                monitoredElement = element;
            }
        }

        /*
         * Create the configuration for the QVTo reconfigurator.
         */
        Map<String, Object> configuration = new HashMap<String, Object>();
        configuration.put(ALLOCATION_FILE_CONFIGURATION_KEY,
                Paths.get(allocationURI.path()).toAbsolutePath().toString());

        SimuLizarWorkflowConfiguration swfc = new SimuLizarWorkflowConfiguration(configuration);
        swfc.setMonitorRepositoryFile(Paths.get(pmsURI.path()).toAbsolutePath().toString());
        swfc.setReconfigurationRulesFolder(reconfRulesURI.toString());

        /*
         * Put the PCM model into the MDSD blackboard.
         */
        final MDSDBlackboard blackboard = new MDSDBlackboard();
        blackboard.addPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, pcmResourceSet);
        final PCMPartitionManager pcmPartitionManager = new PCMPartitionManager(blackboard, swfc);
        RuntimeMeasurementModel rmModel = pcmPartitionManager.findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
        rmModel.getMeasurements().add(responeTimeRuntimeMeasurement);
        
        QVTOReconfigurator reconfigurator = new QVTOReconfigurator();
        reconfigurator.setConfiguration(swfc);
        reconfigurator.setPCMPartitionManager(pcmPartitionManager);
        QvtoReconfigurationLoader reconfigurationLoader = new QvtoReconfigurationLoader();
        reconfigurationLoader.load(swfc);
        EList<ModelTransformation<? extends Object>> transformations = new BasicEList<>(
                reconfigurationLoader.getTransformations());
        boolean checkedAndExceuted = reconfigurator.runExecute(transformations, monitoredElement, resourceTableManager);
        assertTrue(checkedAndExceuted, "Reconfiguration was not executed!");

        return pcmResourceSet;
    }
}