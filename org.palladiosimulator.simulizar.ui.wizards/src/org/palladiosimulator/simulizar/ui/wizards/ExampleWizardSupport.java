package org.palladiosimulator.simulizar.ui.wizards;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;
import org.osgi.framework.Bundle;
import org.palladiosimulator.simulizar.ui.wizards.nature.SimulizarNature;

public class ExampleWizardSupport {
	/**
	 * For this marvelous project we need to: - create the default Eclipse
	 * project - add the custom project nature - create the folder structure
	 * 
	 * @param projectName
	 * @param location
	 * @return
	 * @throws CoreException
	 */
	public static IProject createProject(String projectName, URI location, String archivePath) {
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() > 0);
		IProject project = createBaseProject(projectName, location);
		try {
			addNature(project);
			ZipFile file = null;
			try {
				Bundle b = Activator.getDefault().getBundle();
				URL u = b.getEntry(archivePath);
				URL ur = FileLocator.toFileURL(u);
				file = new ZipFile(ur.getFile());
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
			ZipFileStructureProvider provider = new ZipFileStructureProvider(file);
			IPath containerPath = project.getFullPath();
			Object source = provider.getRoot();
			IOverwriteQuery query = new IOverwriteQuery() {
				@Override
				public String queryOverwrite(String path) {
					return IOverwriteQuery.ALL;
				};
			};
			ImportOperation operation = new ImportOperation(containerPath, source, provider, query);
			operation.setCreateContainerStructure(true);
			try {
				operation.run(null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (CoreException e) {
			e.printStackTrace();
			project = null;
		}

		return project;
	}

	/**
	 * Just do the basics: create a basic project.
	 * 
	 * @param location
	 * @param projectName
	 */
	private static IProject createBaseProject(String projectName, URI location) {
		// it is acceptable to use the ResourcesPlugin class
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

		if (!newProject.exists()) {
			URI projectLocation = location;
			IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
			if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
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
