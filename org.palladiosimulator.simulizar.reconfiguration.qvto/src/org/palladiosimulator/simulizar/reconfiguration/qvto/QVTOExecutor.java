package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.List;

import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.TransformationCache;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.QvtoModelTransformation;

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
	public QVTOExecutor(ModelTransformationCache knownTransformations, QVToModelCache knownModels) {
        super(knownTransformations, knownModels);
    }
    
    public boolean executeTransformations(List<QvtoModelTransformation> transformations){
		boolean result = true;
    	for(QvtoModelTransformation transformation : transformations){
			result &= executeTransformation(transformation);
		}
    	return result;
	}
}
