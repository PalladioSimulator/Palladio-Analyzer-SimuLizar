package org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourcetype.ResourcetypePackage;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.jobs.LoadSDMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.modelaccess.SDMResourceSetPartition;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.storydriven.storydiagrams.StorydiagramsPackage;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.stoex.StoexPackage;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uni_paderborn.fujaba.muml.reachanalysis.core.HashLevel;
import de.uni_paderborn.fujaba.muml.reachanalysis.reachabilityGraph.ReachabilityGraphState;
import de.uni_paderborn.fujaba.muml.reachanalysis.reachabilityGraph.sdm.StepGraph;
import de.uni_paderborn.fujaba.muml.reachanalysis.sdm.SDMReachabilityComputation;
import de.uni_paderborn.fujaba.muml.reachanalysis.sdm.SDMReachabilityComputationStatistics;
import de.uni_paderborn.fujaba.muml.reachanalysis.sdm.export.SDMReachabilityGraphExporter;

public class SDMReconfigurationSpaceExplorer {

    public static final String SDM_RECONFIGURATION_STATE_SPACE = "org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration";

    // Logger
    Logger LOGGER = Logger.getLogger(SDMReconfigurationSpaceExplorer.class);

    // make GraphViz print-out
    private final boolean printGraph = false;

    // use unchangeable node identification
    private final boolean detectUnchangeableNodes = false;

    private final boolean storeIndexMapping = true;

    // toggle debug printout of the interpreter
    private final boolean debug_printout = false;

    private final MDSDBlackboard blackboard;

    private final String runtimeModelURI = "org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration/models/RuntimeMeasurementModel.prm";

    private final SimuLizarWorkflowConfiguration configuration;

    public SDMReconfigurationSpaceExplorer(final SimuLizarWorkflowConfiguration configuration,
            final MDSDBlackboard blackboard) {
        // Load Repository Package and register it in the ResourceSet
        RepositoryPackage.eINSTANCE.eClass();
        StorydiagramsPackage.eINSTANCE.eClass();
        ResourcetypePackage.eINSTANCE.eClass();
        RuntimeMeasurementPackage.eINSTANCE.eClass();
        StoexPackage.eINSTANCE.eClass();

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("repository", new XMIResourceFactoryImpl());
        m.put("resourcetype", new XMIResourceFactoryImpl());
        m.put("sdm", new XMIResourceFactoryImpl());
        m.put("prm", new XMIResourceFactoryImpl());

        this.configuration = configuration;
        this.blackboard = blackboard;

        LOGGER.info("Blackboard is: " + this.blackboard);

    }

    private List<Activity> loadActivities() {
        final SDMResourceSetPartition sdmPartition = getResourceSetPartition(this.blackboard,
                LoadSDMModelsIntoBlackboardJob.SDM_MODEL_PARTITION_ID);

        if (!(sdmPartition == null)) {
            return sdmPartition.getActivities();
        } else {
            return new LinkedList<Activity>();
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends ResourceSetPartition> T getResourceSetPartition(final MDSDBlackboard blackboard, final String id) {
        return (T) blackboard.getPartition(id);
    }

    private HashSet<EObject> setupInitialGraph() throws IOException {
        final HashSet<EObject> result = new HashSet<EObject>();

        final ResourceSet rs = new ResourceSetImpl();
        final Resource runtimeModelResource = rs.createResource(URI.createPlatformPluginURI(runtimeModelURI, true));

        runtimeModelResource.load(Collections.EMPTY_MAP);
        final List<Repository> repositories = ((PCMResourceSetPartition) getResourceSetPartition(this.blackboard,
                LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID)).getRepositories();
        final Allocation allocation = ((PCMResourceSetPartition) getResourceSetPartition(this.blackboard,
                LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID)).getAllocation();
        final org.palladiosimulator.pcm.system.System system = ((PCMResourceSetPartition) getResourceSetPartition(this.blackboard,
                LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID)).getSystem();
        final ResourceEnvironment resourceenvironment = ((PCMResourceSetPartition) getResourceSetPartition(this.blackboard,
                LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID)).getResourceEnvironment();
        final UsageModel usagemodel = ((PCMResourceSetPartition) getResourceSetPartition(this.blackboard,
                LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID)).getUsageModel();

        final RuntimeMeasurementModel runtimemodel = (RuntimeMeasurementModel) runtimeModelResource.getContents().get(0);

        result.add(allocation);
        result.addAll(repositories);
        result.add(system);
        result.add(resourceenvironment);
        result.add(usagemodel);
        result.add(runtimemodel);

        return result;

    }

    public void computeReconfigurationSpace() throws IOException {

        // create initial graph objects
        final HashSet<EObject> initialGraphObjects = setupInitialGraph();

        // load activities
        final List<Activity> activities = loadActivities();

        // compute the reachability graph
        final SDMReachabilityComputation reachComp = new SDMReachabilityComputation(initialGraphObjects, activities);
        reachComp.setIdentifyUnchangeableSubgraphs(detectUnchangeableNodes);
        reachComp.setStoreMatching(false);
        reachComp.setStoreIndexMap(storeIndexMapping);
        reachComp.setHashLevel(HashLevel.LEVEL1);
        SDMReachabilityComputation.setDEBUG(debug_printout);
        final long sysTime = java.lang.System.currentTimeMillis();
        reachComp.computeReachabilityGraph();
        final long curTime = java.lang.System.currentTimeMillis();

        // make GraphViz print-out
        if (printGraph) {
            final SDMReachabilityGraphExporter exporter = new SDMReachabilityGraphExporter();
            exporter.export(reachComp.getReachabilityGraph());
        }

        // get stats
        final SDMReachabilityComputationStatistics stats = reachComp.getReachabilityComputationStatistics();

        // print stats
        LOGGER.info("-------------------- Summary ----------------");
        LOGGER.info("Total number of reached graphs: " + stats.getNumberOfStates());
        LOGGER.info("Max Graph size:\t\t\t" + stats.getMaximumNumberOfObjectsPerState() + " nodes");
        LOGGER.info("Unchangeable nodes:\t\t" + stats.getNumberOfUnchangeableNodes());
        LOGGER.info("Hash collisions/isomorphisms\t" + stats.getHashCollisions() + "/" + stats.getIsomorphismsFound());
        LOGGER.info("Total time:\t\t\t" + (curTime - sysTime) + "ms");
        LOGGER.info("Time for forEach Transformation:" + stats.getTimeForRuleTransformation() + "ms");
        LOGGER.info("Time for unchangeable nodes:\t" + stats.getTimeForUnchangeableNodeIdentifiation() + "ms");
        LOGGER.info("Time for state exploration:\t" + stats.getTimeForReachabilityGraphComputation() + "ms");
        LOGGER.info("Time for isomorphisms:\t\t" + stats.getTimeForIsomorphismCheck() + "ms");
        LOGGER.info("Time for state copy:\t\t" + stats.getTimeForStateCopy() + "ms");
        LOGGER.info("Time for hash computation\t" + stats.getTimeForHashComputation() + "ms");

        // Save states
        final String temporaryDataLocation = this.configuration.getTemporaryDataLocation();
        final ResourceSetPartition partition = new ResourceSetPartition();

        final EList<ReachabilityGraphState> allReachableStates = reachComp.getReachabilityGraph().getStates();
        final List<URI> partitionURIs = new ArrayList<URI>();
        for (final ReachabilityGraphState state : allReachableStates) {
            final StepGraph models = ((StepGraph) state);
            LOGGER.info("----- State: " + state + " -----");
            // URI modelURI = URI.createPlatformResourceURI(temporaryDataLocation +
            // "/model/PCM_partition_state_"
            // + state.getHash());
            // partitionURIs.add(modelURI);
            // partition.setContents(modelURI, models.getContainedNodes());
            if (LOGGER.isDebugEnabled()) {
                for (final EObject model : models.getContainedNodes()) {
                    LOGGER.debug("Model: " + model);
                }
            }
        }
        // partition.storeAllResources();

        final URI temporaryReachabilityGraphURI = URI.createPlatformResourceURI(temporaryDataLocation
                + "/model/simulizar.reachabilitygraph", true);

        final List<EObject> reachabilityGraph = new ArrayList<EObject>();
        reachabilityGraph.addAll(allReachableStates);

        partition.setContents(temporaryReachabilityGraphURI, reachabilityGraph);
        this.blackboard.addPartition(SDM_RECONFIGURATION_STATE_SPACE, partition);

        /*
         * LOGGER.info("------------------------- Saving models -----" + allReachableStates.size());
         * for (ReachabilityGraphState state : allReachableStates) { StepGraph models = ((StepGraph)
         * state); LOGGER.info("------------------------- State: " + state + " -----"); for (EObject
         * model : models.getContainedNodes()) { ResourceSetPartition reconfigurationRSP = new
         * ResourceSetPartition(); URI modelURI =
         * URI.createPlatformResourceURI(temporaryDataLocation + "/model/" + state.toString() + "/"
         * + model.eClass().toString(), true); reconfigurationRSP.setContents(modelURI, model); } }
         */

    }
}
