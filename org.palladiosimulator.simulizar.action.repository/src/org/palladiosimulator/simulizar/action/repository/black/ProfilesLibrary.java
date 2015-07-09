package org.palladiosimulator.simulizar.action.repository.black;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.modelversioning.emfprofile.Profile;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.core.entity.NamedElement;

public class ProfilesLibrary {

    public ProfilesLibrary() {
        super();
    }

    /**
     * Gets whether the stereotype with the given name is applied to the given pcm entity.
     * @param pcmEntity The {@link Entity} to be checked for stereotype application.
     * @param stereotypeName The name of the stereotype to check for application.
     * @return {@code true} whether the given stereotype is applied, {@code false} otherwise.
     */
    public static boolean hasAppliedStereotype(final Entity pcmEntity, final String stereotypeName) {
        return StereotypeAPI.isStereotypeApplied(pcmEntity, stereotypeName);
    }

    /**
     * Gets whether the stereotype with the given name is applied to any of the given pcm entities.
     * @param pcmEntitySet A {@link Set} of pcm elements to be checked for stereotype application.
     * @param stereotypeName The name of the stereotype to check for application.
     * @return {@code true} whether the given stereotype is applied at least once, {@code false} otherwise.
     */
    public static boolean hasAppliedStereotype(final Set<Entity> pcmEntitySet, final String stereotypeName) {
        return StereotypeAPI.hasAppliedStereotype(pcmEntitySet, stereotypeName);
    }

    public static boolean appliedStereotypesEqualsOne(final Set<Entity> pcmEntitySet, final String stereotypeName) {
        int appliedStereotypes = 0;

        for (final Entity entity : pcmEntitySet) {
            if (StereotypeAPI.isStereotypeApplied(entity, stereotypeName)) {
                if (++appliedStereotypes > 1) {
                    return false;
                }
            }
        }

        return appliedStereotypes == 1;
    }

    private static Profile queryProfileByStereotypeName(Entity pcmEntity, String stereotypeName) {
    	assert stereotypeName != null && pcmEntity != null;
    	
    	Profile result = null;
    	for (Profile profile : ProfileAPI.getApplicableProfiles(pcmEntity.eResource())) {
    		if (profile.getStereotype(stereotypeName) != null) {
    			result = profile;
    			break;
    		}
    	}
    	return result;
    }
    
    private static void ensureProfileApplied(Resource resource, Profile profile) {
    	assert resource != null && profile != null;
    	
    	if (!ProfileAPI.isProfileApplied(resource, profile)) {
    		ProfileAPI.applyProfile(resource, profile);
    	}
    }
    
    private static void ensureProfileApplied(Entity pcmEntity, String stereotypeName) {
    	assert pcmEntity != null && stereotypeName != null;
    	
    	Profile profile = queryProfileByStereotypeName(pcmEntity, stereotypeName);
    	if (profile == null) {
    		throw new IllegalArgumentException("Stereotype with given name '" + stereotypeName + "' does not exist!");
    	}
    	ensureProfileApplied(pcmEntity.eResource(), profile);
    }
    
    /**
     * Applies the stereotype with the given name to the given pcm element.
     * @param pcmEntity The {@link Entity} the stereotype shall be applied to.
     * @param stereotypeName The name of the stereotype to apply.
     * @throws IllegalStateException In case no stereotype with the given name exists.
     */
    public static void applyStereotype(final Entity pcmEntity, final String stereotypeName) {
    	ensureProfileApplied(pcmEntity, stereotypeName);
        StereotypeAPI.applyStereotype(pcmEntity, stereotypeName);
    }

    /**
     * Revokes the application of stereotype with the given name to the given pcm element.<br>
     * This method does nothing, in case the stereotype has not been applied beforehand.
     * @param pcmEntity The {@link Entity} that is affected.
     * @param stereotypeName The name of the stereotype to revoke.
     * @throws IllegalStateException In case no stereotype with the given name exists.
     */
    public static void removeStereotypeApplications(final Entity pcmEntity, final String stereotypeName) {
    	ensureProfileApplied(pcmEntity, stereotypeName);
        if (StereotypeAPI.isStereotypeApplied(pcmEntity, stereotypeName)) {
            StereotypeAPI.unapplyStereotype(pcmEntity, stereotypeName);
        }
    }

    /**
     * Sets the integer tagged value of a stereotype which is applied to the given pcm element.
     * @param pcmEntity The {@link Entity} that is affected.
     * @param value The new value to be set
     * @param stereotypeName The name of the stereotype the tagged value belongs to.
     * @param taggedValueName The name of the integer tagged value to be set.
     */
    public static void setTaggedValue(final Entity pcmEntity, final int value, final String stereotypeName, final String taggedValueName) {
        StereotypeAPI.setTaggedValue(pcmEntity, value, stereotypeName, taggedValueName);
    }

    public static int getIntTaggedValue(final Entity pcmEntity, final String taggedValueName, final String stereotypeName) {
        return (int) StereotypeAPI.getTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static double getDoubleTaggedValue(final Entity pcmEntity, final String taggedValueName, final String stereotypeName) {
        return (double) StereotypeAPI.getTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static void delete(final List<NamedElement> rootEObjects, final Entity eObject) {
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
