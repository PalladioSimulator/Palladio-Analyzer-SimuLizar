package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.platform.commons.support.AnnotationSupport.findRepeatableAnnotations;

import java.util.Optional;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.test.commons.annotation.UseModelLoader;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class ExtensibleLoadModelExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        var blackboardOpt = SimuLizarTestExtensionCommons.getObjectFromStore(context, MDSDBlackboard.class);
        if (blackboardOpt.isEmpty()) {
            blackboardOpt = Optional.of(new MDSDBlackboard());
            SimuLizarTestExtensionCommons.putObjectIntoStore(context, MDSDBlackboard.class, blackboardOpt.get());
        }
        
        final var blackboard = blackboardOpt.get();
        findRepeatableAnnotations(context.getElement(), UseModelLoader.class).stream()
            .map(an -> SimuLizarTestExtensionCommons.loadInstance(an.value(), context))
            .forEach(loader -> loader.loadModel(blackboard));
    }

}
