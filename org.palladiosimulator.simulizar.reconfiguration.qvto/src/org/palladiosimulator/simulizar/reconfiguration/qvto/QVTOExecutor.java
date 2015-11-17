package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.TransformationCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.TransformationData;

/**
 * QVTo executor helper class that supports executing QVTo reconfiguration rules.
 * 
 * @author Matthias Becker
 * @author Sebastian Lehrig
 * @author Florian Rosenthal
 */
public class QVTOExecutor extends AbstractQVTOExecutor {

    /**
     * Initializes a new instance of the {@link QVTOExecutor} class.
     * 
     * @param knownTransformations
     *            An {@link TransformationCache} which contains all transformation that can be
     *            executed by this instance, might be empty.
     * @param knownModels
     *            A {@link QVToModelCache} that contains all models that can serve as a
     *            transformation parameter.
     */
    public QVTOExecutor(TransformationCache knownTransformations, QVToModelCache knownModels) {
        super(knownTransformations, knownModels);
    }

    /**
     * Executes all QVTo rules found in the configured reconfiguration rule folder.
     * 
     * @param monitoredElement
     *            the monitored PCM model element.
     * @return true if at least one reconfiguration was executed successfully
     */
    public boolean executeRules(final EObject monitoredElement) {
        boolean result = false;
        for (TransformationData data : super.getAvailableTransformations().getAll()) {
            result |= executeTransformation(data);
        }
        return result;
    }
}
