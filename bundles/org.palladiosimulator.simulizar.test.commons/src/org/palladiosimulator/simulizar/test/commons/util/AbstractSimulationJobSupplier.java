package org.palladiosimulator.simulizar.test.commons.util;

import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IJob;

public abstract class AbstractSimulationJobSupplier<ConfigurationType extends IJobConfiguration>
        implements Supplier<IJob> {

    protected final Optional<ConfigurationType> configuration;

    public AbstractSimulationJobSupplier(Optional<Class<ConfigurationType>> configurationClass, ExtensionContext context) {
        configuration = configurationClass.map(c -> SimuLizarTestExtensionCommons
            .getObjectFromStore(context, IJobConfiguration.class)
            .filter(c::isInstance)
            .map(c::cast)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Configuration present repository initialized. Please make sure to annotate your test accordingly.")));

    }

}
