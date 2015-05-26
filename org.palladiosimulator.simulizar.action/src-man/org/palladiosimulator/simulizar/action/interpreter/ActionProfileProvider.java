package org.palladiosimulator.simulizar.action.interpreter;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.palladiosimulator.simulizar.action.core.Action;

public class ActionProfileProvider implements IProfileProvider {

	
	
	private Action action;

	public ActionProfileProvider(Action action) {
		this.action = action;
	}

	@Override
	public Profile getProfile() {
		return action.getTransientStateProfile();
	}

	@Override
	public String getProfileDescription() {
		return action.getTransientStateProfile().getName();
	}

	@Override
	public ProfileLocationType getProfileLocationType() {
		return ProfileLocationType.BUNDLE;
	}

	@Override
	public String getProfileName() {
		return this.action.getTransientStateProfile().getName();
	}

	@Override
	public String getProfileNsURI() {
		return action.getTransientStateProfile().getNsURI();
	}

	@Override
	public Profile loadProfile(ResourceSet rs) {
		Resource resource = rs.getResource(this.action.getTransientStateProfile().eResource()
                .getURI(), true);
		return obtainProfileFromResource(resource);

	}
	
	// TODO FIXME
	private Profile obtainProfileFromResource(Resource resource) {
        return action.getTransientStateProfile();
	}



}
