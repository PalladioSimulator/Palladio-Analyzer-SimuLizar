package de.upb.pcm.interpreter.switches;


import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.seff.util.SeffSwitch;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.upb.pcm.interpreter.interfaces.IPCMModelSwitch;
import de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter;
import de.upb.pcm.interpreter.interpreter.AbstractSeffInterpreter;


/**
 * Abstract base class for SEFF switches, such as RDSEFFS.
 * 
 * @author Joachim Meyer
 * 
 * @param <T> return type of switch methods.
 */
public abstract class AbstractSeffSwitch<T> extends SeffSwitch<T> implements IPCMModelSwitch<T>
{

   protected static final Logger logger = Logger.getLogger(AbstractSeffSwitch.class.getName());

   private final AbstractPCMModelInterpreter modelInterpreter;

   // Stack frame used to store result variable
   private final SimulatedStackframe<Object> temporaryResultStackFrame;

   private final AssemblyContext assemblyContext;


   /**
    * Constructor
    * 
    * @param modelInterpreter the corresponding pcm model interpreter holding this switch.
    * @param assemblyContext the assembly context of the component of the SEFF.
    */
   public AbstractSeffSwitch(final AbstractPCMModelInterpreter modelInterpreter, final AssemblyContext assemblyContext)
   {
      this.modelInterpreter = modelInterpreter;
      temporaryResultStackFrame = new SimulatedStackframe<Object>();
      this.assemblyContext = assemblyContext;
   }


   /**
    * @return returns the assemblyContext of the component of this SEFF.
    */
   protected AssemblyContext getAssemblyContext()
   {
      return this.assemblyContext;
   }


   /**
    * @return returns the corresponding pcm model interpreter holding this switch.
    */
   protected AbstractSeffInterpreter getModelInterpreter()
   {
      return (AbstractSeffInterpreter) this.modelInterpreter;
   }


   /**
    * @return returns the stackframe containing the result parameter.
    */
   public SimulatedStackframe<Object> getTemporaryResultStackFrame()
   {
      return this.temporaryResultStackFrame;
   }


}
