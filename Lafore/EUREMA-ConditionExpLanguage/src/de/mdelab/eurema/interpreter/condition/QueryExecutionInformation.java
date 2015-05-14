package de.mdelab.eurema.interpreter.condition;

/**
 * Interface to obtain <tt>count</tt> and <tt>time</tt> information about
 * executable megamodel elements of an individual megamodel module (instance of
 * a Feedback Loop Diagram) to be executed. This information is used in
 * expression conditions for branching the control flow in a Feedback Loop
 * Diagram by decision operations. Such information refers to the <tt>count</tt>
 * (<code>C_SINCE</code>) and <tt>time</tt> ( <code>T_WHEN</code>) information
 * of executable elements of the Feedback Loop Diagram (megamodel).
 * 
 * <p>
 * Therefore, <code>C_SINCE</code> and <code>T_WHEN</code> have an argument
 * referring to a name of a model operation or transition in a Feedback Loop
 * Diagram and they return the <tt>count</tt> and <tt>time</tt> values
 * maintained by the interpreter in the EUREMA model for each operation and
 * transition.
 * </p>
 * 
 * <p>
 * We require that operation names are unique within a Feedback Loop Diagram,
 * which is the scope for evaluating an expression/condition. Transitions names
 * just have to be unique for all transitions that share the same source
 * operation. Thus, transition names can be specified relatively to the source
 * operation as a pattern consisting of the operation name, the separator
 * <tt>::</tt>, and the transition name. However, if the transition name is
 * unique within the Feedback Loop Diagram, it is sufficient to use only the
 * transition name without the operation name and separator.
 * </p>
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface QueryExecutionInformation {

	/**
	 * String that separates the model operation name and the outgoing
	 * transition name, e.g., <tt>opName::transitionName</tt>, to be used in the
	 * expressions for decision operations' conditions.
	 */
	public static String SEPARATOR = "::";

	/**
	 * Represents the <tt>ELSE</tt> expression for decision operations'
	 * conditions.
	 */
	public static String DEFAULT = "ELSE";

	/**
	 * Retrieves the <tt>count</tt> information from the EUREMA model as used in
	 * a condition expression by means of <tt>C_SINCE(query)</tt>.
	 * 
	 * @param query
	 *            query as used as a parameter in <tt>C_SINCE</tt>
	 * @return the <tt>count</tt> information for the <code>query</code>
	 */
	public long retrieveCount(String query);

	/**
	 * Retrieves the <tt>time</tt> information from the EUREMA model as used in
	 * a condition expression by means of <tt>T_WHEN(query)</tt>.
	 * 
	 * @param query
	 *            query as used as a parameter in <tt>T_WHEN</tt>
	 * 
	 * @return the <tt>time</tt> information for the <code>query</code>
	 */
	public long retrieveTime(String query);

}
