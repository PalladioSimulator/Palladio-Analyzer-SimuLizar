package org.palladiosimulator.simulizar.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.edp2.repository.local.LocalDirectoryRepositoryHelper;
import org.palladiosimulator.simulizar.action.ui.configuration.ActionRepositoryFileInputConfigBuilder;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.jobs.PCMInterpreterRootCompositeJob;
import org.palladiosimulator.simulizar.power.ui.configuration.PowerInfrastructureRepositoryFileInputConfigBuilder;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class SimulizarRunConfigTest {

    private static final String MODEL_FOLDER = "/org.palladiosimulator.simulizar.tests/testmodel";
    private static final String ALLOCATION_PATH = MODEL_FOLDER + "/server.allocation";
    private static final String USAGE_MODEL_PATH = MODEL_FOLDER + "/server.usagemodel";
    private static final String REPO_PATH = MODEL_FOLDER + "/server.repository";
    private static final String RESOURCE_ENV_PATH = MODEL_FOLDER + "/server.resourceenvironment";
    private static final String SYSTEM_PATH = MODEL_FOLDER + "/server.system";
    private static final String PMS_PATH = MODEL_FOLDER + "/server.pms";
    private static final String RECONFIGURATION_RULES_FOLDER = MODEL_FOLDER + "/rules/";

    private SimuLizarWorkflowConfiguration simulizarConfiguration;
    private MDSDBlackboard blackboard;
    private PCMInterpreterRootCompositeJob simulizarJob;

    private static Repository repo = null;
    private static String repoId = null;
    private static File repoFile = new File("testRepo");

    private static URI allocationUri;
    private static URI usageModelUri;
    private static URI pmsUri;
    private static URI reconfigurationRulesUri;

    @BeforeClass
    public static void setUpBeforeClass() {
        repo = LocalDirectoryRepositoryHelper.initializeLocalDirectoryRepository(repoFile);
        repoId = repo.getId();
        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), repo);

        allocationUri = URI.createPlatformPluginURI(ALLOCATION_PATH, true);
        allocationUri = CommonPlugin.resolve(allocationUri);
        usageModelUri = URI.createPlatformPluginURI(USAGE_MODEL_PATH, true);
        usageModelUri = CommonPlugin.resolve(usageModelUri);
        pmsUri = URI.createPlatformPluginURI(PMS_PATH, true);
        pmsUri = CommonPlugin.resolve(pmsUri);
        reconfigurationRulesUri = URI.createPlatformPluginURI(RECONFIGURATION_RULES_FOLDER, true);
        reconfigurationRulesUri = CommonPlugin.resolve(reconfigurationRulesUri);

    }

    @AfterClass
    public static void tearDownAfterClass() {
        RepositoryManager.removeRepository(RepositoryManager.getCentralRepository(), repo);
        repo = null;
        repoId = null;
        if (repoFile.canRead() && repoFile.canWrite()) {
            if (repoFile.isDirectory()) {
                for (final File f : repoFile.listFiles()) {
                    f.delete();
                }
            }
            repoFile.delete();
        }
    }

    @Before
    public void setUp() throws Exception {
        Map<String, Object> properties = createSimulationProperties();

        this.simulizarConfiguration = new SimuLizarWorkflowConfiguration(properties);
        this.simulizarConfiguration.setAllocationFiles(Arrays.asList(allocationUri.toString()));
        this.simulizarConfiguration.setUsageModelFile(usageModelUri.toString());
        this.simulizarConfiguration.setMonitorRepositoryFile(SimulizarConstants.DEFAULT_MONITOR_REPOSITORY_FILE);
        this.simulizarConfiguration
                .setServiceLevelObjectivesFile(SimulizarConstants.DEFAULT_SERVICELEVELOBJECTIVE_FILE);
        this.simulizarConfiguration.setUsageEvolutionFile(SimulizarConstants.DEFAULT_USAGEEVOLUTION_FILE);
        this.simulizarConfiguration.setSimuComConfiguration(new SimuComConfig(properties, false));

        this.simulizarJob = new PCMInterpreterRootCompositeJob(this.simulizarConfiguration);
        this.blackboard = this.simulizarJob.getBlackboard();
    }

    @Test
    public void testSuccessfulSimulationRunWithoutOptionalArguments() {
        // run the simulation with no optional arguments such as SLO file, action repo,
        // reconfigurations
        // the simulation should finish properly
        try {
            this.simulizarJob.execute(new NullProgressMonitor());
        } catch (Throwable anyException) {
            fail("Unexpected exception thrown by test case:\n---------------------\nType: " + anyException
                    + "\nStack Trace: " + stacktraceToString(anyException) + "\n---------------------");
        }

    }

    @Test
    public void testSuccessfulSimulationRunWithReconfigurationFolder() {
        // run the simulation with just the reconfigurations defined
        // the simulation should finish properly
        this.simulizarConfiguration.setReconfigurationRulesFolder(reconfigurationRulesUri.toString());
        try {
            this.simulizarJob.execute(new NullProgressMonitor());
        } catch (Throwable anyException) {
            fail("Unexpected exception thrown by test case:\n---------------------\nType: " + anyException
                    + "\nStack Trace: " + stacktraceToString(anyException) + "\n---------------------");
        }
    }

    private static Map<String, Object> createSimulationProperties() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(SimuComConfig.SIMULATE_FAILURES, false);
        properties.put(SimuComConfig.SIMULATE_LINKING_RESOURCES, false);
        properties.put(SimuComConfig.USE_FIXED_SEED, false);
        properties.put(SimuComConfig.PERSISTENCE_RECORDER_NAME, "Experiment Data Persistency & Presentation (EDP2)");
        properties.put("EDP2RepositoryID", repoId);
        properties.put(SimuComConfig.SIMULATOR_ID, "de.uka.ipd.sdq.codegen.simucontroller.simulizar");
        properties.put(SimuComConfig.EXPERIMENT_RUN, SimuComConfig.DEFAULT_EXPERIMENT_RUN);
        properties.put(SimuComConfig.SIMULATION_TIME, SimuComConfig.DEFAULT_SIMULATION_TIME);
        properties.put(SimuComConfig.MAXIMUM_MEASUREMENT_COUNT, SimuComConfig.DEFAULT_MAXIMUM_MEASUREMENT_COUNT);
        properties.put(SimuComConfig.VERBOSE_LOGGING, false);
        properties.put(SimuComConfig.VARIATION_ID, SimuComConfig.DEFAULT_VARIATION_NAME);
        properties.put(ActionRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE, "");
        properties.put(SimulizarConstants.RECONFIGURATION_RULES_FOLDER,
                SimulizarConstants.DEFAULT_RECONFIGURATION_RULES_FOLDER);
        properties.put(PowerInfrastructureRepositoryFileInputConfigBuilder.INFRASTRUCTURE_MODEL_FILE, "");

        return properties;
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
