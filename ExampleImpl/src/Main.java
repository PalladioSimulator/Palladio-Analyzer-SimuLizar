
	
		
class NutzungsszenarioThread extends de.uka.ipd.sdq.prototype.framework.usage.AbstractOpenScenarioThread {

	public NutzungsszenarioThread(
			de.uka.ipd.sdq.sensorframework.entities.Experiment exp,
			de.uka.ipd.sdq.sensorframework.entities.ExperimentRun expRun,
			de.uka.ipd.sdq.prototype.framework.utils.RunProperties runProps)
	{
		super(exp, expRun, "Response Time of Nutzungsszenario",runProps,"0.29");
	}

	@Override
	protected Runnable getScenarioRunner(de.uka.ipd.sdq.prototype.framework.utils.RunProperties runProps)
	{
		if (runProps.hasOption('R'))
		{
			de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.setRemoteAddress(runProps.getOptionValue('R'));
		}
		else
		{
			de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.setRemoteAddress(de.uka.ipd.sdq.prototype.framework.registry.RmiRegistry.LOCALHOST);
		}
		return new nutzungsszenario.impl.Nutzungsszenario();
	}
}



	
		public class Main extends de.uka.ipd.sdq.prototype.framework.AbstractMain {

			@Override
			protected void initialiseThreads(
					de.uka.ipd.sdq.sensorframework.entities.Experiment exp,
					de.uka.ipd.sdq.sensorframework.entities.ExperimentRun expRun)
			{
				
					
   this.threads.add(new NutzungsszenarioThread(exp, expRun, runProps));

				
			}
		
			@Override
			protected void setupResources()
			{	
				ResourceEnvironment.setUpResources(runProps.getOptionValue('p'), runProps.getOptionValue('H'), runProps.getOptionValue('s'), getAccuracy());
			}
			
			@Override
			protected void initialiseSystems() 
			{
				
					entpackerservice.impl.EntpackerService.main(null);
							
			}
			
			@Override
			protected String[][] getSystems()
			{
				String[][] systems = {
				
					{"entpackerservice.impl.EntpackerService", "EntpackerService"}
				
				};
				
				return systems;
			}
			
			@Override
			protected void initAllocationStorage() {
				AllocationStorage.initSingleton(new AllocationStorage());
			}
			
			/**
			 * @param args
			 */
			public static void main(String[] args)
			{
				new Main().run(args);
			}
		}
	