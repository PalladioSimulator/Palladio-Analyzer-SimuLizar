

		public class ResourceEnvironment extends de.uka.ipd.sdq.prototype.framework.AbstractResourceEnvironment {
		
			/* All resources are located on this machine, regardless of their resource container
			 * in the model */
			 
			public static void setUpResources(String cpuStrategy, String hddStrategy, String calibrationPath, de.uka.ipd.sdq.measurement.strategies.activeresource.DegreeOfAccuracyEnum accuracy){
				String idContainer = de.uka.ipd.sdq.prototype.framework.AbstractAllocationStorage.getActiveContainer();
		   		
		//active Resources of container lvk (ID=_yc6fwOuOEeCuhfIsXFGDcQ)
		if (idContainer.equals("_yc6fwOuOEeCuhfIsXFGDcQ")) {
			
	
		setUpCPU(cpuStrategy, calibrationPath, accuracy, "1000");
	

	 
		setUpHDD(hddStrategy, calibrationPath, accuracy, "300");
	

		}

 
		//active Resources of container sk1 (ID=_1enWMOuOEeCuhfIsXFGDcQ)
		if (idContainer.equals("_1enWMOuOEeCuhfIsXFGDcQ")) {
			
	
		setUpCPU(cpuStrategy, calibrationPath, accuracy, "1000");
	

	 
		setUpHDD(hddStrategy, calibrationPath, accuracy, "300");
	

		}

 
		//active Resources of container sk2 (ID=_yHnvwOuPEeCuhfIsXFGDcQ)
		if (idContainer.equals("_yHnvwOuPEeCuhfIsXFGDcQ")) {
			
	
		setUpCPU(cpuStrategy, calibrationPath, accuracy, "1000");
	

	 
		setUpHDD(hddStrategy, calibrationPath, accuracy, "300");
	

		}


			}
		}
	