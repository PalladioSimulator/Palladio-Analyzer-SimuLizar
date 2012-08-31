
      
	
	
   package entpackerservice.impl;
  
  
   public class EntpackerService implements entpackerservice.impl.IEntpackerService, java.io.Serializable  
   {
   		private static final long serialVersionUID = 1L;
   
		private static org.apache.log4j.Logger logger = 
			org.apache.log4j.Logger.getLogger(EntpackerService.class.getName());
	  

    
	
	
   public EntpackerService(String assemblyContext) {


		
		
		logger.info("Creating composed structure EntpackerService");
		
		
	

	    
	    
			this.setContext(new entpackerservice.impl.contexts.EntpackerServiceContext());
		
	      
		
			/* And finally, my ports */
			try{
			
	m_portAngeboten_ILastverteiler = new entpackerservice.impl.ports.ILastverteiler_EntpackerService(
		
			
				mylastverteilerAK.getComponent().getPortAngeboten_ILastverteiler_Lastverteiler()
			
		, this, assemblyContext
		);

			} catch(java.rmi.RemoteException e){
			  logger.error("Failed to initialise port for EntpackerService");
			}
			
			
		
	} 
		
      
	
      /* Composed child components member variables */
      
	
		/**
		 *  Port for component lastverteiler
		 */
		protected de.uka.ipd.sdq.prototype.framework.port.IPort<defaultrepository.impl.lastverteiler> mylastverteilerAK = null;
	
	

	
		/**
		 *  Port for component server
		 */
		protected de.uka.ipd.sdq.prototype.framework.port.IPort<defaultrepository.impl.server> myserver1AK = null;
	
	

	
		/**
		 *  Port for component server
		 */
		protected de.uka.ipd.sdq.prototype.framework.port.IPort<defaultrepository.impl.server> myserver2AK = null;
	
	

            
      /**
      * Inner Structure initialisation
      */
      
	  @SuppressWarnings("unchecked")
      
      private void initInnerComponents() {

		 try {
			
				
	
		mylastverteilerAK = (de.uka.ipd.sdq.prototype.framework.port.IPort<defaultrepository.impl.lastverteiler>) de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.lookup("ILastverteiler_lastverteiler__bIrK8OuTEeCuhfIsXFGDcQ");

	

	
		myserver1AK = (de.uka.ipd.sdq.prototype.framework.port.IPort<defaultrepository.impl.server>) de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.lookup("IServer_server__gBXYMOuTEeCuhfIsXFGDcQ");

	

	
		myserver2AK = (de.uka.ipd.sdq.prototype.framework.port.IPort<defaultrepository.impl.server>) de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.lookup("IServer_server__g7v80OuTEeCuhfIsXFGDcQ");

	

			

			 
			  /* First, initialise composite child structures */
			  
			  
			  /* Then initialise basic components */
			  
				  initlastverteilerAK();
			  
				  initserver1AK();
			  
				  initserver2AK();
			  
			  
    	  
    	  }
			catch (java.rmi.RemoteException e) {
				e.printStackTrace();
			}
      }

      
   private void initlastverteilerAK() throws java.rmi.RemoteException { 
      defaultrepository.impl.contexts.lastverteilerContext context = new defaultrepository.impl.contexts.lastverteilerContext(
         
	
		
			/* From Connector _WCGrAOuZEeCuhfIsXFGDcQ */
			
				myserver1AK.getComponent().getPortAngeboten_IServer_Server()
			
	   	
	
,
	
		
			/* From Connector _WjdroOuZEeCuhfIsXFGDcQ */
			
				myserver2AK.getComponent().getPortAngeboten_IServer_Server()
			
	   	
	

         
      );
      
	
		
    
  
	//Initialise Component Parameters
	de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> componentStackFrame = 
		new de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>();
	
		
	mylastverteilerAK.setComponentFrame(componentStackFrame);
  

	

      
      
      mylastverteilerAK.setContext(context);
   }

   private void initserver1AK() throws java.rmi.RemoteException { 
      defaultrepository.impl.contexts.serverContext context = new defaultrepository.impl.contexts.serverContext(
         
         
      );
      
	
		
    
  
	//Initialise Component Parameters
	de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> componentStackFrame = 
		new de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>();
	
		
	myserver1AK.setComponentFrame(componentStackFrame);
  

	

      
      
      myserver1AK.setContext(context);
   }

   private void initserver2AK() throws java.rmi.RemoteException { 
      defaultrepository.impl.contexts.serverContext context = new defaultrepository.impl.contexts.serverContext(
         
         
      );
      
	
		
    
  
	//Initialise Component Parameters
	de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> componentStackFrame = 
		new de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>();
	
		
	myserver2AK.setComponentFrame(componentStackFrame);
  

	

      
      
      myserver2AK.setContext(context);
   }

      /* Inner Structure initialisation end */




    
	
    

	
	 
	
    protected defaultrepository.ILastverteiler m_portAngeboten_ILastverteiler = null;


	
   	
   public defaultrepository.ILastverteiler getPortAngeboten_ILastverteiler () {
      return m_portAngeboten_ILastverteiler;
   }

   	

	
   
   

   
   

   
   protected entpackerservice.impl.contexts.IEntpackerServiceContext myContext = null;

   
	
   public void setContext(Object myContext) {
      this.myContext = (entpackerservice.impl.contexts.IEntpackerServiceContext)myContext;
      
      	initInnerComponents();
      
   }



	
	
	public static void main(String... args) {
		logger.info("Main method of composed structure EntpackerService called");

		String ip = de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.getIpFromArguments(args);
		String assemblyContext = de.uka.ipd.sdq.prototype.framework.AbstractMain.getAssemblyContextFromArguments(args);
		
		de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.setRemoteAddress(ip);
		
		new entpackerservice.impl.EntpackerService(assemblyContext);
	}


	
   }

	
	




   