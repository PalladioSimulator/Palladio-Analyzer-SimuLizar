package de.upb.pcm.interpreter.factories;


import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.upb.pcm.interpreter.access.AbstractPCMModelAccess;
import de.upb.pcm.interpreter.access.AbstractPMSModelAccess;
import de.upb.pcm.interpreter.access.AllocationAccess;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.access.ResourceEnvironmentAccess;
import de.upb.pcm.interpreter.access.SystemAccess;
import de.upb.pcm.interpreter.access.UsageModelAccess;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter;
import de.upb.pcm.interpreter.interpreter.RDSeffInterpreter;
import de.upb.pcm.interpreter.interpreter.RepositoryInterpreter;
import de.upb.pcm.interpreter.interpreter.UsageModelUsageScenarioInterpreter;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Factory for pcm and pms model accesses and pcm model interpreters.
 * 
 * @author Joachim Meyer
 * 
 */
public class ModelAccessFactory implements IModelAccessFactory
{


   private final ModelHelper modelHelper;


   /**
    * Constructor
    * 
    * @param modelHelper the model helper.
    */
   public ModelAccessFactory(final ModelHelper modelHelper)
   {
      super();
      this.modelHelper = modelHelper;


   }


   /**
    * @return the modelHelper.
    */
   private ModelHelper getModelHelper()
   {
      return this.modelHelper;
   }


   /**
    * 
    * @see de.upb.pcm.interpreter.interfaces.IModelAccessFactory#getPCMModelAccess(int,
    *      de.upb.pcm.interpreter.simulation.InterpreterDefaultContext)
    */
   @Override
   public AbstractPCMModelAccess getPCMModelAccess(final int modelAccessType, final InterpreterDefaultContext context)
   {

      switch (modelAccessType)
      {
         case SYSTEM_ACCESS:
            return new SystemAccess(context, getModelHelper());
         case RESOURCE_ENVIRONMENT_ACCESS:
            return new ResourceEnvironmentAccess(context, getModelHelper());
         case ALLOCATION_ACCESS:
            return new AllocationAccess(context, getModelHelper());
         case USAGE_MODEL_ACCESS:
            return new UsageModelAccess(context, getModelHelper());

         default:
            throw new IllegalArgumentException("No reader for readerType " + modelAccessType + " found");

      }
   }


   /**
    * 
    * @see de.upb.pcm.interpreter.interfaces.IModelAccessFactory#getPCMModelInterpreter(int,
    *      de.upb.pcm.interpreter.simulation.InterpreterDefaultContext,
    *      de.uka.ipd.sdq.pcm.core.composition.AssemblyContext)
    */
   // TODO: Refactor me!!!
   @Override
   public AbstractPCMModelInterpreter getPCMModelInterpreter(final int interpreterType,
         final InterpreterDefaultContext context, final AssemblyContext assemblyContext,
         final ProbeSpecContext probeSpecContext)
   {

      switch (interpreterType)
      {

         case USAGEMODEL_USAGESCENARIO_INTERPRETER:
            return new UsageModelUsageScenarioInterpreter(context, probeSpecContext, getModelHelper());

         case RDSEFF_INTERPRETER:
            return new RDSeffInterpreter(context, probeSpecContext, getModelHelper(), assemblyContext);

         case REPOSITORY_INTERPRETER:
            return new RepositoryInterpreter(context, probeSpecContext, getModelHelper());

         default:
            throw new IllegalArgumentException("No interpreter for interpreterType " + interpreterType + " found");

      }
   }


   /**
    * 
    * @see de.upb.pcm.interpreter.interfaces.IModelAccessFactory#getPMSModelAccess()
    */
   @Override
   public AbstractPMSModelAccess getPMSModelAccess()
   {
      return new PMSAccess(getModelHelper());
   }


}
