package de.mdelab.eurema.interpreter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import eurema.EuremaFactory;
import eurema.EuremaPackage;

/**
 * Represents the EUREMA metamodel and provides access to the corresponding
 * <tt>EPackage</tt> and <tt>EFactory</tt>.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public class Metamodel {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(Metamodel.class);

	/**
	 * The singleton instance of the metamodel.
	 */
	public final static Metamodel INSTANCE = new Metamodel();

	/**
	 * Indicates whether the metamodel has been initialized or not.
	 */
	private static boolean INITIALIZED = false;

	/**
	 * The <tt>EPackage</tt> of EUREMA.
	 */
	private EuremaPackage euremaEPackage;
	/**
	 * The <tt>EFactory</tt> of EUREMA:
	 */
	private EuremaFactory euremaEFactory;

	/**
	 * Constructor.
	 */
	private Metamodel() {

	}

	/**
	 * Initializes the metamodel to be used by the EUREMA interpreter.
	 * 
	 * @param euremaPackage
	 *            the <tt>EPackage</tt> of the EUREMA metamodel
	 */
	public void initialize(EuremaPackage euremaPackage) {
		if (INITIALIZED) {
			logger.debug("Metamodel " + euremaPackage.getName() + " already initialized for the interpreter.");
			return;
		}
		logger.debug("Initializing the EUREMA language...");

		this.euremaEPackage = euremaPackage;
		this.euremaEFactory = euremaPackage.getEuremaFactory();

		INITIALIZED = true;
	}

	/**
	 * Returns the <tt>EPackage</tt> of EUREMA.
	 * 
	 * @return the <tt>EPackage</tt> of EUREMA.
	 */
	public EuremaPackage getEuremaPackage() {
		return this.euremaEPackage;
	}

	/**
	 * Returns the <tt>EFactory</tt> of EUREMA.
	 * 
	 * @return the <tt>EFactory</tt> of EUREMA.
	 */
	public EuremaFactory getEuremaFactory() {
		return this.euremaEFactory;
	}

}
