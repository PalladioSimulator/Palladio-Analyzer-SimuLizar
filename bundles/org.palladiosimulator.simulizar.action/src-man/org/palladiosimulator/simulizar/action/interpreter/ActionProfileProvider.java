package org.palladiosimulator.simulizar.action.interpreter;

import org.eclipse.emf.ecore.resource.Resource;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;

public class ActionProfileProvider implements IProfileProvider {

    private final AdaptationBehavior adaptationBehavior;

    public ActionProfileProvider(AdaptationBehavior action) {
        this.adaptationBehavior = action;
    }

    @Override
    public Profile getProfile() {
        return adaptationBehavior.getTransientStateProfile();
    }

    @Override
    public ProfileLocationType getProfileLocationType() {
        return ProfileLocationType.BUNDLE;
    }

    @Override
    public String getProfileName() {
        return this.adaptationBehavior.getTransientStateProfile().getName();
    }

    @Override
    public String getProfileNsURI() {
        return adaptationBehavior.getTransientStateProfile().getNsURI();
    }
}
