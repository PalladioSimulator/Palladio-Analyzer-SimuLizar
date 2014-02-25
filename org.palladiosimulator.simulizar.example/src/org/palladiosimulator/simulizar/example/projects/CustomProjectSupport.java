package org.palladiosimulator.simulizar.example.projects;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

public class CustomProjectSupport {
	/**
	 * For this marvelous project we need to: - create the default Eclipse
	 * project - add the custom project nature - create the folder structure
	 * 
	 * @param projectName
	 * @param location
	 * @param natureId
	 * @return
	 */
	public static IProject createProject(String projectName, URI location) {
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() > 0);

		IProject project = createBaseProject(projectName, location);
		try {
			String[] paths = {"data", ".settings"}; //$NON-NLS-1$ //$NON-NLS-2$
			String[] files0 = {
					"acquireResources.sdm",
					"acquireResources.sdm_diagram",
					"EvaluationSpecification.pms",
					"lastverteiler.entpacke.seff_diagram",
					"releaseResources.sdm",
					"releaseResources.sdm_diagram",
					"resourceProcessingRateReconfigurationStrategy.storydiagrams_diagram",
					"resourceProcessingRateReconfigurationStrategy.storydiagrams1",
					"server.entpacke.seff_diagram", "Simulizar Example.launch",
					"testSystem.allocation", "testSystem.allocation_diagram",
					"testSystem.repository", "testSystem.repository_diagram",
					"testSystem.resourceenvironment",
					"testSystem.resourceenvironment_diagram",
					"testSystem.system", "testSystem.system_diagram",
					"testSystem.usagemodel", "testSystem.usagemodel_diagram" };
			List<String> files2 = new ArrayList<String>();
			files2.add("experiment.ser");
			files2.add("exprun.ser");
			files2.add("id_generator.ser");
			files2.add("sensor.ser");
			files2.add("state.ser");
			for(int i = 0; i <= 3; i++){
				for(int j = 0; j <= 15; j++){
					files2.add("exprun" + i + "_" + j + "_ET.ser");
					files2.add("exprun" + i + "_" + j + "_MEAS.ser");
				}
			}
			for(int i = 5; i <= 46; i++){
				for(int j = 16; j <= 31; j++){
					files2.add("exprun" + i + "_" + j + "_ET.ser");
					files2.add("exprun" + i + "_" + j + "_MEAS.ser");
				}
			}
			List<String> files1 = new ArrayList<String>();
			files1.add("org.eclipse.core.resources.prefs");
			addToProjectStructure(project, paths);
			addFiles(project, null, Arrays.asList(files0));
			addFiles(project, ".settings", files1);
			addFiles(project, "data", files2);
		} catch (CoreException e) {
			e.printStackTrace();
			project = null;
		}

		return project;
	}

	private static void addFiles(IProject project, String folderPath, List<String> files) {
    	IContainer container = null;
    	if(folderPath == null || "".equals(folderPath)){
    		container = project;
    	}else{
    		container = project.getFolder(folderPath);
    	}
    	StringBuilder sourcePath = new StringBuilder("../structure/");
    	if(folderPath != null && !"".equals(folderPath)){
    		sourcePath.append(folderPath).append("/");
    	}
		for(String file : files){
	    	InputStream inputStream = CustomProjectSupport.class.getClassLoader().getResourceAsStream(sourcePath.toString() + file);
	    	IFile f = container.getFile(new Path(file));
	        try {
				f.create(inputStream, false, null);
				inputStream.close();
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}

	/**
	 * Just do the basics: create a basic project.
	 * 
	 * @param location
	 * @param projectName
	 */
	private static IProject createBaseProject(String projectName, URI location) {
		// it is acceptable to use the ResourcesPlugin class
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);

		if (!newProject.exists()) {
			URI projectLocation = location;
			IProjectDescription desc = newProject.getWorkspace()
					.newProjectDescription(newProject.getName());
			if (location != null
					&& ResourcesPlugin.getWorkspace().getRoot()
							.getLocationURI().equals(location)) {
				projectLocation = null;
			}

			desc.setLocationURI(projectLocation);
			try {
				newProject.create(desc, null);
				if (!newProject.isOpen()) {
					newProject.open(null);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		return newProject;
	}

	private static void createFolder(IProject project, IFolder folder)
			throws CoreException {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			createFolder(project, (IFolder) parent);
		}
		if (!folder.exists()) {
			folder.create(false, true, null);
		}
	}

	/**
	 * Create a folder structure with a parent root.
	 * 
	 * @param newProject
	 * @param paths
	 * @throws CoreException
	 */
	private static void addToProjectStructure(IProject newProject, String[] paths) throws CoreException {
		for (String path : paths) {
			IFolder etcFolders = newProject.getFolder(path);
			createFolder(newProject, etcFolders);
		}
	}
}
