package org.palladiosimulator.simulizar.ui.wizards;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
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
	
	/**
	 * The method changes the values of the launch configuration attributes. It does string replacement. 
	 * For the value of every attribute referred by {@code attributeKeys} it replaces {@code stringToReplace} by {@code stringThatReplaces}.
	 * @param attributesKeys keys of attributes to modify.
	 * @param stringToReplace string that will be replaced in the values of attributes referred by {@code attributesKeys}.
	 * @param stringThatReplaces string that replaces the {@code stringToReplace}.
	 * @param readOnlyLaunchConfiguration launchConfiguration that will be modified.
	 * @param writableLaunchConfiguration writable version of the {@code readOnlyLaunchConfiguration}.
	 * @throws CoreException The exception thrown in case of problems with handling the launch configuration.
	 */
	public static void modifyLaunchConfigurationAttributeValues(List<String> attributesKeys, 
																String stringToReplace, 
																String stringThatReplaces, 
																ILaunchConfiguration readOnlyLaunchConfiguration, 
																ILaunchConfigurationWorkingCopy writableLaunchConfiguration) throws CoreException{
		for (String attributeKey : attributesKeys) {
			String attributeValue = readOnlyLaunchConfiguration.getAttribute(attributeKey, "");
			writableLaunchConfiguration.setAttribute(attributeKey,
													 attributeValue.replace(stringToReplace, stringThatReplaces));
		}
		readOnlyLaunchConfiguration.delete();
		writableLaunchConfiguration.doSave();
	}
}
