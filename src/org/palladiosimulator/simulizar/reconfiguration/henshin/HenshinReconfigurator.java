package org.palladiosimulator.simulizar.reconfiguration.henshin;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.storydiagrams.SDReconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.utils.FileUtil;

public class HenshinReconfigurator implements IReconfigurator {

    private final IModelAccess modelAccess;
    private static final String HENSHIN_FILE_EXTENSION = ".henshin";
    private final HenshinResourceSet henshinResourceSet;
    private final List<Module> modules;

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SDReconfigurator.class);

    public HenshinReconfigurator(final IModelAccess modelAccess, final SimuLizarWorkflowConfiguration configuration) {
        super();

        final String path = configuration.getReconfigurationRulesFolder();

        this.modelAccess = modelAccess;
        this.henshinResourceSet = new HenshinResourceSet(path);
        this.modules = this.createModules(path);
    }

    private List<Module> createModules(String path) {

        final File folder = FileUtil.getFolder(path);
        final File[] files = FileUtil.getFiles(folder, HENSHIN_FILE_EXTENSION);

        List<Module> modules = new LinkedList<Module>();
        if (files != null && files.length > 0) {
            for (final File file : files) {
                modules.add(this.henshinResourceSet.getModule(file.getPath(), false));
            }
        }
        return modules;
    }

    /**
     * @param app
     * @param resourceSet
     * @param module
     * @param saveResult
     */
    private boolean executeReconfiguration(UnitApplication app, HenshinResourceSet resourceSet, Module module) {
        // Load the measurement model into an EGraph
        EGraph graph = new EGraphImpl(this.modelAccess.getGlobalPCMModel().getAllocation());

        app.setEGraph(graph);

        // Set parameters for rule and execute...
        app.setUnit(module.getUnit("execute"));

        if (app.execute(null)) {
            // Saving the result:

            // resourceSet.saveEObject(graph.getRoots().get(0), "loadbalancer-result.repository");
            // resourceSet.save(null);
            LOGGER.info("Successfully executed Henshin rule.");
            return true;
        } else {
            LOGGER.info("Executing Henshin rule failed.");
            /*
             * resourceSet.saveEObject( graph.getRoots().get(0),
             * URI.createFileURI("C:/Users/Matthias/Documents/loadbalancer-result" +
             * Double.toString(1000 * Math.random()) + ".repository"));
             */
            return false;
            // throw new RuntimeException("Error creating outsourcing load");
        }

    }

    /**
     * @param app
     * @param resourceSet
     * @param module
     */
    private boolean analyzeReconfiguration(UnitApplication app, HenshinResourceSet resourceSet, Module module) {
        // Load the example model into an EGraph:
        EGraph graph = new EGraphImpl(this.modelAccess.getRuntimeMeasurementModel());
        app.setEGraph(graph);

        // Execute analyze step of rule
        app.setUnit(module.getUnit("analyze"));

        if (app.execute(null)) {
            LOGGER.debug("Found matching Henshin rule.");
            return true;
        } else {
            LOGGER.debug("No matching Henshin rule found.");
            return false;
        }
    }

    @Override
    public boolean checkAndExecute(EObject measuringPoint) {

        /*
         * // Load Repository Package and register it in the ResourceSet
         * RepositoryPackage.eINSTANCE.eClass(); PrmPackage.eINSTANCE.eClass();
         * 
         * Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE; Map<String, Object> m
         * = reg.getExtensionToFactoryMap(); m.put("repository", new XMIResourceFactoryImpl());
         * m.put("prm", new XMIResourceFactoryImpl());
         * 
         * // FIX for xtext StoEx: Register xtextbin in the Resource Factory if
         * (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("xtextbin"))
         * Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xtextbin", new
         * BinaryGrammarResourceFactoryImpl());
         */

        // Create an engine and a rule application:
        Engine engine = new EngineImpl();
        UnitApplication app = new UnitApplicationImpl(engine);

        boolean result = false;
        for (final Module module : this.modules) {
            if (analyzeReconfiguration(app, this.henshinResourceSet, module)) {
                result |= executeReconfiguration(app, this.henshinResourceSet, module);
            }
        }
        return result;
    }

}
