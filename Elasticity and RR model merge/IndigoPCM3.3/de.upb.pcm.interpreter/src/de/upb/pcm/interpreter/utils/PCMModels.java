package de.upb.pcm.interpreter.utils;


import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;


/**
 * Class combining all pcm models.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMModels
{
   private UsageModel usageModel;

   private de.uka.ipd.sdq.pcm.system.System system;

   private Repository repository;

   private Allocation allocation;

   private ResourceEnvironment resourceEnvironment;


   /**
    * @return returns the allocation model.
    */
   public Allocation getAllocation()
   {
      return this.allocation;
   }


   /**
    * @return returns the repository model.
    */
   public Repository getRepository()
   {
      return this.repository;
   }


   /**
    * @return returns the resource environment model.
    */
   public ResourceEnvironment getResourceEnvironment()
   {
      return this.resourceEnvironment;
   }


   /**
    * @return returns the system model.
    */
   public de.uka.ipd.sdq.pcm.system.System getSystem()
   {
      return this.system;
   }


   /**
    * @return returns the usage model.
    */
   public UsageModel getUsageModel()
   {
      return this.usageModel;
   }


   /**
    * @param allocation the allocation model to set.
    */
   public void setAllocation(final Allocation allocation)
   {
      this.allocation = allocation;
   }


   /**
    * @param repository the repository model to set.
    */
   public void setRepository(final Repository repository)
   {
      this.repository = repository;
   }


   /**
    * @param resourceEnvironment the resource environment model to set.
    */
   public void setResourceEnvironment(final ResourceEnvironment resourceEnvironment)
   {
      this.resourceEnvironment = resourceEnvironment;
   }


   /**
    * @param system the system to set.
    */
   public void setSystem(final de.uka.ipd.sdq.pcm.system.System system)
   {
      this.system = system;
   }


   /**
    * @param usageModelt the usage model to set.
    */
   public void setUsageModel(final UsageModel usageModel)
   {
      this.usageModel = usageModel;
   }


}
