package de.upb.pcm.interpreter.factories;


import de.uka.ipd.sdq.simucomframework.usage.ClosedWorkloadUserFactory;
import de.upb.pcm.interpreter.access.AbstractPCMModelAccess;


/**
 * An extension of the ClosedWorkloadUserFactory, which is able to hold an AbstractPCMModelAccess.
 * 
 * @author Joachim Meyer
 */
public abstract class ClosedWorkloadScenarioUserFactory extends ClosedWorkloadUserFactory
{

   private final AbstractPCMModelAccess abstractPCMModelAccess;


   /**
    * Constructor
    * 
    * @param abstractPCMModelAccess the AbstractPCMModelAccess to be hold.
    * @param specification the specification of the think time.
    */
   public ClosedWorkloadScenarioUserFactory(final AbstractPCMModelAccess abstractPCMModelAccess,
         final String specification)
   {
      super(abstractPCMModelAccess.getModelHelper().getSimuComModel(), specification);
      this.abstractPCMModelAccess = abstractPCMModelAccess;
   }


   /**
    * @return the AbstractPCMModelAccess.
    */
   protected AbstractPCMModelAccess getAbstractPCMModelAccess()
   {
      return this.abstractPCMModelAccess;
   }


}
