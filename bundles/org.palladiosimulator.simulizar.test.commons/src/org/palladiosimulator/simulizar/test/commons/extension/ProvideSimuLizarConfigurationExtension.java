package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfigSupplier;

import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;

public class ProvideSimuLizarConfigurationExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        var job = findAnnotation(context.getElement(), SimulationConfigSupplier.class)
            .map(an -> SimuLizarTestExtensionCommons.loadSupplierInstance(an.value(), context))
            .orElseThrow(() -> new IllegalArgumentException(
                    "The extension was not propery registered. No (meta-)present SimulationConfigSupplier annotation found."));
        SimuLizarTestExtensionCommons.putObjectIntoStore(context, IJobConfiguration.class, job);
    }
}
