
	  package entpackerservice.impl.ports;

import java.rmi.RemoteException;

import de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry;
      
   
   /*
	*Port class for ILastverteiler_EntpackerService
	*/
	public class ILastverteiler_EntpackerService extends java.rmi.server.UnicastRemoteObject implements defaultrepository.ILastverteiler, de.uka.ipd.sdq.prototype.framework.port.IPort<entpackerservice.impl.IEntpackerService>, java.rmi.Remote, java.io.Serializable {

		protected static org.apache.log4j.Logger logger = 
			org.apache.log4j.Logger.getLogger(ILastverteiler_EntpackerService.class.getName());
	  
	  	protected entpackerservice.impl.IEntpackerService myCompositeComponent = null;
	  
		protected defaultrepository.ILastverteiler myInnerPort = null;
	     
		public ILastverteiler_EntpackerService(defaultrepository.ILastverteiler myInnerPort, entpackerservice.impl.IEntpackerService myCompositeComponent, String assemblyContext) throws java.rmi.RemoteException {
			this.myInnerPort = myInnerPort;
			this.myCompositeComponent = myCompositeComponent;
		        	        
			de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.registerPort(de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.getRemoteAddress(), 
					RmiRegistry.getRegistryPort(), this, "ILastverteiler_EntpackerService_" + assemblyContext);
			logger.info("Instantiation of port ILastverteiler_EntpackerService completed");	        
		}	     
	     	 	
	    @Override
		public void setContext(Object myContext) throws java.rmi.RemoteException {
	    	myCompositeComponent.setContext(myContext);
		}

		@Override
		public void setComponentFrame(de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> myComponentStackFrame) throws java.rmi.RemoteException {
		}

		@Override
		public entpackerservice.impl.IEntpackerService getComponent() throws java.rmi.RemoteException {
			return myCompositeComponent;
		}	 	
	     	 	 
	 	 

    
	     
	     
   public 
	
   
   de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>

   entpacke0
   ( 
	de.uka.ipd.sdq.simucomframework.variables.StackContext ctx
 )
 throws java.rmi.RemoteException
 {
   	  
   
	//logger.debug("Entering port of composed structure EntpackerService");
	

	
   de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>
 result = myInnerPort.entpacke0(
	     
	ctx
);
	

	return result;


   }

@Override
public void reconfigure(double delta) throws RemoteException {
	myInnerPort.reconfigure(delta);
	
}   

	  }


   