package org.palladiosimulator.simulizar.action.interpreter;

import org.eclipse.emf.ecore.resource.Resource;
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

	// TODO FIXME
	private Profile obtainProfileFromResource(Resource resource) {
        return action.getTransientStateProfile();
	}



}
