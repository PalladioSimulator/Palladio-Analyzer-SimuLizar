package de.upb.pcm.interpreter.launcher;


import java.util.ArrayList;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowLauncher;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.logging.console.LoggerAppenderStruct;
import de.upb.pcm.interpreter.launcher.jobs.PCMInterpreterRootCompositeJob;


/**
 * Job for launching the pcm interpreter.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMInterpreterLauncher extends SimuComWorkflowLauncher
{

   @Override
   protected IJob createWorkflowJob(final SimuComWorkflowConfiguration config, final ILaunch launch)
         throws CoreException
   {
      return new PCMInterpreterRootCompositeJob(config);
   }


   @Override
   protected ArrayList<LoggerAppenderStruct> setupLogging(final org.apache.log4j.Level logLevel) throws CoreException
   {
      final ArrayList<LoggerAppenderStruct> result = super.setupLogging(logLevel);
      result.add(setupLogger("de.upb.pcm.interpreter", logLevel, Level.DEBUG == logLevel ? DETAILED_LOG_PATTERN
            : SHORT_LOG_PATTERN));

      return result;
   }

}
