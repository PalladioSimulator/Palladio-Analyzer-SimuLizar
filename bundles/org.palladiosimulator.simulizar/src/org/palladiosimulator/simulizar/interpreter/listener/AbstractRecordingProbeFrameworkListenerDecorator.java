package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.AbstractRecorderConfiguration;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.recorderframework.utils.RecorderExtensionHelper;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;

/**
 * This class has to be implemented by all clients that wish to have additional measurements
 * recorded, i.e., by all classes that make use of the
 * {@code org.palladiosimulator.simulizar.interpreter.listener.probeframework} extension point.
 *
 * @author Florian Rosenthal
 *
 */
public abstract class AbstractRecordingProbeFrameworkListenerDecorator {

    private AbstractProbeFrameworkListener probeFrameworkListener;

    /**
     * Injects the probe framework listener that is decorated by this class.
     *
     * @param probeFrameworkListener
     *            The {@link ProbeFrameworkListener} used during the current simulation run.
     */
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener probeFrameworkListener) {
        this.probeFrameworkListener = probeFrameworkListener;
    }

    /**
     * Registers additional measurements to be observed/stored by the probe framework listener.<br>
     * This method is called by the {@link ProbeFrameworkListener} in order to have additional
     * measurements attached.
     */
    public void registerMeasurements() {
        if (this.probeFrameworkListener == null) {
            throw new IllegalStateException("No ProbeFrameworkListener at hand. "
                    + "Ensure that setProbeFrameworkListener(..) has been called beforehand.");
        }
    }

    /**
     * Gets the current {@link ProbeFrameworkListener} instance.
     *
     * @return The current {@link AbstractProbeFrameworkListener} that has been passed to this
     *         instance via {@link #setProbeFrameworkListener(AbstractProbeFrameworkListener)}.
     */
    protected AbstractProbeFrameworkListener getProbeFrameworkListener() {
        return this.probeFrameworkListener;
    }
    
    /**
     * Gets the current {@link ProbeFrameworkContext}.
     * 
     * @return the {@link ProbeFrameworkContext} of the running simulation.
     */
    protected ProbeFrameworkContext getProbeFrameworkContext() {
        return this.probeFrameworkListener.simuComModel.getProbeFrameworkContext();
    }

    /**
     * Template method to instantiate and initialize a {@link IRecorder} implementation based on the
     * {@link SimuComConfig} of the current SimuLizar run.
     *
     * @param recorderConfigMap
     *            A {@link Map} which contains the recorder configuration attributes.
     * @return An {@link IRecorder} initialized with the given configuration.
     * @see #instantiateRecorder(SimuComConfig)
     * @see #createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(MetricDescription,
     *      MeasuringPoint)
     */
    protected IRecorder initializeRecorder(final Map<String, Object> recorderConfigMap) {
        assert recorderConfigMap != null;

        SimuComConfig config = this.probeFrameworkListener.getSimuComModel().getConfiguration();
        IRecorder recorder = instantiateRecorder(config);
        recorder.initialize(createRecorderConfiguration(config, recorderConfigMap));

        return recorder;
    }

    /**
     * Method to instantiate an {@link IRecorder} used for the recording of the measurements. This
     * method is called within {@link #initializeRecorder(Map)} to the initialization of the created
     * recorder.
     * 
     * @param config
     *            The {@link SimuComConfig} which contains the configuration of the current
     *            simulation run.
     * @return This default implementation returns the result of<br>
     *         {@code RecorderExtensionHelper.instantiateRecorderImplementationForRecorder(config.getRecorderName());}
     *         .
     */
    protected IRecorder instantiateRecorder(SimuComConfig config) {
        assert config != null;
        return RecorderExtensionHelper.instantiateRecorderImplementationForRecorder(config.getRecorderName());
    }

    /**
     * Method to obtain an {@link IRecorderConfiguration} used for the recording of the
     * measurements. This method is called within {@link #initializeRecorder(Map)} to initialize the
     * created recorder after it has been instantiated.
     * 
     * @param config
     *            The {@link SimuComConfig} which contains the configuration of the current
     *            simulation run.
     * @param recorderConfigMap
     *            A {@link Map} with the configuration attributes.
     * @return This default implementation returns the result of <br>
     *         {@code config.getRecorderConfigurationFactory().createRecorderConfiguration(recorderConfigMap);}
     *         .
     */
    protected IRecorderConfiguration createRecorderConfiguration(SimuComConfig config,
            Map<String, Object> recorderConfigMap) {
        assert recorderConfigMap != null && config != null;

        return config.getRecorderConfigurationFactory().createRecorderConfiguration(recorderConfigMap);
    }

    /**
     * Registers the given recorder at the given measurement source, i.e., adds it to the list of
     * observers.
     *
     * @param measurementSource
     *            The {@link MeasurementSource} whose measurements shall be recorded.
     * @param recorder
     *            The {@link IRecorder} to record new measurements produced by the given source.
     */
    protected void registerMeasurementsRecorder(final MeasurementSource measurementSource, final IRecorder recorder) {
        assert measurementSource != null && recorder != null;
        measurementSource.addObserver(recorder);
    }

    /**
     * Convenience method to create a recorder configuration map which has the
     * {@link AbstractRecorderConfiguration#RECORDER_ACCEPTED_METRIC} attribute (key) set to the
     * given metric description and the {@link AbstractRecorderConfiguration#MEASURING_POINT}
     * attribute (key) set to the given measuring point.
     *
     * @param recorderAcceptedMetric
     *            The {@link MetricDescription} to be put in the map.
     * @param measuringPoint
     *            The {@link MeasuringPoint} to be put in the map.
     * @return A recorder configuration {@link Map} initialized as described.
     * @see #initializeRecorder(Map)
     */
    public static Map<String, Object> createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
            final MetricDescription recorderAcceptedMetric, final MeasuringPoint measuringPoint) {
        assert recorderAcceptedMetric != null;
        assert measuringPoint != null;

        final Map<String, Object> result = new HashMap<String, Object>();
        result.put(AbstractRecorderConfiguration.RECORDER_ACCEPTED_METRIC, recorderAcceptedMetric);
        result.put(AbstractRecorderConfiguration.MEASURING_POINT, measuringPoint);
        return result;
    }
}
