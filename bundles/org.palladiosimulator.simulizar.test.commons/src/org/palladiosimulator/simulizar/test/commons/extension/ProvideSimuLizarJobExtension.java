package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationJobSupplier;

import de.uka.ipd.sdq.workflow.jobs.IJob;

public class ProvideSimuLizarJobExtension implements BeforeEachCallback {
    
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        var job = findAnnotation(context.getElement(), SimulationJobSupplier.class)
            .map(an -> SimuLizarTestExtensionCommons.loadSupplierInstance(an.value(), context))
            .orElseThrow(() -> new IllegalArgumentException(
                    "The extension was not propery registered. No (meta-)present SimulationJobSupplier annotation found."));
        SimuLizarTestExtensionCommons.putObjectIntoStore(context, IJob.class, job);
    }
    



}
