/**
 * 
 */
package org.palladiosimulator.simulizar.reconfiguration.henshin.modelaccess;

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

    List<Module> modules = new Vector<Module>();

    public List<Module> getModules() {

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
            this.modules.add(module);
        }
        return this.modules;
    }

}
