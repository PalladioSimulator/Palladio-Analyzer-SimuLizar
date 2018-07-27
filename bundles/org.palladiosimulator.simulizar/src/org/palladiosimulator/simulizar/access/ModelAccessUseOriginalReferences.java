package org.palladiosimulator.simulizar.access;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * The class serves the same purpose as its superclass. It differs in that, that it uses its internal EMFCopyHelper
 * instead of org.palladiosimulator.commons.emfutils.EMFCopyHelper. The one used here uses original references when
 * copying models.
 *
 * @author Igor Rogic
 */
public class ModelAccessUseOriginalReferences extends ModelAccess {

    private static final Logger LOGGER = Logger.getLogger(ModelAccessUseOriginalReferences.class.getName());

    public ModelAccessUseOriginalReferences(final MDSDBlackboard blackboard){
    	super(blackboard);
    }
    
    private static final class EMFCopyHelper {

        public static List<EObject> deepCopyEObjectList(final List<EObject> roots) {
            final Copier copier = new Copier(true, true);
            final List<EObject> result = (List<EObject>) copier.copyAll(roots);
            copier.copyReferences();
            return result;
        }

        public static List<EObject> deepCopyToEObjectList(final ResourceSet originalResourceSet) {
            EcoreUtil.resolveAll(originalResourceSet);
            final List<EObject> roots = new LinkedList<EObject>();
            for (final Resource r : originalResourceSet.getResources()) {
                for (final EObject root : r.getContents()) {
                    roots.add(root);
                }
            }
            return deepCopyEObjectList(roots);
        }

    }

    /**
     * @return a copy of the global PCM modelling partition
     */
    protected PCMResourceSetPartition copyPCMPartition() {
        LOGGER.debug("Take a new copy of the global PCM for new simulation threads");
        final PCMResourceSetPartition newPartition = new PCMResourceSetPartition();
        final List<EObject> modelCopy = EMFCopyHelper.deepCopyToEObjectList(getGlobalPCMModel().getResourceSet());
        for (int i = 0; i < modelCopy.size(); i++) {
            final Resource resource = newPartition.getResourceSet().createResource(URI.createFileURI("/temp" + i));
            resource.getContents().add(modelCopy.get(i));
        }
        return newPartition;
    }
   }
