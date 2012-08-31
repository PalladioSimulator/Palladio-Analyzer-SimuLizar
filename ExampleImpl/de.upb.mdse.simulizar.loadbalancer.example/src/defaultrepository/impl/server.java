
	   
	

	   
	   package defaultrepository.impl; 

	      
	   
public class server

	   

	   implements defaultrepository.impl.Iserver, java.io.Serializable
	   {
	   	  
     
	private static org.apache.log4j.Logger logger = 
			org.apache.log4j.Logger.getLogger(server.class.getName());
			
	
	
	
	
	
	
	public server(String assemblyContext) {

		
		
		
		
		
			m_portAngeboten_IServer_Server = init_Angeboten_IServer_Server(assemblyContext);
		
		
		
	}

 
	      
	
    

	
   	
	
    protected defaultrepository.IServer m_portAngeboten_IServer_Server = null;

	private defaultrepository.impl.ports.IServer_server init_Angeboten_IServer_Server(String assemblyContext) {
		try {
			return new defaultrepository.impl.ports.IServer_server(this, assemblyContext);
		} catch (java.rmi.RemoteException e) {
			logger.error("Failed to initialise port defaultrepository.impl.ports.IServer_server");
		}
		return null;
	}


   	
   	
   public defaultrepository.IServer getPortAngeboten_IServer_Server () {
      return m_portAngeboten_IServer_Server;
   }

   	

	      
   
   

   
   

   
   protected defaultrepository.impl.contexts.IserverContext myContext = null;

   
	
   public void setContext(Object myContext) {
      this.myContext = (defaultrepository.impl.contexts.IserverContext)myContext;
      
   }



	      
	
	
   public 
	
   de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>

      iServer_entpacke0
         (
	de.uka.ipd.sdq.simucomframework.variables.StackContext ctx
)

   {
   	  
 
      
   	  
   	  	
	      
   
    

 
  
   
    // measure time for SEFF  
    
	

   
  
 


	
	
		de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> resultStackFrame =
			new de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>();
		de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> methodBodyStackFrame =
			ctx.getStack().currentStackFrame();
		if (this.myDefaultComponentStackFrame.getContents().size() > 0) {
			methodBodyStackFrame.addVariables(this.myDefaultComponentStackFrame);
		}
		if (this.myComponentStackFrame.getContents().size() > 0) {
			methodBodyStackFrame.addVariables(this.myComponentStackFrame);
		}
		
		
			
				
					
				
			
		
	


   
      
   
/* InternalAction - START */
    // software failures:
	
	

	// direct resource demands:
	
	
	
   {
      double demand = ctx.evaluate("datei.BYTESIZE*2", Double.class);


	
		de.uka.ipd.sdq.prototype.framework.strategies.DemandConsumerStrategiesRegistry.singleton()
	    	.getStrategyFor(de.uka.ipd.sdq.measurement.strategies.activeresource.ResourceTypeEnum.CPU).consume(demand);
	
		

   }


	

	// infrastructure calls:
    
/* InternalAction - END */

   
      
   
/* InternalAction - START */
    // software failures:
	
	

	// direct resource demands:
	
	
	
   {
      double demand = ctx.evaluate("datei.BYTESIZE + IntPMF[(20;0.5)(10;0.5)]", Double.class);


	
		de.uka.ipd.sdq.prototype.framework.strategies.DemandConsumerStrategiesRegistry.singleton()
	    	.getStrategyFor(de.uka.ipd.sdq.measurement.strategies.activeresource.ResourceTypeEnum.HDD).consume(demand);
	
		

   }


	

	// infrastructure calls:
    
/* InternalAction - END */

   
      
   
	

 
  
   
    
	

   
  
 


	
	
	return resultStackFrame;
	


   

   

   

   

    	
   	  
   	  
   }   


	

		  
	
	// Component Parameter Defaults
	// TODO: The stackframes are not yet initialised by calling setComponentFrame in Protocom, thus initialise them here, too
	protected de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> myDefaultComponentStackFrame = new de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>();

	// Component Parameter setter
	// TODO: The stackframes are not yet initialised by calling setComponentFrame in Protocom, thus initialise them here, too
	protected de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> myComponentStackFrame = new de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>();
	
	public void setComponentFrame(de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> myComponentStackFrame) {
		this.myComponentStackFrame = myComponentStackFrame;	
		this.myDefaultComponentStackFrame = new de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>();
		
			
		
	}

	
	
		
	public static void main(String... args) {
		logger.info("Main method of basic component server called");

		String ip = de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.getIpFromArguments(args);
		String assemblyContext = de.uka.ipd.sdq.prototype.framework.AbstractMain.getAssemblyContextFromArguments(args);
		
		de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.setRemoteAddress(ip);
		
		new defaultrepository.impl.server(assemblyContext);
	}

	

	   }
	   

	