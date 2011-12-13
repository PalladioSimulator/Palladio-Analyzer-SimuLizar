/**
 * 
 */
package de.upb.pcm.interpreter.ssj;

import umontreal.iro.lecuyer.simevents.Event;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.IEntityDelegate;

/**
 * @author Snowball
 *
 */
public class SSJEntity implements IEntityDelegate {

	private de.uka.ipd.sdq.simucomframework.abstractSimEngine.Entity myAbstractEntity;
	boolean isScheduled = false;
	Event nextEventForThisEntity = null;
	
	public SSJEntity(de.uka.ipd.sdq.simucomframework.abstractSimEngine.Entity entity, String name) {
		// super(owner, name, false);
		this.myAbstractEntity = entity;
	}

	public IEntityDelegate getEntity() {
		return myAbstractEntity;
	}

	public boolean isScheduled() {
		return isScheduled;
	}

	public void reschedule(double d) {
		nextEventForThisEntity.reschedule(d);
	}

}
