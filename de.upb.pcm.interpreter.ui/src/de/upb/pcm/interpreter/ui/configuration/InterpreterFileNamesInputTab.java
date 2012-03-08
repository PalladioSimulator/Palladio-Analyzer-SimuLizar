package de.upb.pcm.interpreter.ui.configuration;


import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import de.uka.ipd.sdq.workflow.pcm.ConstantsContainer;
import de.uka.ipd.sdq.workflow.pcm.runconfig.FileNamesInputTab;


/**
 * File name input tab for SimuLizar. Uses middleware and eventmiddle ware input fields for PMS
 * models and SDM models.
 */
public class InterpreterFileNamesInputTab extends FileNamesInputTab
{


   /**
    * @see de.uka.ipd.sdq.workflow.launchconfig.tabs.FileNamesInputTab#createControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createControl(final Composite parent)
   {
      super.createControl(parent);

      // Change text of first and second input field
      final Group pmsGroupPms = ((Group) container.getChildren()[0]);
      pmsGroupPms.setText("Optional: Palladio Monitoring Specification Model (PMS) File ");

      final Group pmsGroupSdm = ((Group) container.getChildren()[1]);
      pmsGroupSdm.setText("Optional: Story Diagram Folder (select one file, the other files will be derived)");

   }


   /**
    * @see de.uka.ipd.sdq.workflow.launchconfig.tabs.FileNamesInputTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
    */
   @Override
   public void initializeFrom(final ILaunchConfiguration configuration)
   {

      super.initializeFrom(configuration);
      // Clear default values
      if (eventMiddlewareRepository.getText().equals(ConstantsContainer.DEFAULT_EVENT_MIDDLEWARE_FILE))
      {
         eventMiddlewareRepository.setText("");
      }
      if (mwtextRepository.getText().equals(ConstantsContainer.MWREPOSITORY_FILE))
      {
         mwtextRepository.setText("");
      }

   }


   /**
    * @see de.uka.ipd.sdq.workflow.launchconfig.tabs.FileNamesInputTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
    */
   @Override
   public boolean isValid(final ILaunchConfiguration launchConfig)
   {
      return true;
   }


}
