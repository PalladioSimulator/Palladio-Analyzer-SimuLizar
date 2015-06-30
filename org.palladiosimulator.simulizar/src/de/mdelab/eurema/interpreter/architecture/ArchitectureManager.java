package de.mdelab.eurema.interpreter.architecture;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;

/**
 * Provides access to the architecture of the self-adaptive software as
 * specified by the EUREMA model. It is the sum of the <i>Layer Diagram</i> and
 * the corresponding <i>Feedback Loop Diagrams</i>.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class ArchitectureManager {

	/**
	 * The Architecture Manager instance.
	 */
	public final static ArchitectureManager INSTANCE =
			new ArchitectureManager();

	/**
	 * Flag indicating whether this manager has been initialized or not.
	 */
	private boolean initialized = false;

	/**
	 * The EUREMA model as the {@code eurema.Architecture} instance.
	 */
	private eurema.Architecture eArchitecture;

	/**
	 * Constructor.
	 */
	private ArchitectureManager() {

	}

	/**
	 * Initializes the Architecture Manager Manager.
	 * 
	 * @param eArchitecture
	 *            the {@code eurema.Architecture} element of the EUREMA model.
	 */
	public void initialize(eurema.Architecture eArchitecture) {
		if (!this.initialized) {
			this.eArchitecture = eArchitecture;
			this.initialized = true;
		} else {
			throw new EuremaInterpreterException(
					"The Architecture Manager Manager has already been initialized.");
		}
	}

	/**
	 * Returns all layers of the architecture.
	 * 
	 * @return all layers of the architecture.
	 */
	public List<eurema.Layer> getAllLayers() {
		EList<eurema.Layer> layers = this.eArchitecture.getLayers();
		return Collections.unmodifiableList(layers);
	}

	/**
	 * The layer with the given <code>layerNumber</code>. Layers are enumerated
	 * bottom-up starting with 0.
	 * 
	 * @param layerNumber
	 *            the given layer number
	 * @return the specified layer or <code>null</code> if there is no layer
	 *         with the given number.
	 */
	public eurema.Layer getLayerByNumber(int layerNumber) {
		EList<eurema.Layer> layers = this.eArchitecture.getLayers();
		eurema.Layer layer = null;
		for (eurema.Layer tmpLayer : layers) {
			int lNumber = tmpLayer.getNumber();
			if (lNumber == layerNumber) {
				layer = tmpLayer;
				break;
			}
		}
		return layer;
	}

	/**
	 * Each layer of the architecture has a name. This methods retrieves the
	 * layer by its name.
	 * 
	 * @param layerName
	 *            the name of the layer
	 * @return the layer with the given name or <code>null</code> if there is no
	 *         layer with the given name.
	 */
	public eurema.Layer getLayerByName(String layerName) {
		EList<eurema.Layer> layers = this.eArchitecture.getLayers();
		eurema.Layer layer = null;
		for (eurema.Layer tmpLayer : layers) {
			String lName = tmpLayer.getName();
			if (lName.equals(layerName)) {
				layer = tmpLayer;
				break;
			}
		}
		return layer;
	}

	/**
	 * Returns all modules contained in the architecture.
	 * 
	 * @return all modules contained in the architecture.
	 */
	public List<eurema.Module> getAllModules() {
		List<eurema.Module> allModules = new LinkedList<eurema.Module>();
		List<eurema.Layer> allLayers = this.getAllLayers();
		for (eurema.Layer layer : allLayers) {
			EList<eurema.Module> modules = layer.getModules();
			allModules.addAll(modules);
		}
		return Collections.unmodifiableList(allModules);
	}

	/**
	 * Returns all managed modules, i.e., all modules at layer 0.
	 * 
	 * @return all managed modules, i.e., all modules at layer 0.
	 */
	public List<eurema.Module> getAllManagedModules() {
		return this.getAllModulesAtLayer(0);
	}

	/**
	 * Returns all managing modules, i.e., all modules at layers 1..n.
	 * 
	 * @return all managing modules, i.e., all modules at layers 1..n.
	 */
	public List<eurema.Module> getAllManagingModules() {
		List<eurema.Module> allManagingModules = new LinkedList<>();
		// add all modules
		allManagingModules.addAll(this.getAllModules());
		// remove managed modules
		allManagingModules.removeAll(this.getAllManagedModules());
		return Collections.unmodifiableList(allManagingModules);
	}

	/**
	 * Returns all modules located in the layer with the given layer number.
	 * 
	 * @param layerNumber
	 *            the number of the layer
	 * @return all modules located in the layer with the given layer number. If
	 *         the layer does not contain any module, the result is the empty
	 *         list. If there is no layer with the given layer number, the
	 *         result is <code>null</code>.
	 */
	// layer 0 only contains adaptable software modules
	public List<eurema.Module> getAllModulesAtLayer(int layerNumber) {
		eurema.Layer layer = this.getLayerByNumber(layerNumber);
		if (layer == null) {
			return null;
		} else {
			EList<eurema.Module> modules = layer.getModules();
			return Collections.unmodifiableList(modules);
		}
	}

	/**
	 * Returns the module with the given name.
	 * 
	 * @param moduleName
	 *            the name of the module
	 * @return the module with the given name or <code>null</code> if there is
	 *         no module with the given name.
	 */
	public eurema.Module getModuleByName(String moduleName) {
		eurema.Module result = null;
		List<eurema.Layer> layers = this.getAllLayers();
		outerloop: for (eurema.Layer layer : layers) {
			EList<eurema.Module> modules = layer.getModules();
			for (eurema.Module tmpModule : modules) {
				String tmpModuleName = tmpModule.getName();
				if (tmpModuleName.equals(moduleName)) {
					result = tmpModule;
					break outerloop;
				}
			}
		}
		return result;
	}

	/**
	 * Returns the module with the given ID.
	 * 
	 * @param moduleId
	 *            the ID of the module
	 * @return the module with the given ID or <code>null</code> if there is no
	 *         module with the given ID.
	 */
	public eurema.Module getModuleById(String moduleId) {
		eurema.Module result = null;
		List<eurema.Layer> layers = this.getAllLayers();
		outerloop: for (eurema.Layer layer : layers) {
			EList<eurema.Module> modules = layer.getModules();
			for (eurema.Module tmpModule : modules) {
				String tmpModuleId = tmpModule.getUid();
				if (tmpModuleId.equals(moduleId)) {
					result = tmpModule;
					break outerloop;
				}
			}
		}
		return result;
	}

	/**
	 * Returns the architecture of the self-adaptive software defined by the
	 * EUREMA model.
	 * 
	 * @return the architecture of the self-adaptive software defined by the
	 *         EUREMA model.
	 */
	public eurema.Architecture getArchitecture() {
		return this.eArchitecture;
	}
}
