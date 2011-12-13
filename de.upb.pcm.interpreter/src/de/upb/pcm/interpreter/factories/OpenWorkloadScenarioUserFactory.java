package de.upb.pcm.interpreter.factories;


import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUserFactory;
import de.upb.pcm.interpreter.access.AbstractPCMModelAccess;


/**
 * An extension of the OpenWorkloadUserFactory, which is able to hold an AbstractPCMModelAccess.
 * 
 * @author Joachim Meyer
 */
public abstract class OpenWorkloadScenarioUserFactory extends OpenWorkloadUserFactory
{

   private final AbstractPCMModelAccess abstractPCMModelAccess;


   /**
    * Constructor
    * 
    * @param abstractPCMModelAccess the AbstractPCMModelAccess to be hold.
    */
   public OpenWorkloadScenarioUserFactory(final AbstractPCMModelAccess abstractPCMModelAccess)
   {
      super(abstractPCMModelAccess.getModelHelper().getSimuComModel());
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
