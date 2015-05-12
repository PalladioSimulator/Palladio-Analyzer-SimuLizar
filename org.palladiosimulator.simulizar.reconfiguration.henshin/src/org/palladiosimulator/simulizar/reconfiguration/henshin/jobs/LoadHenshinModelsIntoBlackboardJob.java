package org.palladiosimulator.simulizar.reconfiguration.henshin.jobs;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.reconfiguration.henshin.modelaccess.HenshinResourceSetPartition;
import org.palladiosimulator.simulizar.utils.FileUtil;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * 
 * @author Matthias Becker
 *
 */
public class LoadHenshinModelsIntoBlackboardJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

    private static final String HENSHIN_FILE_EXTENSION = ".henshin";

    public static final String HENSHIN_MODEL_PARTITION_ID = "org.palladiosimulator.simulizar.reconfiguration.henshin";

    private static final Logger LOGGER = Logger.getLogger(LoadHenshinModelsIntoBlackboardJob.class);

    private MDSDBlackboard blackboard;

    private final String path;

    public LoadHenshinModelsIntoBlackboardJob(final SimuComWorkflowConfiguration configuration,
            MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
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

        if (this.path != null && !(this.path.equals(""))) {

            // add file protocol only if necessary
            String filePath = path;
            File folder = null;
            if (!path.startsWith("platform:")) {
                filePath = "file:///" + filePath;

                URI pathToSDM = URI.createURI(filePath);
                folder = new File(pathToSDM.toFileString());
            } else {
                String folderString = "";
                try {
                    URL pathURL = FileLocator.resolve(new URL(this.path));
                    folderString = pathURL.toExternalForm().replace("file:", "");
                    folder = new File(folderString);
                } catch (IOException e) {
                    LOGGER.warn("Folder " + folderString + " cannot be accessed.", e);
                    return;
                }
            }

            if (!folder.exists()) {
                LOGGER.warn("Folder " + folder + " does not exist. No reconfiguration rules will be loaded.");
                return;
            }
            final File[] files = FileUtil.getFiles(folder, HENSHIN_FILE_EXTENSION);

            if (files != null && files.length > 0) {
                for (final File file : files) {
                    henshinPartition.loadModel(URI.createFileURI(file.getPath()));
                }
            } else {
                LOGGER.warn("No Henshin reconfiguration rules found. Henshin reconfiguration engine disabled.");
            }
        }

    }

    @Override
    public String getName() {
        return "Load Henshin reconfiguration rules";
    }

}
