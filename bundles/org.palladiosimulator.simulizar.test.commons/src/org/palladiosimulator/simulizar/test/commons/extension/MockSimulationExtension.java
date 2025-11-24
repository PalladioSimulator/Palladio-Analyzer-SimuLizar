package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.test.commons.annotation.Identified;
import org.palladiosimulator.simulizar.test.commons.annotation.MockSimulation;
import org.palladiosimulator.simulizar.test.commons.annotation.Named;
import org.palladiosimulator.simulizar.test.commons.util.SimuLizarMockUtils;

/**
 * The MockSimulationExtension uses JUnit 5 Extension API to facilitate tests of SimuLizar internals
 * 
 * To enable use of this extension annotate your test class with
 * <code>@ExtendWith(MockSimulation.class)</code>
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
public class MockSimulationExtension implements BeforeEachCallback {

    @SuppressWarnings("unchecked")
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
       findAnnotation(context.getElement(), MockSimulation.class)
            .ifPresent(mockSimulationAnnotation -> SimuLizarTestExtensionCommons.getObjectFromStore(context, PCMResourceSetPartition.class)
                .ifPresent(rsp -> SimuLizarMockUtils
                    .setUpMockRuntimeStateForModelObserving(rsp, mockSimulationAnnotation.initializeRegistry())
                    .entrySet()
                    .forEach(e -> SimuLizarTestExtensionCommons.putObjectIntoStore(context, (Class<Object>) e.getKey(), e.getValue())))); 
    }

}
