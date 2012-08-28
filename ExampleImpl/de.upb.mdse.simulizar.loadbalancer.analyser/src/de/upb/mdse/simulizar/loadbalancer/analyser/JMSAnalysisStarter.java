package de.upb.mdse.simulizar.loadbalancer.analyser;

import de.upb.mdse.simulizar.loadbalancer.analyser.filter.BatchFilter;
import de.upb.mdse.simulizar.loadbalancer.analyser.filter.ComputeResponseTimeFilter;
import de.upb.mdse.simulizar.loadbalancer.analyser.filter.MeanFilter;
import kieker.analysis.AnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.filter.forward.TeeFilter;
import kieker.analysis.plugin.reader.jms.JMSReader;
import kieker.common.configuration.Configuration;

public final class JMSAnalysisStarter {

	private static final String CONNECTION_FACTORY_TYPE__HORNETQ = "org.jnp.interfaces.NamingContextFactory";

	/** Default provider URL used by HornetQ */
	private static final String PROVIDER_URL__HORNETQ = "jnp://localhost:1100/";

	private static final String QUEUE_HORNETQ = "monitoring";

	private static String connectionFactory;
	private static String providerUrl;
	private static String queue;

	private JMSAnalysisStarter() {}

	public static void main(final String[] args) {
		JMSAnalysisStarter.configure();
		
		final AnalysisController analysisInstance = new AnalysisController();

		final Configuration logReaderConfiguration = new Configuration();
		logReaderConfiguration.setProperty(JMSReader.CONFIG_PROPERTY_NAME_PROVIDERURL, providerUrl);
		logReaderConfiguration.setProperty(JMSReader.CONFIG_PROPERTY_NAME_FACTORYLOOKUP, connectionFactory);
		logReaderConfiguration.setProperty(JMSReader.CONFIG_PROPERTY_NAME_DESTINATION, queue);

		final JMSReader logReader = new JMSReader(logReaderConfiguration);
		analysisInstance.registerReader(logReader);

		final ComputeResponseTimeFilter rtFilter = new ComputeResponseTimeFilter(new Configuration());
		final BatchFilter batchFilter = new BatchFilter(new Configuration());
		final MeanFilter meanFilter = new MeanFilter(new Configuration());
		final TeeFilter teeFilter = new TeeFilter(new Configuration());
		
		analysisInstance.registerFilter(rtFilter);
		analysisInstance.registerFilter(batchFilter);
		analysisInstance.registerFilter(meanFilter);
		analysisInstance.registerFilter(teeFilter);

		try {
			analysisInstance.connect(logReader, JMSReader.OUTPUT_PORT_NAME_RECORDS, rtFilter, ComputeResponseTimeFilter.INPUT_PORT_NAME_EVENTS);
			analysisInstance.connect(rtFilter, ComputeResponseTimeFilter.OUTPUT_PORT_RESPONSE_TIMES, batchFilter, BatchFilter.INPUT_RESPONSE_TIME_MEASUREMENTS);
			analysisInstance.connect(batchFilter, BatchFilter.OUTPUT_BATCH_MAP, meanFilter, MeanFilter.INPUT_BATCH_MAP);
			analysisInstance.connect(meanFilter, MeanFilter.OUTPUT_MEAN_MAP, teeFilter, TeeFilter.INPUT_PORT_NAME_EVENTS);
			analysisInstance.run();
		} catch (final AnalysisConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses the given arguments and initializes the variables {@link #connectionFactory} and {@link #providerUrl}.
	 * 
	 * @return true iff valid parameters were passed
	 */
	private static boolean configure() {
		connectionFactory = CONNECTION_FACTORY_TYPE__HORNETQ;
		System.out.println("jms-connection-factory:" + connectionFactory);
		providerUrl = PROVIDER_URL__HORNETQ;
		System.out.println("jms-provider-url:      " + providerUrl);
		queue = QUEUE_HORNETQ;
		System.out.println("jms-queue:             " + queue);
		System.out.println();
		return true;
	}
}
