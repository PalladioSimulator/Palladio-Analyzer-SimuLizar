package de.mdelab.eurema.interpreter;

import java.util.Properties;

import de.mdelab.eurema.interpreter.util.PropertyLoader;

/**
 * Configuration properties of the EUREMA interpreter.
 * 
 * @author thomas vogel
 * @version 0.03
 * 
 */
public class EuremaProperties {

	/**
	 * Properties file.
	 */
	private static String PROPERTIES_FILE = "eurema.properties";

	/*
	 * property keys
	 */

	/**
	 * Property key for the URI of the default {@code eurema.RuntimeEnvironment}
	 * instance.
	 */
	private static String EUREMA_RUNTIME_ENVIRONMENT_URI = "eurema.model.re.URI";

	/**
	 * Property key for the URI of the EUREMA model containing the default type
	 * hierarchy of events used for triggering conditions.
	 */
	private static String EUREMA_EVENT_TYPE_HIERARCHY_URI = "eurema.model.eth.URI";

	/**
	 * The properties loaded from the properties file after invoking
	 * {@link #loadProperties()}.
	 */
	private Properties properties;

	/**
	 * Constructor.
	 */
	EuremaProperties() {

	}

	/**
	 * Loads the properties from the properties file {@link #PROPERTIES_FILE}.
	 */
	void loadProperties() {
		this.properties = PropertyLoader.loadProperties(PROPERTIES_FILE);
	}

	/**
	 * Returns the URI of the default {@code eurema.RuntimeEnvironment}
	 * instance.
	 * 
	 * @return the URI of the default {@code eurema.RuntimeEnvironment}
	 *         instance.
	 */
	String getRuntimeEnvironmentURI() {
		return this.properties.getProperty(EUREMA_RUNTIME_ENVIRONMENT_URI);
	}

	/**
	 * Returns the URI of the EUREMA model containing the default type hierarchy
	 * of events used for triggering conditions.
	 * 
	 * @return the URI of the EUREMA model containing the default type hierarchy
	 *         of events used for triggering conditions.
	 */
	String getEventTypeHierarchyURI() {
		return this.properties.getProperty(EUREMA_EVENT_TYPE_HIERARCHY_URI);
	}

}
