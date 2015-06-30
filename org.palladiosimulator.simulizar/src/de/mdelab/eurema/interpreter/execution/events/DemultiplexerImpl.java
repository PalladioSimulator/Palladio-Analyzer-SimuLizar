package de.mdelab.eurema.interpreter.execution.events;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.mdelab.eurema.interpreter.EventQueue;
import de.mdelab.eurema.interpreter.execution.module.scheduler.EventDrivenModuleScheduler;
import de.mdelab.eurema.interpreter.util.EuremaUtils;

/**
 * Implementation of the {@code Demultiplexer}. It forwards events from the
 * global sensor event queue to local event queues based on event type
 * information. The demultiplexer works between the layers 0 and 1. Events
 * emitted by the adaptable software are stored in a global sensor event queue
 * while each module that has a triggering condition and that is located at
 * layer 1 has an individual event queue (a local event queue). Events are
 * <b>not</b> copied while forwarding them.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class DemultiplexerImpl implements Demultiplexer {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(DemultiplexerImpl.class);

	/**
	 * Subscription of event-driven schedulers (that may also be time-driven) of
	 * modules. The key is the event type and the value is the list of
	 * schedulers that are interested in events of this type since the
	 * triggering condition for the module to be scheduled uses such events.
	 */
	private final Map<eurema.EventType, List<EventDrivenModuleScheduler>> eventSubscriptions;

	/**
	 * Queue to which the adaptable software adds events that are immediately
	 * forwarded by the demultiplexer to the local event queues.
	 */
	private final EventQueueInternal globalSensorEventQueue;

	/**
	 * Queue to store all events emitted by the adaptable software for further
	 * processing. The queue might be used by model operation implementations.
	 */
	private final List<eurema.Event> allSensorEvents;

	/**
	 * Indicates whether the demultiplexer should continue processing (
	 * <code>shutdown == false</code>) or stop and shut down (
	 * <code>shutdown == true</code>).
	 */
	private boolean shutdown = false;

	/**
	 * Constructor.
	 * 
	 * @param globalSensorEventQueue
	 *            the queue to which the adaptable software sends sensor events.
	 * @param eventSubscriptions
	 *            the subscriptions mapping event types to schedulers that
	 *            interested in events of these types
	 */
	public DemultiplexerImpl(EventQueueInternal globalSensorEventQueue,
			Map<eurema.EventType, List<EventDrivenModuleScheduler>> eventSubscriptions) {
		this.eventSubscriptions = eventSubscriptions;
		this.globalSensorEventQueue = globalSensorEventQueue;
		this.allSensorEvents = new LinkedList<>();
	}

	/**
	 * Demultiplexes events emitted by the adaptable software from the global
	 * queue to the individual local queues of the layer 1 modules. The
	 * demultiplexing is based on the subscriptions of the local queues, i.e.,
	 * the mapping from types of events to the local queues.
	 */
	// demux the events emitted by the adaptable software
	@Override
	public void run() {
		while (!this.shutdown) {
			try {
				eurema.Event eEvent = this.globalSensorEventQueue.take();
				logger.debug("Processing the following event emitted by the adaptable software: "
						+ EuremaUtils.eventToString(eEvent));
				eurema.EventType eEventType = eEvent.getType();

				List<EventDrivenModuleScheduler> subscribers = this.eventSubscriptions.get(eEventType);
				String msg = "Demultiplexing of sensor event "
						+ EuremaEventTypeHierarchy.INSTANCE.getEEventAsString(eEvent) + " to " + subscribers.size()
						+ " subscribing modules: ";
				for (EventDrivenModuleScheduler subscriber : subscribers) {
					EventQueue queue = subscriber.getLocalSensorEventQueue();
					queue.add(eEvent);
					msg += subscriber.getExecutable().getEModule().getName() + ", ";
				}
				logger.debug(msg.subSequence(0, msg.length() - 2));
				// add event to the monitoring queue
				this.allSensorEvents.add(eEvent);
			} catch (InterruptedException e) {
				logger.debug(e.getMessage());
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<eurema.Event> getAllSensorEvents() {
		return Collections.unmodifiableList(this.allSensorEvents);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shutDown() {
		this.shutdown = true;
	}

}
