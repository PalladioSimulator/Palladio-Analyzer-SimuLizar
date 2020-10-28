package org.palladiosimulator.simulizar.test.commons.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.Repository.LocalMemoryRepository;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.edp2.models.Repository.RepositoryFactory;

/**
 * This JUnit 5 Extension support the creation of simulation tests. Before test execution it creates
 * a {@link LocalMemoryRepository} and registers it with the {@link RepositoryManager} singleton and
 * the {@link SimuLizarTestExtensionCommons#SIMULIZAR} store namespace. The repository is removed
 * after test execution.
 * 
 * Furthermore, this extension supports writing test cases by filtering out a single
 * {@link ExperimentRun} from the repository to be provided as parameter.
 * 
 * @author Sebastian Krach
 *
 */
public class ProvideEDP2RepositoryExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        var repo = RepositoryFactory.eINSTANCE.createLocalMemoryRepository();

        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), repo);

        SimuLizarTestExtensionCommons.putObjectIntoStore(context, LocalMemoryRepository.class, repo);
        SimuLizarTestExtensionCommons.putObjectIntoStore(context, Repository.class, repo);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        SimuLizarTestExtensionCommons.getObjectFromStore(context, LocalMemoryRepository.class)
            .ifPresent(repo -> RepositoryManager.removeRepository(RepositoryManager.getCentralRepository(), repo));
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter()
            .getType()
            .isAssignableFrom(ExperimentRun.class)
                && SimuLizarTestExtensionCommons.getObjectFromStore(extensionContext, LocalMemoryRepository.class)
                    .map(repository -> repository.getExperimentGroups()
                        .stream()
                        .anyMatch(eg -> eg.getExperimentSettings()
                            .stream()
                            .anyMatch(es -> !es.getExperimentRuns()
                                .isEmpty())))
                    .orElse(false);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {

        if (parameterContext.getParameter()
            .getType()
            .isAssignableFrom(ExperimentRun.class)) {
            return SimuLizarTestExtensionCommons.getObjectFromStore(extensionContext, LocalMemoryRepository.class)
                .flatMap(repository -> repository.getExperimentGroups()
                    .stream()
                    .findAny()
                    .flatMap(eg -> eg.getExperimentSettings()
                        .stream()
                        .findAny()
                        .flatMap(es -> es.getExperimentRuns()
                            .stream()
                            .findAny())))
                .orElseThrow(() -> new IllegalStateException("There should be a Experiment Run in the repository"));
        } else {
            return null;
        }

    }

}
