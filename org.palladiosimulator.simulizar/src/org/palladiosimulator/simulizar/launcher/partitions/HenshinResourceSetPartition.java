/**
 * 
 */
package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.model.Module;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * @author Matthias Becker
 *
 */
public class HenshinResourceSetPartition extends ResourceSetPartition {

    private List<Module> createModules(String path) {
        /*
         * 
         * final File folder = FileUtil.getFolder(path); final File[] files =
         * FileUtil.getFiles(folder, HENSHIN_FILE_EXTENSION);
         * 
         * List<Module> modules = new LinkedList<Module>(); if (files != null && files.length > 0) {
         * for (final File file : files) {
         * modules.add(this.henshinResourceSet.getModule(file.getPath(), false)); } } return
         * modules;
         */
        List<Module> modules = new Vector<Module>();
        for (final Resource resource : this.rs.getResources()) {
            Module module = null;

            if (resource != null) {
                for (EObject object : resource.getContents()) {
                    if (object instanceof Module) {
                        module = (Module) object;
                        break;
                    }
                }
            }
            modules.add(module);
        }
        return modules;
    }

}
