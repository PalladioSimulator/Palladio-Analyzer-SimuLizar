package org.palladiosimulator.simulizar.tests;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.edp2.repository.local.LocalDirectoryRepositoryHelper;
import org.palladiosimulator.pcm.allocation.util.AllocationResourceFactoryImpl;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.repository.util.RepositoryResourceFactoryImpl;
import org.palladiosimulator.pcm.resourceenvironment.util.ResourceenvironmentResourceFactoryImpl;
import org.palladiosimulator.pcm.resourcetype.ResourcetypePackage;
import org.palladiosimulator.pcm.resourcetype.util.ResourcetypeResourceFactoryImpl;
import org.palladiosimulator.pcm.system.util.SystemResourceFactoryImpl;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelResourceFactoryImpl;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.tests.jobs.MinimalPCMInterpreterRootCompositeJob;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class SimulizarRunConfigTest {

    private static final String MODEL_FOLDER = "/org.palladiosimulator.simulizar.tests/testmodel";
    private static final String ALLOCATION_PATH = MODEL_FOLDER + "/server.allocation";
    private static final String USAGE_MODEL_PATH = MODEL_FOLDER + "/server.usagemodel";
    private static final String MONITOR_REPO_PATH = MODEL_FOLDER + "/monitors/server.monitorrepository";
    private static final String RECONFIGURATION_RULES_FOLDER = MODEL_FOLDER + "/rules/";
    private static final String USAGE_EVOLUTION_MODEL_PATH = MODEL_FOLDER + "/usageevolution/server.usageevolution";
    private static final String EMPTY_USAGE_EVOLUTION_MODEL_PATH = MODEL_FOLDER
            + "/usageevolution/empty.usageevolution";
    private static final String SLO_REPO_PATH = MODEL_FOLDER + "/slo/server.slo";

    private static final String REPOSITORY_EXTENSION = "repository";
    private static final String RESOURCE_ENVIRONMENT_EXTENSION = "resourceenvironment";
    private static final String SYSTEM_EXTENSION = "system";
    private static final String ALLOCATION_EXTENSION = "allocation";
    private static final String RESOURCETYPE_EXTENSION = "resourcetype";
    private static final String USAGEMODEL_EXTENSION = "usagemodel";

    private static final String PALLADIO_RESOURCETYPES_PATHMAP = "pathmap://PCM_MODELS/Palladio.resourcetype";
    private static final String PALLADIO_RESOURCETYPES_PATHMAP_TARGET = "platform:/plugin/org.palladiosimulator.pcm.resources/defaultModels/Palladio.resourcetype";
    private static final String PRIMITIVE_TYPES_REPO_PATHMAP = "pathmap://PCM_MODELS/PrimitiveTypes.repository";
    private static final String PRIMITIVE_TYPES_REPO_PATHMAP_TARGET = "platform:/plugin/org.palladiosimulator.pcm.resources/defaultModels/PrimitiveTypes.repository";

    private SimuLizarWorkflowConfiguration simulizarConfiguration;
    private SequentialBlackboardInteractingJob<MDSDBlackboard> simulizarJob;

    private Repository repo = null;

    private static URI allocationUri;
    private static URI usageModelUri;
    private static URI monitorRepoUri;
    private static URI reconfigurationRulesUri;
    private static URI usageEvolutionModelUri;
    private static URI emptyUsageEvolutionModelUri;
    private static URI sloRepoUri;

    @BeforeAll
    public static void setUpBeforeClass() {
        Registry.INSTANCE.put(RepositoryPackage.eNS_URI, RepositoryPackage.eINSTANCE);
        Registry.INSTANCE.put(ResourcetypePackage.eNS_URI, ResourcetypePackage.eINSTANCE);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(REPOSITORY_EXTENSION,
                new RepositoryResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(RESOURCE_ENVIRONMENT_EXTENSION,
                new ResourceenvironmentResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(SYSTEM_EXTENSION,
                new SystemResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(ALLOCATION_EXTENSION,
                new AllocationResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(RESOURCETYPE_EXTENSION,
                new ResourcetypeResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(USAGEMODEL_EXTENSION,
                new UsagemodelResourceFactoryImpl());

        final Map<URI, URI> uriMap = URIConverter.URI_MAP;
        uriMap.put(URI.createURI(PALLADIO_RESOURCETYPES_PATHMAP), URI.createURI(PALLADIO_RESOURCETYPES_PATHMAP_TARGET));
        uriMap.put(URI.createURI(PRIMITIVE_TYPES_REPO_PATHMAP), URI.createURI(PRIMITIVE_TYPES_REPO_PATHMAP_TARGET));

        allocationUri = URI.createPlatformPluginURI(ALLOCATION_PATH, true);
        usageModelUri = URI.createPlatformPluginURI(USAGE_MODEL_PATH, true);
        monitorRepoUri = URI.createPlatformPluginURI(MONITOR_REPO_PATH, true);
        reconfigurationRulesUri = URI.createPlatformPluginURI(RECONFIGURATION_RULES_FOLDER, true);
        usageEvolutionModelUri = URI.createPlatformPluginURI(USAGE_EVOLUTION_MODEL_PATH, true);
        emptyUsageEvolutionModelUri = URI.createPlatformPluginURI(EMPTY_USAGE_EVOLUTION_MODEL_PATH, true);
        sloRepoUri = URI.createPlatformPluginURI(SLO_REPO_PATH, true);
    }

    @BeforeEach
    public void setUp(@TempDir File localRepositoryDirectory) throws IOException {
        this.repo = LocalDirectoryRepositoryHelper
                .initializeLocalDirectoryRepository(localRepositoryDirectory);
        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), this.repo);

        final Map<String, Object> properties = createSimulationProperties();

        this.simulizarConfiguration = new SimuLizarWorkflowConfiguration(properties);
        this.simulizarConfiguration.setAllocationFiles(Arrays.asList(allocationUri.toString()));
        this.simulizarConfiguration.setUsageModelFile(usageModelUri.toString());
        this.simulizarConfiguration.setMonitorRepositoryFile(SimulizarConstants.DEFAULT_MONITOR_REPOSITORY_FILE);
        this.simulizarConfiguration
                .setServiceLevelObjectivesFile(SimulizarConstants.DEFAULT_SERVICELEVELOBJECTIVE_FILE);
        this.simulizarConfiguration.setUsageEvolutionFile(SimulizarConstants.DEFAULT_USAGEEVOLUTION_FILE);
        this.simulizarConfiguration.setSimuComConfiguration(new SimuComConfig(properties, false));

        final MDSDBlackboard blackboard = new MDSDBlackboard();
        this.simulizarJob = new MinimalPCMInterpreterRootCompositeJob(this.simulizarConfiguration, blackboard);
    }

    @AfterEach
    public void tearDown() {
        RepositoryManager.removeRepository(RepositoryManager.getCentralRepository(), this.repo);
    }

    @Test
    public void testSuccessfulSimulationRunWithoutOptionalArguments() {
        // run the simulation with no optional arguments such as SLO file, action repo,
        // reconfigurations
        // the simulation should finish properly
        runSuccessfulSimulation();
    }

    @Test
    public void testSuccessfulSimulationRunWithSLO() {
        // run the simulation with just the service level objects defined
        // the simulation should finish properly
        this.simulizarConfiguration.setServiceLevelObjectivesFile(sloRepoUri.toString());
        runSuccessfulSimulation();
    }

    @Test
    public void testSuccessfulSimulationRunWithReconfigurationFolder() {
        // run the simulation with just the reconfigurations defined
        // the simulation should finish properly
        this.simulizarConfiguration.setReconfigurationRulesFolder(reconfigurationRulesUri.toString());
        runSuccessfulSimulation();
    }

    @Test
    public void testSuccessfulSimulationRunWithEmptyReconfigurationFolder(@TempDir File emptyRulesFolder) {
        URI emptyRulesURI = URI.createFileURI(emptyRulesFolder.toPath().normalize().toAbsolutePath().toString());
        this.simulizarConfiguration
                .setReconfigurationRulesFolder(emptyRulesURI.toString());
        runSuccessfulSimulation();
    }

    @Test
    public void testSuccessfulSimulationRunWithUsageEvolution() {
        // run the simulation with just the usage evolution defined
        // the simulation should finish properly
        this.simulizarConfiguration.setUsageEvolutionFile(usageEvolutionModelUri.toString());
        runSuccessfulSimulation();
    }

    @Test
    public void testSuccessfulSimulationRunWithUsageEvolutionNoParamEvolution() {
        // run the simulation with just an empty usage evolution defined
        // the simulation should finish properly
        this.simulizarConfiguration.setUsageEvolutionFile(emptyUsageEvolutionModelUri.toString());
        runSuccessfulSimulation();
    }

    @Test
    public void testSuccessfulSimulationRunWithMonitorRepository() {
        // run the simulation with just the monitors defined
        // the simulation should finish properly
        this.simulizarConfiguration.setMonitorRepositoryFile(monitorRepoUri.toString());
        runSuccessfulSimulation();
    }

    @Test
    public void testSuccessfulSimulationRunWithMonitorRepositoryAndReconfigurationFolder() {
        this.simulizarConfiguration.setReconfigurationRulesFolder(reconfigurationRulesUri.toString());
        this.simulizarConfiguration.setMonitorRepositoryFile(monitorRepoUri.toString());
        runSuccessfulSimulation();
    }

    private Map<String, Object> createSimulationProperties() {
        final Map<String, Object> properties = new HashMap<>();

        properties.put(SimuComConfig.SIMULATE_FAILURES, false);
        properties.put(SimuComConfig.SIMULATE_LINKING_RESOURCES, false);
        properties.put(SimuComConfig.USE_FIXED_SEED, false);
        properties.put(SimuComConfig.PERSISTENCE_RECORDER_NAME,
                org.palladiosimulator.recorderframework.edp2.Activator.EDP2_ID);
        properties.put("EDP2RepositoryID", this.repo.getId());
        properties.put(SimuComConfig.SIMULATOR_ID, "de.uka.ipd.sdq.codegen.simucontroller.simulizar");
        properties.put(SimuComConfig.EXPERIMENT_RUN, SimuComConfig.DEFAULT_EXPERIMENT_RUN);
        properties.put(SimuComConfig.SIMULATION_TIME, "2000");
        properties.put(SimuComConfig.MAXIMUM_MEASUREMENT_COUNT, SimuComConfig.DEFAULT_MAXIMUM_MEASUREMENT_COUNT);
        properties.put(SimuComConfig.VERBOSE_LOGGING, false);
        properties.put(SimuComConfig.VARIATION_ID, SimuComConfig.DEFAULT_VARIATION_NAME);
        properties.put(SimulizarConstants.RECONFIGURATION_RULES_FOLDER,
                SimulizarConstants.DEFAULT_RECONFIGURATION_RULES_FOLDER);
        properties.put("de.uka.ipd.sdq.workflowengine.debuglevel", "1"); // Log level DEBUG

        return properties;
    }

    private void runSuccessfulSimulation() {
        final IProgressMonitor progressMonitor = new NullProgressMonitor();
        try {
            this.simulizarJob.execute(progressMonitor);
        } catch (final Throwable anyException) {
            failDueToException(anyException);
        }
    }

    private static void failDueToException(final Throwable unexpectedException) {
        fail("Unexpected exception thrown by test case:\n---------------------\nType: " + unexpectedException
                + "\nStack Trace: " + stacktraceToString(unexpectedException) + "\n---------------------");
    }

    private static String stacktraceToString(final Throwable exception) {
        final StringBuilder result = new StringBuilder();
        if (exception.getStackTrace() != null) {
            for (final StackTraceElement element : exception.getStackTrace()) {
                result.append(element.toString() + "\n");
            }
        }
        return result.toString().trim();
    }
}
