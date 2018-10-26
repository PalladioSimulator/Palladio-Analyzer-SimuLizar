package org.palladiosimulator.simulizar.events;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;

import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;

public class EventsTransformationExtensionTab extends AbstractLaunchConfigurationTab {
	private static final Boolean DEFAULT_SIMULATE_EVENTS = false;

	private Image infoImage;
	
	private Button simulateFailuresButton;
	private Text eventMiddlewareRepository;
	
	private Button storeTransformedModelsButton;
	private Text storeTransformedModelsProject;
	
	@Override
	public final void createControl(final Composite parent) {
		infoImage = getImage("information.png");
		
		final SelectionListener selectionListener = new SelectionListener() {

			public void widgetDefaultSelected(final SelectionEvent e) {
				EventsTransformationExtensionTab.this.setDirty(true);
				EventsTransformationExtensionTab.this.updateLaunchConfigurationDialog();
			}

			public void widgetSelected(final SelectionEvent e) {
				EventsTransformationExtensionTab.this.setDirty(true);
				EventsTransformationExtensionTab.this.updateLaunchConfigurationDialog();
			}
		};
		
		final ModifyListener modifyListener = (ModifyEvent e) -> {
				EventsTransformationExtensionTab.this.setDirty(true);
				EventsTransformationExtensionTab.this.updateLaunchConfigurationDialog();
			};
		
		
		// Create the top-level container:
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		container.setLayout(new GridLayout());
		
		// Warning label because these settings are only effective when using SimuLizar
		CLabel lblChooseEventSimInfo = new CLabel(container, SWT.NONE);
        lblChooseEventSimInfo.setText("These settings are only effective when using SimuLizar.");
        lblChooseEventSimInfo.setImage(infoImage);

		// Create reliability section:
		final Group eventGroup = new Group(container, SWT.NONE);
		eventGroup.setText("Events");
		final GridData gdReliabilityGroup = new GridData(SWT.FILL, SWT.CENTER, true, false);
		eventGroup.setLayoutData(gdReliabilityGroup);
		eventGroup.setLayout(new GridLayout());
		simulateFailuresButton = new Button(eventGroup, SWT.CHECK);
		final GridData gdSimulateEventsButton = new GridData(SWT.FILL, SWT.CENTER, true, false);
		simulateFailuresButton.setLayoutData(gdSimulateEventsButton);
		simulateFailuresButton.setText("Simulate Events");
		simulateFailuresButton.addSelectionListener(selectionListener);

		eventMiddlewareRepository = new Text(container, SWT.SINGLE | SWT.BORDER);
		TabHelper.createFileInputSection(container, modifyListener, "Event Middleware Repository File",
				ConstantsContainer.REPOSITORY_EXTENSION, eventMiddlewareRepository,
				"Select Event Middleware Repository File", getShell(),
				ConstantsContainer.DEFAULT_EVENT_MIDDLEWARE_FILE);
		
		final Group temporaryGroup = new Group(container, SWT.NONE);
		temporaryGroup.setText("Storage");
		final GridData gdTemporaryGroup = new GridData(SWT.FILL, SWT.CENTER, true, false);
		temporaryGroup.setLayoutData(gdTemporaryGroup);
		temporaryGroup.setLayout(new GridLayout());
		storeTransformedModelsButton = new Button(temporaryGroup, SWT.CHECK);
		storeTransformedModelsButton.setText("Store Transformed Models");
		storeTransformedModelsButton.addSelectionListener(selectionListener);
		storeTransformedModelsProject = new Text(temporaryGroup, SWT.SINGLE | SWT.BORDER);
		storeTransformedModelsProject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		storeTransformedModelsProject.addModifyListener(modifyListener);
	}
	
	private static Image getImage(String file) {
        return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/" + file).createImage();
	}

	@Override
	public final String getName() {
		return "Events Transformation";
	}

	@Override
	public final void initializeFrom(final ILaunchConfiguration configuration) {
		try {
			simulateFailuresButton.setSelection(
					configuration.getAttribute(EventsTransformationWorkflowExtensionJob.SIMULATE_EVENTS, true));
			eventMiddlewareRepository.setText(
					configuration.getAttribute(EventsTransformationWorkflowExtensionJob.EVENT_MIDDLEWARE_FILE, ConstantsContainer.DEFAULT_EVENT_MIDDLEWARE_FILE));
			storeTransformedModelsButton.setSelection(
					configuration.getAttribute(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS, true));
			storeTransformedModelsProject.setText(
					configuration.getAttribute(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS_PROJECT, ConstantsContainer.DEFAULT_TEMPORARY_DATA_LOCATION));
		} catch (CoreException e) {
			simulateFailuresButton.setSelection(false);
			eventMiddlewareRepository.setText(ConstantsContainer.DEFAULT_EVENT_MIDDLEWARE_FILE);
			storeTransformedModelsButton.setSelection(true);
			storeTransformedModelsProject.setText(ConstantsContainer.DEFAULT_TEMPORARY_DATA_LOCATION);
		}
	}

	@Override
	public final void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.SIMULATE_EVENTS,
				this.simulateFailuresButton.getSelection());
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.EVENT_MIDDLEWARE_FILE,
				this.eventMiddlewareRepository.getText());
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS, storeTransformedModelsButton.getSelection());
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS_PROJECT, storeTransformedModelsProject.getText());
	}

	@Override
	public final void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.SIMULATE_EVENTS, DEFAULT_SIMULATE_EVENTS);
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.EVENT_MIDDLEWARE_FILE,
				ConstantsContainer.DEFAULT_EVENT_MIDDLEWARE_FILE);
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS, true);
		configuration.setAttribute(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS_PROJECT, ConstantsContainer.DEFAULT_TEMPORARY_DATA_LOCATION);
	}

}
