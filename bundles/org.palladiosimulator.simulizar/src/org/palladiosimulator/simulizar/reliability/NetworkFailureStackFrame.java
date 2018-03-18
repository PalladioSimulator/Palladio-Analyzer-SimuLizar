package org.palladiosimulator.simulizar.reliability;

import org.palladiosimulator.pcm.reliability.NetworkInducedFailureType;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.reliability.MarkovFailureType;

public class NetworkFailureStackFrame extends FailureStackFrame<NetworkInducedFailureType> {

	private LinkingResource link;
	
	public NetworkFailureStackFrame(NetworkInducedFailureType type, LinkingResource link, AbstractAction source) {
		super(type, source);
		this.link = link;
	}

	public LinkingResource getLink() {
		return link;
	}

	public void setLink(LinkingResource link) {
		this.link = link;
	}



	@Override
	public MarkovFailureType translateToMarkovFailureType(FailureStatistics stats) {
		//TODO: Was sind die �brigen parameter?
		this.type.getCommunicationLinkResourceType__NetworkInducedFailureType();
		return stats.getInternalNetworkFailureType(link.getId(), type.getCommunicationLinkResourceType__NetworkInducedFailureType().getId());
	}
	
	

}
