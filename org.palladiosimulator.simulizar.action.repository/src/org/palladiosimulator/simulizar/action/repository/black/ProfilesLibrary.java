package org.palladiosimulator.simulizar.action.repository.black;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.modelversioning.emfprofile.Profile;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

public class ProfilesLibrary {

    public ProfilesLibrary() {
        super();
    }

    public static boolean hasAppliedStereotype(Entity pcmEntity, String stereotypeName) {
        return StereotypeAPI.isStereotypeApplied(pcmEntity, stereotypeName);
    }

    public static boolean hasAppliedStereotype(Set<Entity> pcmEntitySet, String stereotypeName) {
        return StereotypeAPI.hasAppliedStereotype(pcmEntitySet, stereotypeName);
    }

    public static boolean appliedStereotypesEqualsOne(Set<Entity> pcmEntitySet, String stereotypeName) {
        int appliedStereotypes = 0;

        for (final Entity entity : pcmEntitySet) {
            if (StereotypeAPI.isStereotypeApplied(entity, stereotypeName)) {
                appliedStereotypes++;
            }
        }

        if (appliedStereotypes != 1) {
            return false;
        }
        return true;
    }

    public static void applyStereotype(Entity pcmEntity, String stereotypeName) {
        StereotypeAPI.applyStereotype(pcmEntity, stereotypeName);
    }

    public static void removeStereotypeApplications(Entity pcmEntity, String stereotypeName) {
        if (StereotypeAPI.isStereotypeApplied(pcmEntity, stereotypeName)) {
            StereotypeAPI.unapplyStereotype(pcmEntity, stereotypeName);
        }
    }

    public static void setTaggedValue(Entity pcmEntity, int value, String stereotypeName, String taggedValueName) {
        StereotypeAPI.setTaggedValue(pcmEntity, value, stereotypeName, taggedValueName);
    }

    public static int getIntTaggedValue(Entity pcmEntity, String taggedValueName, String stereotypeName) {
        return (int) StereotypeAPI.getTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static double getDoubleTaggedValue(Entity pcmEntity, String taggedValueName, String stereotypeName) {
        return (double) StereotypeAPI.getTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static void delete(List<NamedElement> rootEObjects, Entity eObject) {
        final Set<EObject> eObjects = new HashSet<EObject>();
        final Set<EObject> crossResourceEObjects = new HashSet<EObject>();
        eObjects.add(eObject);
        for (@SuppressWarnings("unchecked")
        final TreeIterator<InternalEObject> j = (TreeIterator<InternalEObject>) (TreeIterator<?>) eObject
                .eAllContents(); j.hasNext();) {
            final InternalEObject childEObject = j.next();
            if (childEObject.eDirectResource() != null) {
                crossResourceEObjects.add(childEObject);
            } else {
                eObjects.add(childEObject);
            }
        }

        Map<EObject, Collection<EStructuralFeature.Setting>> usages;
        usages = UsageCrossReferencer.findAll(eObjects, rootEObjects);

        for (final Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : usages.entrySet()) {
            final EObject deletedEObject = entry.getKey();
            final Collection<EStructuralFeature.Setting> settings = entry.getValue();
            for (final EStructuralFeature.Setting setting : settings) {
                if (!eObjects.contains(setting.getEObject()) && setting.getEStructuralFeature().isChangeable()) {
                    EcoreUtil.remove(setting, deletedEObject);
                }
            }
        }

        EcoreUtil.remove(eObject);

        for (final EObject crossResourceEObject : crossResourceEObjects) {
            EcoreUtil.remove(crossResourceEObject.eContainer(), crossResourceEObject.eContainmentFeature(),
                    crossResourceEObject);
        }
    }
}
