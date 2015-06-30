package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.Metamodel;
import de.mdelab.eurema.interpreter.condition.QueryExecutionInformation;
import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventType;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventTypeHierarchy;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;
import de.mdelab.eurema.interpreter.execution.module.inerceptor.MegamodelModuleInterceptionManager;

/**
 * Implementation of a {@code ModuleExecutable} that is used for executing an
 * {@code eurema.MegamodelModule}. It also provides an execution context for the
 * megamodel module. The module does not need to have a triggering condition.
 * 
 * This implementation ensures the non-reentrance of a megamodel module (cf.
 * Implementation of the {@link #run(eurema.InitialOperation)} method).
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class MegamodelModuleExecutableImpl
		implements
		ModuleExecutable<eurema.MegamodelModule, eurema.MegamodelModuleTrigger, eurema.InitialOperation, eurema.FinalOperation>,
		MegamodelModuleExecutionContext {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager
			.getLogger(MegamodelModuleExecutableImpl.class);

	/**
	 * The global execution context.
	 */
	private GlobalExecutionContext globalExecutionContext;

	/**
	 * The megamodel module to be executed.
	 */
	private eurema.MegamodelModule eMegamodelModule;

	/**
	 * The thread that is currently executing the module or <code>null</code>
	 * when the module is currently not executed.
	 */
	private Thread executingThread = null;

	/**
	 * Constructor.
	 * 
	 * @param eMegamodelModule
	 *            the megamodel module to be executed.
	 * @param globalExecutionContext
	 *            the global execution context
	 */
	public MegamodelModuleExecutableImpl(
			eurema.MegamodelModule eMegamodelModule,
			GlobalExecutionContext globalExecutionContext) {
		this.eMegamodelModule = eMegamodelModule;
		this.globalExecutionContext = globalExecutionContext;
		eurema.ExecutionContext eExecutionContext = this
				.setUpExecutionContext();
		this.setUpExecutionInformation(eExecutionContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.FinalOperation run(eurema.InitialOperation eInitialOperation) {
		// This method is synchronized. Thus, only one thread may invoke it at
		// any time and thus, only one thread may execute the module at any
		// time. This ensures the non-reentrance of the module.
		synchronized (this) {

			assert this.executingThread == null;
			this.executingThread = Thread.currentThread();

			logger.debug("Starting to run the megamodel module "
					+ this.eMegamodelModule.getName() + "... by thread "
					+ this.executingThread.getId());

			if (eInitialOperation == null) {
				throw new EuremaInterpreterException(
						"Failure in executing the megamodel module "
								+ this.eMegamodelModule.getName()
								+ ". The initial operation to start execution is null.");
			}

			// Execute the elements iteratively

			// the element to be executed
			eurema.Executable executable = null;
			// the <tt>EClass</tt> of the element to be executed
			EClass executableEClass = null;
			// the executor for the element to be executed
			ElementExecutor executor;

			// the element executed just before the element to be executed
			eurema.Executable executedBefore = null;

			// start execution with the initial operation
			executable = eInitialOperation;
			executableEClass = executable.eClass();

			// execute the next executable element until reaching a final
			// operation
			eurema.EuremaPackage euremaPackage = Metamodel.INSTANCE
					.getEuremaPackage();
			EClass finalOperationEClass = euremaPackage.getFinalOperation();
			while (!executableEClass.equals(finalOperationEClass)) {

				// set the current pointer to the executable element
				this.setCurrentPointer(executable);

				// select appropriate executor
				executor = this.globalExecutionContext
						.getElementExecutorFactory().getElementExecutorByClass(
								executableEClass);

				// CHECK FOR INTERCEPTIONS "BEFORE"
				this.checkForInteceptions(EuremaEventType.BEFORE, executable);

				// execute the executable
				eurema.Executable nextExecutable = executor.execute(executable,
						executedBefore, this);

				// update execution information of the executable
				this.updateExecutionInformation(executable, executedBefore);

				// CHECK FOR INTERCEPTIONS "AFTER"
				this.checkForInteceptions(EuremaEventType.AFTER, executable);

				// set variables for the next run of the loop
				executedBefore = executable;
				executable = nextExecutable;
				executableEClass = executable.eClass();
			}

			// execute the final operation
			assert executable instanceof eurema.FinalOperation;
			this.setCurrentPointer(executable);
			executor = this.globalExecutionContext.getElementExecutorFactory()
					.getElementExecutorByClass(executableEClass);
			executor.execute(executable, executedBefore, this);
			// update execution information
			this.updateExecutionInformation(executable, executedBefore);

			// set current pointer to null
			this.setCurrentPointer(null);

			logger.debug("Terminating the execution of the megamodel module "
					+ this.eMegamodelModule.getName() + "... by thread "
					+ this.executingThread.getId());

			// reset executing thread
			assert this.executingThread == Thread.currentThread();
			this.executingThread = null;

			// return status -- final operation of the megamodel
			return (eurema.FinalOperation) executable;
		}
	}

	/**
	 * Checks whether the megamodel module currently executed should be
	 * intercepted to execute another module. Inteceptions points are here
	 * <tt>BEFORE</tt> or <tt>AFTER</tt> the execution of an
	 * {@code eurema.OperationBehavior}.
	 * 
	 * @param eventType
	 *            the interception point of the executable, that is,
	 *            <tt>BEFORE</tt> or <tt>AFTER</tt>.
	 * @param executable
	 *            the executable element currently executed
	 */
	private void checkForInteceptions(EuremaEventType eventType,
			eurema.Executable executable) {
		if (executable instanceof eurema.OperationBehavior) {
			eurema.Event eEvent = Metamodel.INSTANCE.getEuremaFactory()
					.createEvent();
			eEvent.setName(executable.getName());
			eEvent.setType(EuremaEventTypeHierarchy.INSTANCE
					.getEEventType(eventType));
			MegamodelModuleInterceptionManager.INSTANCE
					.executeInterceptorModules(this.eMegamodelModule, eEvent);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.MegamodelModule getEModule() {
		return this.eMegamodelModule;
	}

	/**
	 * Sets up the local execution context ({@code eurema.ExecutionContext}) for
	 * the megamodel module.
	 * 
	 * @return the execution context being set up.
	 */
	private eurema.ExecutionContext setUpExecutionContext() {
		logger.debug("Setting up the execution context of the megamodel module "
				+ this.eMegamodelModule.getName());
		// Assert that the :MegamodelModule has no :ExecutionContext
		assert this.eMegamodelModule.getContext() == null;
		// Create an :ExecutionContext
		eurema.ExecutionContext eExecutionContext = Metamodel.INSTANCE
				.getEuremaFactory().createExecutionContext();
		String executionContextId = this.eMegamodelModule.getUid()
				+ "_EXECUTION_CONTEXT";
		eExecutionContext.setUid(executionContextId);
		this.eMegamodelModule.setContext(eExecutionContext);
		return eExecutionContext;
	}

	/**
	 * Sets up and initializes the execution information for the executable
	 * megamodel elements of the megamodel module.
	 * 
	 * @param eExecutionContext
	 *            the execution context of the megamodel module.
	 */
	private void setUpExecutionInformation(
			eurema.ExecutionContext eExecutionContext) {
		logger.debug("Setting up the execution information of the megamodel module "
				+ this.eMegamodelModule.getName());
		eurema.Megamodel eMegamodel = this.eMegamodelModule.getMegamodel();
		TreeIterator<EObject> allContents = eMegamodel.eAllContents();
		eurema.EuremaFactory eFactory = Metamodel.INSTANCE.getEuremaFactory();
		while (allContents.hasNext()) {
			EObject next = allContents.next();
			if (next instanceof eurema.Executable) {
				eurema.Executable eExecutableElement = (eurema.Executable) next;
				// create execution information for the executable megamodel
				// element
				eurema.ExecutionInformation eExecutionInformation = eFactory
						.createExecutionInformation();
				// set execution context
				eExecutionInformation.setContext(eExecutionContext);
				// set executable megamodel element
				eExecutionInformation.setExecutable(eExecutableElement);
				// set count and time
				eExecutionInformation.setCount(0);
				eExecutionInformation.setTime(0);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.MegamodelModuleTrigger getETrigger() {
		return this.eMegamodelModule.getTrigger();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.ExecutionContext getExecutionContext() {
		eurema.ExecutionContext eExecutionContext = this.eMegamodelModule
				.getContext();
		assert eExecutionContext != null;
		return eExecutionContext;
	}

	/**
	 * Retrieves the {@code eurema.Executable} from the megamodel module by
	 * matching the indicated <code>name</code> against (1) all names of
	 * {@code eurema.Operation}s and (2) all names of {@code eurema.Exit}s.
	 * 
	 * The motivation for (2) is that the names of {@code eurema.Transition}s
	 * are not depicted in the FLD but the name of the {@code eurema.Exit}s are
	 * and there is a 1-to-1 relationship between an {@code eurema.Transition}
	 * and an {@code eurema.Exit}.
	 * 
	 * 
	 * The first match is returned. If there is no match, the result is
	 * <code>null</code>.
	 * 
	 * <p>
	 * In EUREMA, we require that operation names are unique within a megamodel
	 * module while exit names must only be unique among all exits of the
	 * corresponding operation. Thus, multiple exits belonging to different
	 * operations might have the same name.
	 * </p>
	 * <p>
	 * This method should be used if it can be assumed that the name as given by
	 * the parameter of this method is uniquely used as a name for an
	 * {@code eurema.Operation} or {@code eurema.Transition}/{@code eurema.Exit}
	 * in the megamodel for this module executable.
	 * </p>
	 * 
	 * @param name
	 *            the name of the {@code eurema.Operation} or {@code eurema.Exit}.
	 * @return the first executable found by its name or <code>null</code> if
	 *         there is no executable with the given name.
	 */
	private eurema.Executable getExecutableByName(String name) {
		eurema.Executable eResult = null;
		List<eurema.Operation> allEOperations = this.getAllOperations();
		outerloop: for (eurema.Operation eOperation : allEOperations) {
			// check whether operation matches
			if (eOperation.getName().equals(name)) {
				eResult = eOperation;
				break;
			} else {
				// check exits of the operation
				for (eurema.Exit eExit : eOperation.getExits()) {
					if (eExit.getName().equals(name)) {
						eurema.Transition eTransition = eExit.getOutgoing();
						eResult = eTransition;
						break outerloop;
					}
				}
			}
		}
		return eResult;
	}

	/**
	 * Retrieves the {@code eurema.Operation} of the megamodel module by its
	 * name.
	 * 
	 * @param name
	 *            the name of the operation
	 * @return the operation with the given name or <code>null</code> if there
	 *         is no operation with the given name contained in the megamodel
	 *         module.
	 */
	private eurema.Operation getOperationByName(String name) {
		eurema.Operation eOperation = null;
		for (eurema.Operation op : this.getAllOperations()) {
			if (op.getName().equals(name)) {
				eOperation = op;
				break;
			}
		}
		return eOperation;
	}

	/**
	 * Returns all operation elements of the megamodel module.
	 * 
	 * @return all operation elements of the megamodel module.
	 */
	private List<eurema.Operation> getAllOperations() {
		// all operations
		List<eurema.Operation> eOperations = new LinkedList<>();
		eurema.Megamodel eMegamodel = this.eMegamodelModule.getMegamodel();

		// add initial operations
		eOperations.addAll(eMegamodel.getInitialOperations());
		// add decision operations
		eOperations.addAll(eMegamodel.getDecisionOperations());
		// add final operations
		eOperations.addAll(eMegamodel.getFinalOperations());
		// add operation behavior (model operations and megamodel calls)
		eOperations.addAll(eMegamodel.getBehavior());
		
		return eOperations;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long retrieveCount(String query) {
		eurema.ExecutionInformation eExecutionInformation = this
				.resolveExecutionInformation(query);
		long count = new Long(eExecutionInformation.getCount());
		logger.debug(" |_ Evaluating the query " + query + " to " + count);
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long retrieveTime(String query) {
		eurema.ExecutionInformation eExecutionInformation = this
				.resolveExecutionInformation(query);
		long time = (Long) eExecutionInformation.getTime();
		logger.debug(" |_ Evaluating the query " + query + " to " + time);
		return time;
	}

	/**
	 * Retrieves the {@code eurema.ExecutionInformation} element of the
	 * executable element identified by the <code>query</code>. This query
	 * refers to an {@code eurema.Executable} element, either an
	 * {@code eurema.Operation} or an {@code eurema.Transition}. If it is an
	 * {@code eurema.Operation}, then the query refers to the name of the
	 * operation. If it is an {@code eurema.Transition}, then the name refers to
	 * the exit compartment being the source of the transition.
	 * <p>
	 * Such a query is either:
	 * <ul>
	 * <li>the name of an operation: <tt>operationName</tt></li>
	 * <li>the unqualified name of a transition: <tt>transitionName</tt></li>
	 * <li>the qualified name of an exit compartment:
	 * <tt>sourceOperationName::exitName</tt> The names of the source operation
	 * and of the exit are separated by
	 * {@code QueryExecutionInformation#SEPARATOR}.</li>
	 * </ul>
	 * </p>
	 * We require that operation names are unique within a Feedback Loop Diagram
	 * (FLD), which is the scope for evaluating a query. Exit names just have to
	 * be unique for all exists that share the same source operation. Thus, exit
	 * names can be specified relatively to the operation the exit belongs to,
	 * particularly, as a pattern consisting of the operation name, the
	 * separator {@code QueryExecutionInformation#SEPARATOR}, and the exit name.
	 * However, if the exit name is unique within the FLD, it is sufficient to
	 * use only the exit name without the operation name and separator.
	 * 
	 * @param query
	 *            the name of the executable megamodel element.
	 * @return the {@code eurema.ExecutionInformation} for the element
	 *         identified by the query.
	 */
	private eurema.ExecutionInformation resolveExecutionInformation(String query) {
		if (query == null || query.equals("")) {
			throw new EuremaInterpreterException(
					"Failure in resolving an executable megamodel element and its "
							+ " execution information. The query is null or the empty String.");
		}
		if (!query.contains(QueryExecutionInformation.SEPARATOR)) {
			// The query is the name of an operation or the unqualified name of
			// a transition. Thus, we may assume that the name of the operation
			// or transition is unique within the megamodel/FLD.
			// Find the operation or transition
			eurema.Executable executable = this.getExecutableByName(query);
			if (executable == null) {
				throw new EuremaInterpreterException(
						"Failure in resolving an executable megamodel element and its "
								+ " execution information. No element can be found for the query: "
								+ query);
			}
			return executable.getExecInfo();
		} else {
			// The query is the qualified name of a transition
			String[] queryParts = query
					.split(QueryExecutionInformation.SEPARATOR);
			assert queryParts.length == 2;
			String queryOperationName = queryParts[0];
			String queryExitName = queryParts[1];

			eurema.Operation eOperation = this
					.getOperationByName(queryOperationName);
			if (eOperation == null) {
				throw new EuremaInterpreterException(
						"Failure in resolving an operation and its execution information. "
								+ "No operation can be found for the query: "
								+ queryOperationName);
			}
			eurema.Transition eResult = null;
			for (eurema.Exit eExit : eOperation.getExits()) {		
				if (eExit.getName().equals(queryExitName)) {
					eurema.Transition eTransition = eExit.getOutgoing();
					eResult = eTransition;
					break;
				}
			}
			if (eResult == null) {
				throw new EuremaInterpreterException(
						"Failure in resolving a transition and its execution information. "
								+ "No transition can be found for the query: "
								+ query);
			}
			return eResult.getExecInfo();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<eurema.ExecutionInformation> getExecutionInformation() {
		return this.eMegamodelModule.getContext().getExecInfos();
	}

	/**
	 * Sets the <tt>current</tt> pointer of the execution context for the
	 * executing megamodel module to the currently executed element of the
	 * module.
	 * 
	 * @param executable
	 *            the currently executed element
	 */
	private void setCurrentPointer(eurema.Executable executable) {
		if (executable != null) {
			logger.debug(" |_ Executing the " + executable.eClass().getName()
					+ " " + executable.getName() + " ...");
		} else {
			logger.debug(" |_ Execution done. No executable element is running.");
		}
		this.eMegamodelModule.getContext().setCurrent(executable);
	}

	/**
	 * Updates the {@code eurema.ExecutionInformation} of the
	 * {@code eurema.Executable} that has just been executed.
	 * 
	 * <p>
	 * The <tt>time</tt> attribute reflects the timestamp of the last execution
	 * of the operation or transition.
	 * </p>
	 * <p>
	 * For a transition, the <tt>count</tt> attribute reflects the number how
	 * often the source operation of the transition has been executed without
	 * taking the corresponding transition but another outgoing transition. If
	 * the corresponding transition is taken, <tt>count</tt> is reset to
	 * <tt>0</tt>.
	 * </p>
	 * <p>
	 * For an operation, the <tt>count</tt> attribute reflects the number of
	 * executions of the operation.
	 * </p>
	 * 
	 * @param executable
	 *            The executable (operation or transition) that is currently
	 *            executed.
	 * @param executedBefore
	 *            the executable that has been executed before the
	 *            <code>executable</code> (= first parameter).
	 */
	private void updateExecutionInformation(eurema.Executable executable,
			eurema.Executable executedBefore) {
		eurema.ExecutionInformation executableExecutionInfo = executable
				.getExecInfo();
		// The <tt>time</tt> attribute reflects the timestamp of the last
		// execution of the operation or transition. => Set <tt>time</tt> to the
		// current time stamp
		executableExecutionInfo.setTime(System.currentTimeMillis());
		// set <tt>count</tt>
		if (executable instanceof eurema.Operation) {
			// For an operation, the <tt>count</tt> attribute reflects the
			// number of executions of the operation. => increment
			// <tt>count</tt> by 1
			executableExecutionInfo
					.setCount(executableExecutionInfo.getCount() + 1);
		} else if (executable instanceof eurema.Transition) {
			// For a transition, the <tt>count</tt> attribute reflects the
			// number how often the source operation of the transition has been
			// executed without taking the corresponding transition but another
			// outgoing transition. If the corresponding transition is taken,
			// <tt>count</tt> is reset to <tt>0</tt>.
			// => re-set <tt>count</tt> of the executed transition to 0.
			executableExecutionInfo.setCount(0);
			// => increment <tt>count</tt> of the other transitions having the
			// same source operation by 1.
			// <code>executedBefore</code> is the source operation of the
			// executable (currently executing transition)
			eurema.Operation sourceOperation = (eurema.Operation) executedBefore;
			for (eurema.Exit eExit : sourceOperation.getExits()) {
				eurema.Transition eTransition = eExit.getOutgoing();
				if (!eTransition.equals(executable)) {
					eurema.ExecutionInformation eTransitionExecutionInfo = eTransition
							.getExecInfo();
					eTransitionExecutionInfo.setCount(eTransitionExecutionInfo
							.getCount() + 1);
				}
			}

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.MegamodelModule getMegamodelModule() {
		return this.eMegamodelModule;
	}
}
