package org.palladiosimulator.simulizar.launcher.jobs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.partitions.SDMResourceSetPartition;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Job for loading all sdm models in a specific folder into the blackboard.
 * 
 * @author Joachim Meyer
 * 
 */
public class LoadSDMModelsIntoBlackboardJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

    private static final String STORYDIAGRAMS_FILE_EXTENSION = ".sdm";

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
        this.path = (String) configuration.getAttributes().get(SimulizarConstants.SDM_FOLDER);
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        final SDMResourceSetPartition sdmPartition = new SDMResourceSetPartition();
        this.getBlackboard().addPartition(SDM_MODEL_PARTITION_ID, sdmPartition);

        if (!this.path.equals("")) {
       
        	//add file protocol only if necessary
        	String filePath = path;
        	File folder = null;
        	if (!path.startsWith("platform:")) {
        		filePath = "file:///" + filePath;
        		
        		URI pathToSDM = URI.createURI(filePath);
                folder = new File(pathToSDM.toFileString());
        	}
        	else {
        	    try {
                    URL pathURL = FileLocator.resolve(new URL(this.path));
                    String folderString = pathURL.toExternalForm().replace("file:", "");
                    folder = new File(folderString);
                } catch (IOException e) {
                    logger.warn ("No SDM models found, SD reconfigurations disabled.", e);
                    return;
                }
        	}
            
        	if (!folder.exists()) {
        	    logger.warn("Folder "+folder+" does not exist. No reconfiguration rules will be loaded.");
        	    return;
        	}
            final File[] files = folder.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(final File dir, final String name) {
                    return name.endsWith(STORYDIAGRAMS_FILE_EXTENSION);
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
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {

    }

    /**
     * @see de.uka.ipd.sdq.workflow.IBlackboardInteractingJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
     */
    @Override
    public void setBlackboard(final MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
    }

}