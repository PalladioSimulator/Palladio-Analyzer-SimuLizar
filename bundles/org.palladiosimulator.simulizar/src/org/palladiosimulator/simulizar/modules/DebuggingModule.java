package org.palladiosimulator.simulizar.modules;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

/**
 * This dagger module binds implementations supporting the debugging of SimuLizar simulations.
 */
@Module
public interface DebuggingModule {

    /**
     * In case debug logging is activated, an instance of the {@link LogDebugListener} is bound into
     * the set of {@link IInterpreterListener}s.
     */
    @Provides
    @ElementsIntoSet
    static Set<IInterpreterListener> provideDebugListener(Lazy<LogDebugListener> impl) {
        Set<IInterpreterListener> result = new HashSet<>();
        if (Logger.getLogger(LogDebugListener.class).isDebugEnabled()) {
            result.add(impl.get());
        }
        return result;
    }
}
