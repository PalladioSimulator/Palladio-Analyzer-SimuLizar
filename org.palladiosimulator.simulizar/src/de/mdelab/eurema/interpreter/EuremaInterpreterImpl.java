package de.mdelab.eurema.interpreter;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.interpreter.execution.events.EuremaEventTypeHierarchy;
import de.mdelab.eurema.interpreter.models.ModelRepository;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;

/**
 * Implementation of the EUREMA Interpreter.
 * 
 * @author thomas vogel
 * @version 0.03
 * 
 */
public class EuremaInterpreterImpl implements EuremaInterpreter {

	private IModelAccess access;
	private RuntimeViolationsModel vRun;
	private RuntimeStrategiesModel sRun;

	@Override
	public IModelAccess getModelAccess() {
		return access;
	}

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(EuremaInterpreterImpl.class);

	/**
	 * Configuration properties of the EUREMA interpreter.
	 */
	private EuremaProperties euremaProps;

	/**
	 * The runtime environment of the EUREMA interpreter.
	 */
	private RuntimeEnvironment runtimeEnvironment;

	/**
	 * EMF resource set to be used by the interpreter.
	 */
	private ResourceSet rs;

	/**
	 * Constructor.
	 */
	protected EuremaInterpreterImpl() {
		this.setUpInterpreter();
	}

	/**
	 * Sets up the interpreter.
	 */
	private void setUpInterpreter() {
		logger.debug("Setting up the EUREMA interpreter...");

		// loading the interpreter properties
		logger.debug(" |_ Loading the EUREMA properties...");
		this.euremaProps = new EuremaProperties();
		this.euremaProps.loadProperties();

		// register resource factories
		logger.debug(" |_ Setting up the EMF infrastructure...");
		this.rs = new ResourceSetImpl();
		this.rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		this.rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		this.rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("eurema", new XMIResourceFactoryImpl());
		this.rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

		// load EUREMA metamodel / package
		logger.debug(" |_ Loading the EUREMA metamodel...");
		eurema.EuremaPackage euremaPackage = eurema.EuremaPackage.eINSTANCE;
		this.rs.getPackageRegistry().put(euremaPackage.getNsURI(), euremaPackage);
		Metamodel.INSTANCE.initialize(euremaPackage);
		logger.debug("  |_ Loaded the EUREMA metamodel: namespace URI is " + euremaPackage.getNsURI());

		// create EUREMA event type hierarchy
		logger.debug(" |_ Create the EUREMA event type hierarchy...");
		eurema.EventType rootEventType = EuremaEventTypeHierarchy.INSTANCE.initialize();
		Resource eEventTypeHierarchy = this.rs
				.createResource(URI.createURI(this.euremaProps.getEventTypeHierarchyURI()));
		eEventTypeHierarchy.getContents().add(rootEventType);

		// create EUREMA default runtime environment
		logger.debug(" |_ Create the eurema.RuntimeEnvironment...");
		eurema.RuntimeEnvironment eRuntimeEnvironment = Metamodel.INSTANCE.getEuremaFactory()
				.createRuntimeEnvironment();
		Resource eRuntimeEnvironmentResource = this.rs
				.createResource(URI.createURI(this.euremaProps.getRuntimeEnvironmentURI()));
		eRuntimeEnvironmentResource.getContents().add(eRuntimeEnvironment);

		this.runtimeEnvironment = new RuntimeEnvironment(eRuntimeEnvironment);

		// register Story Diagrams used internally by the interpreter for
		// offline adaptation. Registration does not happen due to the use of
		// the SD interpreter within model operation implementation.
		//
		// TODO register Story Diagrams; generic interface for registering such
		// stuff?
		// rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
		// .put("story", new XMIResourceFactoryImpl());
		// rs.getPackageRegistry().put(StoryDiagramEcorePackage.eNS_URI,
		// StoryDiagramEcorePackage.eINSTANCE);

		logger.debug(this.listResources(this.rs));
		logger.debug("Completed the setup of the EUREMA interpreter.");
	}

	/**
	 * String representation of the resource set <code>rs</code>.
	 * 
	 * @param rs
	 *            the resource set
	 * @return a String representation listing all resources in the resource set
	 */
	private String listResources(ResourceSet rs) {
		StringBuffer sb = new StringBuffer("Resources in the ResourceSet:\n");
		int i = 1;
		for (Resource r : rs.getResources()) {
			sb.append(" (" + i + ") " + r.getURI().toString() + "\n");
			i++;
		}
		// return String without the last line break.
		return sb.substring(0, sb.length() - 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQueue execute(eurema.Architecture eArchitecture, IModelAccess access, RuntimeViolationsModel v,
			RuntimeStrategiesModel s) {
		if (eArchitecture == null) {
			throw new EuremaInterpreterException("The architecture to be executed is null.");
		}

		// setting up the model repository
		logger.debug(" |_ Setting up the EUREMA model repository...");
		ModelRepository.INSTANCE.initialize(this.rs, eArchitecture.getModelResourceSet());

		return this.runtimeEnvironment.execute(eArchitecture, access, v, s);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQueue execute(String euremaArchitectureModelUri, IModelAccess modAccess, RuntimeViolationsModel v,
			RuntimeStrategiesModel s) {
		if (euremaArchitectureModelUri == null || euremaArchitectureModelUri.equals("")) {
			throw new EuremaInterpreterException(
					"The URI of the architecture to be executed is null or the empty String.");
		}
		eurema.Architecture eArchitecture = this.loadArchitecture(euremaArchitectureModelUri);
		access = modAccess;

		return this.execute(eArchitecture, modAccess, v, s);
	}

	/**
	 * Loads the EUREMA model from the given URI.
	 * 
	 * @param euremaArchitectureModelUri
	 *            the URI from which the model should be loaded
	 * @return the loaded EUREMA model as the {@code eurema.Architecture}
	 *         instance.
	 */
	private eurema.Architecture loadArchitecture(String euremaArchitectureModelUri) {
		if (euremaArchitectureModelUri == null || euremaArchitectureModelUri.equals("")) {
			throw new EuremaInterpreterException(
					"The URI of the architecture to be executed is null or the empty String.");
		}
		logger.debug("Loading the EUREMA model from the URI " + euremaArchitectureModelUri);

		String filename = new File(euremaArchitectureModelUri).getAbsolutePath();
		URI modelURI = URI.createFileURI(filename);
		Resource eArchitectureResource = this.rs.getResource(modelURI, true);

		EList<EObject> contents = eArchitectureResource.getContents();
		if (contents.size() == 0) {
			throw new EuremaInterpreterException("EUREMA architecture model contains no root object.");
		}
		if (contents.size() != 1) {
			throw new EuremaInterpreterException("EUREMA architecture model contains more than one root objects.");
		}
		EObject rootObject = contents.get(0);
		if (rootObject == null) {
			throw new EuremaInterpreterException("EUREMA architecture model contains a null root object.");
		}
		if (!(rootObject instanceof eurema.Architecture)) {
			throw new EuremaInterpreterException(
					"EUREMA architecture model does not contain a root object of type eurema.Architecture but of type "
							+ rootObject.eClass().getName());
		} else {
			logger.debug("EUREMA model successfully loaded from the URI " + euremaArchitectureModelUri);
			return (eurema.Architecture) rootObject;
		}
	}

	@Override
	public RuntimeViolationsModel getRuntimeViolationsModel() {
		return vRun;
	}

	@Override
	public RuntimeStrategiesModel getRuntimeStrategiesModel() {
		return sRun;
	}

}
