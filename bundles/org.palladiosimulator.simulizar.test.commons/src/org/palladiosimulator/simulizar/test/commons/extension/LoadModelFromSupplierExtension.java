package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.core.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.test.commons.annotation.PCMInstanceFromSupplier;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class LoadModelFromSupplierExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        findAnnotation(context.getElement(), PCMInstanceFromSupplier.class)
            .map(an -> SimuLizarTestExtensionCommons.loadInstance(an.value(), context))
            .ifPresent(rsp -> {
                SimuLizarTestExtensionCommons.putObjectIntoStore(context, PCMResourceSetPartition.class, rsp);
                var blackboard = new MDSDBlackboard();
                blackboard.addPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, rsp);
                SimuLizarTestExtensionCommons.putObjectIntoStore(context, MDSDBlackboard.class, blackboard);
            });
    }

}
