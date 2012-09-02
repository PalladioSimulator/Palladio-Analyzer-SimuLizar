package de.upb.pcm.simulizar.launcher.jobs;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.upb.pcm.simulizar.launcher.partitions.SDMResourceSetPartition;

/**
 * Job for loading all sdm models in a specific folder into the blackboard.
 * 
 * @author Joachim Meyer
 * 
 */
public class LoadSDMModelsIntoBlackboardJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

    public static final String SDM_MODEL_PARTITION_ID = "de.upb.pcm.sdm";

    private MDSDBlackboard blackboard;

    private final String path;
    
    private static final Logger logger = Logger.getLogger(LoadSDMModelsIntoBlackboardJob.class);

    /**
     * Constructor
     * 
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    public LoadSDMModelsIntoBlackboardJob(final SimuComWorkflowConfiguration configuration) {
        this.path = configuration.getEventMiddlewareFile();
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        final SDMResourceSetPartition sdmPartition = new SDMResourceSetPartition();

        // final URI pathToSDM = CommonPlugin.asLocalURI(URI.createPlatformResourceURI(new
        // File("/SDM2/").getPath(), true));
        if (!this.path.equals("")) {
            final File pathFile = new File(this.path);
            final String parent = pathFile.getParent();

            final URI pathToSDM = URI.createURI("file:///" + parent);
            final File folder = new File(pathToSDM.toFileString());

            final File[] files = folder.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(final File dir, final String name) {
                    return name.endsWith(".storydiagrams");
                }
            });
            if (files != null && files.length > 0) {
                for (final File file : files) {
                    sdmPartition.loadModel(URI.createFileURI(file.getPath()));
                }
            } else {
                logger.warn ("No SDM models found, SD reconfigurations disabled.");
            }
        }

        this.getBlackboard().addPartition(SDM_MODEL_PARTITION_ID, sdmPartition);

    }

    /**
     * @return returns the blackboard.
     */
    private MDSDBlackboard getBlackboard() {
        return this.blackboard;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#getName()
     */
    @Override
    public String getName() {
        return "Perform SDM Models Load";
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void rollback(final IProgressMonitor monitor) throws RollbackFailedException {

    }

    /**
     * @see de.uka.ipd.sdq.workflow.IBlackboardInteractingJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
     */
    @Override
    public void setBlackboard(final MDSDBlackboard blackboard) {
        this.blackboard = blackboard;

    }

}