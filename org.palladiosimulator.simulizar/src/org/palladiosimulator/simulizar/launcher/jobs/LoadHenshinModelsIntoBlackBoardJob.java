package org.palladiosimulator.simulizar.launcher.jobs;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.partitions.HenshinResourceSetPartition;
import org.palladiosimulator.simulizar.utils.FileUtil;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class LoadHenshinModelsIntoBlackBoardJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

    private static final String HENSHIN_FILE_EXTENSION = ".henshin";

    public static final String HENSHIN_MODEL_PARTITION_ID = "org.palladiosimulator.simulizar.reconfiguration.henshin";

    private MDSDBlackboard blackboard;

    private final String path;

    public LoadHenshinModelsIntoBlackBoardJob(final SimuComWorkflowConfiguration configuration) {
        this.path = (String) configuration.getAttributes().get(SimulizarConstants.RECONFIGURATION_RULES_FOLDER);
    }

    @Override
    public void setBlackboard(MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
    }

    /**
     * @return returns the blackboard.
     */
    private MDSDBlackboard getBlackboard() {
        return this.blackboard;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
        // TODO Do we need to do anything to clean up?
    }

    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {

        final HenshinResourceSetPartition henshinPartition = new HenshinResourceSetPartition();
        this.getBlackboard().addPartition(HENSHIN_MODEL_PARTITION_ID, henshinPartition);

        final File folder = FileUtil.getFolder(this.path);
        final File[] files = FileUtil.getFiles(folder, HENSHIN_FILE_EXTENSION);

        if (files != null && files.length > 0) {
            for (final File file : files) {
                henshinPartition.loadModel(URI.createURI(file.getPath()));
                // this.blackboard.add(this.henshinResourceSet.getModule(file.getPath(), false));
            }
        } else {
            throw new JobFailedException();
        }

    }

    @Override
    public String getName() {
        return "Load Henshin reconfiguration rules";
    }

}
