package org.palladiosimulator.simulizar.ui.wizard.projects;

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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.palladiosimulator.simulizar.ui.wizard.natures.SimulizarNature;

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
			addNature(project);
			// list of project directories
			String[] paths = {
					".settings", "monitors", "reconf_qvto", "results", "rules_active", "rules_sd" }; //$NON-NLS-1$ //$NON-NLS-2$
			// creating directories
			addToProjectStructure(project, paths);

			// adding files to the root directory
			String[] filesArray = {
					"lastverteiler.entpacke.seff_diagram",
					"server.entpacke.seff_diagram",
					"Simulizar Example.launch",
					"testSystem.allocation",
					"testSystem.allocation_diagram",
					"testSystem.repository",
					"testSystem.repository_diagram",
					"testSystem.resourceenvironment",
					"testSystem.resourceenvironment_diagram",
					"testSystem.system",
					"testSystem.system_diagram",
					"testSystem.usagemodel",
					"testSystem.usagemodel_diagram" };
			addFiles(project, null, Arrays.asList(filesArray));

			// adding files to the .settings directory
			List<String> filesList = new ArrayList<String>();
			filesList.add("org.eclipse.core.resources.prefs");
			addFiles(project, paths[0], filesList);

			// adding files to the monitors directory
			filesList.clear();
			filesList.add("EvaluationSpecification.pms");
			addFiles(project, paths[1], filesList);

			// adding files to the reconv_qvto directory
			filesList.clear();
			filesList.add("scaleDown.qvto");
			filesList.add("scaleUp.qvto");
			addFiles(project, paths[2], filesList);

			// adding files to the rules_active directory
			filesList.clear();
			filesList.add("outsource.qvto");
			addFiles(project, paths[4], filesList);

			// adding files to the rules_sd directory
			filesList.clear();
			filesList.add("acquireResources.sdm"); filesList.add("acquireResources.sdm_diagram");
			filesList.add("releaseResources.sdm"); filesList.add("releaseResources.sdm_diagram");
			addFiles(project, paths[5], filesList);
		} catch (CoreException e) {
			e.printStackTrace();
			project = null;
		}

		return project;
	}

	private static void addFiles(IProject project, String folderPath,
			List<String> files) {
		IContainer container = null;
		if (folderPath == null || "".equals(folderPath)) {
			container = project;
		} else {
			container = project.getFolder(folderPath);
		}
		StringBuilder sourcePath = new StringBuilder("../structure/");
		if (folderPath != null && !"".equals(folderPath)) {
			sourcePath.append(folderPath).append("/");
		}
		for (String file : files) {
			InputStream inputStream = CustomProjectSupport.class
					.getClassLoader().getResourceAsStream(
							sourcePath.toString() + file);
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
	private static void addToProjectStructure(IProject newProject,
			String[] paths) throws CoreException {
		for (String path : paths) {
			IFolder etcFolders = newProject.getFolder(path);
			createFolder(newProject, etcFolders);
		}
	}

	private static void addNature(IProject project) throws CoreException {
		if (!project.hasNature(SimulizarNature.NATURE_ID)) {
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = SimulizarNature.NATURE_ID;
			description.setNatureIds(newNatures);

			IProgressMonitor monitor = null;
			project.setDescription(description, monitor);
		}
	}
}
