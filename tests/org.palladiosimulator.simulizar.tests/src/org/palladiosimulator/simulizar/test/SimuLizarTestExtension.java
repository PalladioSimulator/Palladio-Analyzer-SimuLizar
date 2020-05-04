package org.palladiosimulator.simulizar.test;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;
import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.test.annotation.Identified;
import org.palladiosimulator.simulizar.test.annotation.MockSimulation;
import org.palladiosimulator.simulizar.test.annotation.Named;
import org.palladiosimulator.simulizar.test.annotation.PCMInstanceFromSupplier;
import org.palladiosimulator.simulizar.test.annotation.PredicateFactory;

import com.google.common.collect.Streams;

/**
 * The SimuLizarMockExtension uses JUnit 5 Extension API to facilitate tests of SimuLizar internals
 * 
 * To enable use of this extension annotate your test class with
 * <code>@ExtendWith(SimuLizarTestExtension.class)</code>
 * 
 * By annotating a test method with <code>@MockSimulation</code> mocks of the required SimuLizar
 * internals are created which allow to test i. e. the behavior of model syncers.
 * 
 * In particular, the mocked infrastructure contains:
 * <ul>
 * <li>Functioning ResourceRegistry, where factory methods <code>createXXX</code> return and
 * register mock implementations</li>
 * <li>PCMModelManager providing the pcm instance as global pcm model</li>
 * <li>SimuLizarRuntimeState, SimuComModel mocked to provide the above</li>
 * </ul>
 * 
 * In a test method, if you want to access a mock, simply add it as a parameter to your test method.
 * Furthermore, you can similarly get access to a model element. EMF model elements are retrieved
 * from the ResourceSet by means of filtering by the parameter type. Additionally, it is possible to
 * filter using dedicated annotations, e.g. by NamedElement::getEntityName() ({@link Named}) or by
 * Identifier::getId() ({@link Identified}).
 * 
 * Example:
 * 
 * <code>
 * &#64;Test<br/>
 * &#64;PCMInstanceFromSupplier(TestModelBase.Empty.class)<br/> 
 * &#64;MockSimulation<br/>
 * final void testAddResourceContainer(AbstractSimuLizarRuntimeState runtimeState, ResourceEnvironment environment,<br/>
 *  &#64;Named("Container 1") ResourceContainer container) {
 * }
 * </code>
 * 
 * @author Sebastian Krach
 *
 */
public class SimuLizarTestExtension implements BeforeEachCallback, ParameterResolver {
    private final static Namespace SIMULIZAR = create("org.palladiosimulator.simulizar");

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        loadPCMInstance(context).ifPresent(rsp -> context.getStore(SIMULIZAR)
            .put(PCMResourceSetPartition.class.getName(), rsp));

        findAnnotation(context.getElement(), MockSimulation.class)
            .ifPresent(mockSimulationAnnotation -> getObjectFromStore(context, PCMResourceSetPartition.class)
                .ifPresent(rsp -> SimuLizarMockUtils
                    .setUpMockRuntimeStateForModelObserving(rsp, mockSimulationAnnotation.initializeRegistry())
                    .entrySet()
                    .forEach(e -> context.getStore(SIMULIZAR)
                        .put(e.getKey()
                            .getName(), e.getValue()))));
    }

    private Optional<PCMResourceSetPartition> loadPCMInstance(ExtensionContext context) {
        return findAnnotation(context.getElement(), PCMInstanceFromSupplier.class).map(an -> {
            try {
                for (var constr : an.value()
                    .getDeclaredConstructors()) {
                    if (constr.getParameterCount() == 1 && constr.getParameters()[0].getType()
                        .isAssignableFrom(ExtensionContext.class)) {
                        return an.value()
                            .getDeclaredConstructor(ExtensionContext.class)
                            .newInstance(context)
                            .get();
                    }
                }
                return an.value()
                    .getDeclaredConstructor()
                    .newInstance()
                    .get();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return getObjectFromStore(extensionContext, parameterContext.getParameter()
            .getType()).isPresent() || findModelEntity(parameterContext, extensionContext).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return getObjectFromStore(extensionContext, (Class<Object>) parameterContext.getParameter()
            .getType()).or(() -> findModelEntity(parameterContext, extensionContext))
                .orElse(null);
    }

    /**
     * Retrieves an object from the cached mock store.
     */
    private <T> Optional<T> getObjectFromStore(ExtensionContext extensionContext, Class<T> clazz) {
        return Optional.ofNullable(extensionContext.getStore(SIMULIZAR)
            .get(clazz.getName(), clazz));
    }

    /**
     * Resolves a PCMResourceSetPartition from the extensionContext and looks for objects conforming
     * to the parameter context.
     */
    private Optional<Object> findModelEntity(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getObjectFromStore(extensionContext, PCMResourceSetPartition.class).flatMap(rsp -> {
            var filter = getParameterFilterPredicate(parameterContext);
            return Streams.stream(EcoreUtil.getAllContents(rsp.getResourceSet(), true))
                .filter(filter)
                .findAny();
        });
    }

    /**
     * Returns the predicate suitable for parameter lookup based on the parameter context. If the
     * parameter is annotated with a {@link PredicateFactory}-based annotation the appropriate
     * factory is used to create the filter predicate.
     */
    private Predicate<Object> getParameterFilterPredicate(ParameterContext parameterContext) {
        Predicate<Object> basePredicate = parameterContext.getParameter()
            .getType()::isInstance;

        return parameterContext.findAnnotation(PredicateFactory.class)
            .map(pf -> {
                try {
                    return pf.value()
                        .getDeclaredConstructor()
                        .newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    throw new RuntimeException(e);
                }
            })
            .map(factory -> factory.apply(parameterContext))
            .map(basePredicate::and)
            .orElse(basePredicate);
    }
}
