package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.AbstractRecorderConfiguration;
import org.palladiosimulator.recorderframework.utils.RecorderExtensionHelper;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;

public abstract class AbstractRecordingProbeFrameworkListenerDecorator implements IProbeFrameworkListenerDecorator {

    private ProbeFrameworkListener probeFrameworkListener;

    @Override
    public void setProbeFrameworkListener(ProbeFrameworkListener probeFrameworkListener) {
        this.probeFrameworkListener = probeFrameworkListener;
    }

    @Override
    public void registerMeasurements() {
        if (this.probeFrameworkListener == null) {
            throw new IllegalStateException("No ProbeFrameworkListener at hand. "
                    + "Ensure that setProbeFrameworkListener(..) has been called beforehand.");
        }
    }

    protected ProbeFrameworkListener getProbeFrameworkListener() {
        return this.probeFrameworkListener;
    }

    /**
     * Instantiates and initializes a {@link IRecorder} implementation based on the
     * {@link SimuComConfig} of the current SimuLizar run.
     * 
     * @param recorderConfigMap
     *            A {@link Map} which contains the recorder configuration attributes.
     * @return An {@link IRecorder} initialized with the given configuration.
     * @see #createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(MetricDescription,
     *      MeasuringPoint)
     */
    protected IRecorder initializeRecorder(Map<String, Object> recorderConfigMap) {
        assert recorderConfigMap != null;

        SimuComConfig config = this.probeFrameworkListener.getSimuComModel().getConfiguration();
        IRecorder recorder = RecorderExtensionHelper.instantiateRecorderImplementationForRecorder(config
                .getRecorderName());
        recorder.initialize(config.getRecorderConfigurationFactory().createRecorderConfiguration(recorderConfigMap));

        return recorder;
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
    protected void registerMeasurementsRecorder(MeasurementSource measurementSource, IRecorder recorder) {
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
    protected static Map<String, Object> createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
            MetricDescription recorderAcceptedMetric, MeasuringPoint measuringPoint) {
        assert recorderAcceptedMetric != null;
        assert measuringPoint != null;

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(AbstractRecorderConfiguration.RECORDER_ACCEPTED_METRIC, recorderAcceptedMetric);
        result.put(AbstractRecorderConfiguration.MEASURING_POINT, measuringPoint);
        return result;
    }
}
