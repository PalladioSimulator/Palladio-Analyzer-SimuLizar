package de.mdelab.eurema.interpreter.execution.module.executor.software;

import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;

/**
 * Implementation of a {@code ModuleExecutable} that is used for executing a
 * {@code eurema.SoftwareModule}. The software module is a feedback loop and not
 * an implementation instance of a model operation!
 * 
 * This implementation ensures the non-reentrance of a software module realizing
 * a feedback loop (cf. Implementation of the
 * {@link #run(eurema.InitialOperation)} method).
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class SoftwareModuleExecutableImpl
		implements
		ModuleExecutable<eurema.SoftwareModule, eurema.SoftwareModuleTrigger, String, String> {

	/**
	 * The global execution context.
	 */
	private GlobalExecutionContext globalExecutionContext;

	/**
	 * The software module to be executed.
	 */
	private eurema.SoftwareModule eSoftwareModule;

	/**
	 * The code to invoke the software module through reflection.
	 */
	private String implementationExecutionMethod;

	/**
	 * Constructor.
	 * 
	 * @param eSoftwareModule
	 *            the software module to be executed.
	 * @param globalExecutionContext
	 *            the global execution context
	 */
	public SoftwareModuleExecutableImpl(eurema.SoftwareModule eSoftwareModule,
			GlobalExecutionContext globalExecutionContext) {
		this.eSoftwareModule = eSoftwareModule;
		this.globalExecutionContext = globalExecutionContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String run(String entryState) {
		// This method is synchronized. Thus, only one thread may invoke it at
		// any time and thus, only one thread may execute the module at any
		// time. This ensures the non-reentrance of the module.
		synchronized (this) {
			this.implementationExecutionMethod = entryState;
			// TODO execute the software module
			// use this.implementationExecutionMethod
			System.out.println(this.implementationExecutionMethod);
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.SoftwareModule getEModule() {
		return this.eSoftwareModule;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.SoftwareModuleTrigger getETrigger() {
		return this.eSoftwareModule.getTrigger();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.ExecutionContext getExecutionContext() {
		return null;
	}

}
