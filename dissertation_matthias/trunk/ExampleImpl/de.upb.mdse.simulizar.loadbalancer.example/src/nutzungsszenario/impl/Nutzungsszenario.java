
		package nutzungsszenario.impl;
		public class Nutzungsszenario implements java.lang.Runnable
		{
			protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
		
			
	
   protected defaultrepository.ILastverteiler m_portAngeboten_ILastverteiler = null;



			public Nutzungsszenario() {
				

			
	              
    {
		//get the business interface
		
		
		
		// Wait for system
		m_portAngeboten_ILastverteiler = (defaultrepository.ILastverteiler)de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.lookup("ILastverteiler_EntpackerService_");
	}


				
		        
    

		//FIXME: why is expRun needed?
        // expRun = de.uka.ipd.sdq.prototype.framework.AbstractMain.getLatestExperimentRun();
        
        //FIXME: declare and initialise sensors  

		ctx.getStack().createAndPushNewStackFrame();
		
		// de.uka.ipd.sdq.simucomframework.variables.cache.StoExCache
		//		.initialiseStoExCache(new de.uka.ipd.sdq.probfunction.math.impl.DefaultRandomGenerator());
		
		de.uka.ipd.sdq.probfunction.math.IProbabilityFunctionFactory probFunctionFactory = de.uka.ipd.sdq.probfunction.math.impl.ProbabilityFunctionFactoryImpl.getInstance();
		
		probFunctionFactory.setRandomGenerator(new de.uka.ipd.sdq.probfunction.math.impl.DefaultRandomGenerator());
		de.uka.ipd.sdq.simucomframework.variables.cache.StoExCache.initialiseStoExCache(probFunctionFactory);



			}
			
			
	private	de.uka.ipd.sdq.simucomframework.variables.StackContext ctx = new de.uka.ipd.sdq.simucomframework.variables.StackContext();
	
	@org.junit.Test public void scenarioRunner()
	{
		de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> outerStackframe = ctx.getStack().createAndPushNewStackFrame();
		
		
	

		{	
			
   

   
      
   
	
    
	
	
	try {
	
	// Start Simulate an external call
	de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> currentFrame = ctx.getStack().currentStackFrame();
	// prepare stackframe
	de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> stackframe = ctx.getStack().createAndPushNewStackFrame();
	
		
			
				
					stackframe.addValue("datei.BYTESIZE",
					   	ctx.evaluate("IntPMF[ (100;0.5) (50;0.5) ]",currentFrame));
				
			
		
	


	
		
	

	
	de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> callResult =


   	m_portAngeboten_ILastverteiler.entpacke0
	   	(
	ctx
);
	
	
	// Stop the time measurement
	
		
	

	
	
	

	
	} catch (java.rmi.RemoteException e) {
		
	}
	finally
	{
		
 	ctx.getStack().removeStackFrame();

	}
	// END Simulate an external call





   
      
   

   

   

   


		}
		
	

		
		ctx.getStack().removeStackFrame();
	}

			
			public void run() {
				scenarioRunner();
			}
		}
	