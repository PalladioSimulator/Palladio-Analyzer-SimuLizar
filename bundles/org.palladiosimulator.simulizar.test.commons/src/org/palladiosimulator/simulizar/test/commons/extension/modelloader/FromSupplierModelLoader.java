package org.palladiosimulator.simulizar.test.commons.extension.modelloader;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.test.commons.annotation.PCMInstanceFromSupplier;
import org.palladiosimulator.simulizar.test.commons.extension.AbstractModelLoader;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class FromSupplierModelLoader extends AbstractModelLoader<PCMInstanceFromSupplier> {
    public FromSupplierModelLoader(ExtensionContext context) {
        super(context, PCMInstanceFromSupplier.class);
    }

    @Override
    public void loadModel(MDSDBlackboard blackboard) {
        for (var supplier : annotations) {
            var partition = SimuLizarTestExtensionCommons.loadSupplierInstance(supplier.value(), context);
            addOrMergePartitionIntoBlackboard(partition, supplier.partitionId(), blackboard);
        }
    }

}
