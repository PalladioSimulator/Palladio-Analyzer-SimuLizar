package org.palladiosimulator.simulizar.ui.wizard.znncom.projects;

import java.io.InputStream;
import java.net.URI;
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
import org.palladiosimulator.simulizar.ui.wizard.znncom.natures.SimulizarNature;

public class CustomProjectSupport {
    private static final String FOLDER_ARCHIVE = "archive";
    private static final String FILE_PROJECT = "simulizar_example_znn_com.zip";
    private static final String PATH_RESOURCES = "../resources/";
    private static final String PATH_DELIMITER = "/";

    /**
     * For this marvelous project we need to: - create the default Eclipse project - add the custom
     * project nature - create the folder structure
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
            addToProjectStructure(project, new String[] {
                FOLDER_ARCHIVE
            });
            addFiles(project, FOLDER_ARCHIVE, Arrays.asList(FILE_PROJECT));
        } catch (CoreException e) {
            e.printStackTrace();
            project = null;
        }

        return project;
    }

    private static void addFiles(IProject project, String folderPath, List<String> files) {
        IContainer container = null;
        if (folderPath == null || "".equals(folderPath)) {
            container = project;
        } else {
            container = project.getFolder(folderPath);
        }
        StringBuilder sourcePath = new StringBuilder(PATH_RESOURCES);
        if (folderPath != null && !"".equals(folderPath)) {
            sourcePath.append(folderPath).append(PATH_DELIMITER);
        }
        for (String file : files) {
            InputStream inputStream = CustomProjectSupport.class.getClassLoader().getResourceAsStream(
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

    private static void createFolder(IProject project, IFolder folder) throws CoreException {
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
