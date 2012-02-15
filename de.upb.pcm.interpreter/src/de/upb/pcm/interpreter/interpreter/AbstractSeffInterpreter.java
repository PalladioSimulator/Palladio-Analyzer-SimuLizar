package de.upb.pcm.interpreter.interpreter;


import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Abstract interpreter base class for SEFFs in case the pcm meta model will be extended.
 * 
 * @author Joachim Meyer
 * 
 */
public abstract class AbstractSeffInterpreter extends AbstractRepositoryInterpreter
{

   /**
    * The assembly context of the corresponding component.
    */
   private final AssemblyContext assemblyContext;


   /**
    * Constructor
    * 
    * @param contex the interpreter default context for the pcm model interpreter, may be null.
    * @param modelHelper the model helper.
    * @param assemblyContext the assembly context of the corresponding component.
    */
   public AbstractSeffInterpreter(final InterpreterDefaultContext context, final ModelHelper modelHelper,
         final AssemblyContext assemblyContext)
   {
      super(context, modelHelper);
      this.assemblyContext = assemblyContext;
   }


   /**
    * @return the assemblyContext of the corresponding component.
    */
   protected AssemblyContext getAssemblyContext()
   {
      return this.assemblyContext;
   }


}
