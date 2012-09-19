package de.upb.pcm.interpreter.ui.configuration;


import org.eclipse.debug.ui.ILaunchConfigurationDialog;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuTabGroup;


/**
 * Configuration tab for SimuLizar, changes file names input tab of the SimuCom configuration tab.
 * 
 * @author Joachim Meyer
 */
public class InterpreterConfigurationTab extends SimuTabGroup
{

   /**
    * @see de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
    *      java.lang.String)
    */
   @Override
   public void createTabs(final ILaunchConfigurationDialog dialog, final String mode)
   {
      super.createTabs(dialog, mode);

      // change file configuration tab
      super.getTabs()[0] = new InterpreterFileNamesInputTab();
   }
}
