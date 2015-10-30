package org.palladiosimulator.simulizar.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.edp2.repository.local.LocalDirectoryRepositoryHelper;
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

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @BeforeClass
    public static void setUpBeforeClass() {
        allocationUri = URI.createPlatformPluginURI(ALLOCATION_PATH, true);
        usageModelUri = URI.createPlatformPluginURI(USAGE_MODEL_PATH, true);
        monitorRepoUri = URI.createPlatformPluginURI(MONITOR_REPO_PATH, true);
        reconfigurationRulesUri = URI.createPlatformPluginURI(RECONFIGURATION_RULES_FOLDER, true);
        usageEvolutionModelUri = URI.createPlatformPluginURI(USAGE_EVOLUTION_MODEL_PATH, true);
        emptyUsageEvolutionModelUri = URI.createPlatformPluginURI(EMPTY_USAGE_EVOLUTION_MODEL_PATH, true);
        sloRepoUri = URI.createPlatformPluginURI(SLO_REPO_PATH, true);
    }

    @Before
    public void setUp() throws IOException {
        this.repo = LocalDirectoryRepositoryHelper
                .initializeLocalDirectoryRepository(this.tempFolder.newFolder("testRepo"));
        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), this.repo);

        Map<String, Object> properties = createSimulationProperties();

        this.simulizarConfiguration = new SimuLizarWorkflowConfiguration(properties);
        this.simulizarConfiguration.setAllocationFiles(Arrays.asList(allocationUri.toString()));
        this.simulizarConfiguration.setUsageModelFile(usageModelUri.toString());
        this.simulizarConfiguration.setMonitorRepositoryFile(SimulizarConstants.DEFAULT_MONITOR_REPOSITORY_FILE);
        this.simulizarConfiguration
                .setServiceLevelObjectivesFile(SimulizarConstants.DEFAULT_SERVICELEVELOBJECTIVE_FILE);
        this.simulizarConfiguration.setUsageEvolutionFile(SimulizarConstants.DEFAULT_USAGEEVOLUTION_FILE);
        this.simulizarConfiguration.setSimuComConfiguration(new SimuComConfig(properties, false));

        MDSDBlackboard blackboard = new MDSDBlackboard();
        this.simulizarJob = new MinimalPCMInterpreterRootCompositeJob(this.simulizarConfiguration, blackboard);
    }

    @After
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
    public void testSuccessfulSimulationRunWithEmptyReconfigurationFolder() throws IOException {
        File emptyRulesFolder = this.tempFolder.newFolder();
        this.simulizarConfiguration
                .setReconfigurationRulesFolder(emptyRulesFolder.toPath().normalize().toAbsolutePath().toString());
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
        Map<String, Object> properties = new HashMap<>();

        properties.put(SimuComConfig.SIMULATE_FAILURES, false);
        properties.put(SimuComConfig.SIMULATE_LINKING_RESOURCES, false);
        properties.put(SimuComConfig.USE_FIXED_SEED, false);
        properties.put(SimuComConfig.PERSISTENCE_RECORDER_NAME, "Experiment Data Persistency & Presentation (EDP2)");
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
        IProgressMonitor progressMonitor = new NullProgressMonitor();
        try {
            this.simulizarJob.execute(progressMonitor);
        } catch (Throwable anyException) {
            failDueToException(anyException);
        }
    }

    private static void failDueToException(Throwable unexpectedException) {
        fail("Unexpected exception thrown by test case:\n---------------------\nType: " + unexpectedException
                + "\nStack Trace: " + stacktraceToString(unexpectedException) + "\n---------------------");
    }

    private static String stacktraceToString(Throwable exception) {
        StringBuilder result = new StringBuilder();
        if (exception.getStackTrace() != null) {
            for (StackTraceElement element : exception.getStackTrace()) {
                result.append(element.toString() + "\n");
            }
        }
        return result.toString().trim();
    }
}
