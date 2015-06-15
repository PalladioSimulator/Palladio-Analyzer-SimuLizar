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
        return pcmEntitySet.stream().anyMatch(entity -> StereotypeAPI.isStereotypeApplied(entity, stereotypeName));
    }

    public static boolean appliedStereotypesEqualsOne(Set<Entity> pcmEntitySet, String stereotypeName) {
        return pcmEntitySet.stream().filter(pcmEntity -> hasAppliedStereotype(pcmEntity, stereotypeName)).count() == 1;
    }

    public static void applyStereotype(Entity pcmEntity, String stereotypeName) {
        Collection<Profile> applicableProfiles = ProfileAPI.getApplicableProfiles(pcmEntity.eResource());
        applicableProfiles.stream().filter(p -> Objects.nonNull(p.getStereotype(stereotypeName))).findAny()
                .ifPresent(profile -> {
                    EList<Profile> profileList = new BasicEList<Profile>(1);
                    profileList.add(profile);
                    ProfileAPI.updateProfileApplications(pcmEntity.eResource(), profileList);
                    if (StereotypeAPI.isStereotypeApplicable(pcmEntity, stereotypeName)) {
                        StereotypeAPI.applyStereotype(pcmEntity, stereotypeName);
                    }
                });
        // final Stereotype stereotype = pcmEntity
        // .getApplicableStereotype(stereotypeName);
        // if(stereotype != null) {
        // pcmEntity.applyStereotype(stereotype);
        // pcmEntity.saveContainingProfileApplication();
        // }
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
        Set<EObject> eObjects = new HashSet<EObject>();
        Set<EObject> crossResourceEObjects = new HashSet<EObject>();
        eObjects.add(eObject);

        @SuppressWarnings("unchecked")
        TreeIterator<InternalEObject> allContents = (TreeIterator<InternalEObject>) (TreeIterator<?>) eObject
                .eAllContents();
        allContents.forEachRemaining(childEObject -> {
            if (childEObject.eDirectResource() != null) {
                crossResourceEObjects.add(childEObject);
            } else {
                eObjects.add(childEObject);
            }
        });

        Map<EObject, Collection<EStructuralFeature.Setting>> usages = UsageCrossReferencer.findAll(eObjects,
                rootEObjects);

        usages.forEach((deletedEObject, settingsCollection) -> settingsCollection
                .stream()
                .filter(setting -> !eObjects.contains(setting.getEObject())
                        && setting.getEStructuralFeature().isChangeable())
                .forEach(setting -> EcoreUtil.remove(setting, deletedEObject)));

        EcoreUtil.remove(eObject);
        crossResourceEObjects.forEach(crossResourceEObject -> EcoreUtil.remove(crossResourceEObject.eContainer(),
                crossResourceEObject.eContainmentFeature(), crossResourceEObject));
    }
}
