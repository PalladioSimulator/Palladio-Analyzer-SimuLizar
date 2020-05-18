
	  package defaultrepository.impl.ports;

import java.rmi.RemoteException;

import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;

import de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry;
      
   
	  /** 
	   * Operation Provide Role Port class for ILastverteiler_lastverteiler 
	   */
	  public class ILastverteiler_lastverteiler extends de.uka.ipd.sdq.prototype.framework.port.AbstractBasicPort<defaultrepository.impl.Ilastverteiler> implements defaultrepository.ILastverteiler {	  

		  protected static org.apache.log4j.Logger logger = 
			org.apache.log4j.Logger.getLogger(ILastverteiler_lastverteiler.class.getName());
				
		  public ILastverteiler_lastverteiler(defaultrepository.impl.Ilastverteiler myComponent, String assemblyContext) throws java.rmi.RemoteException {
		  		this.myComponent = myComponent;
				de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.registerPort(de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.getRemoteAddress(), 
						RmiRegistry.getRegistryPort(), this, "ILastverteiler_lastverteiler_" + assemblyContext);
		  }
		    
	      


	      
   @OperationExecutionMonitoringProbe	      
   public 
	
   
   de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object>

   entpacke0
   ( 
	de.uka.ipd.sdq.simucomframework.variables.StackContext ctx
 )
 throws java.rmi.RemoteException
 {
   	  
   
	return myComponent.iLastverteiler_entpacke0(
	     
	ctx
);


   }





@Override
public void reconfigure(double delta) throws RemoteException {
	myComponent.reconfigure(delta);
}   

	  }


   