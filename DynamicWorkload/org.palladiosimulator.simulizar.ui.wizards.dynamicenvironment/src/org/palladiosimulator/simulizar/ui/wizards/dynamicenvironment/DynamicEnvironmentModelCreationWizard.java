package org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.pages.DEModelNewFileCreationPage;
import org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.pages.SelectUsageModelPage;
import org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.pages.WorkModelPage;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import dlim.DlimFactory;
import dlim.DlimPackage;
import dlim.Sequence;
import dlim.TimeDependentWorkFunctionContainer;
import dlim.WorkLoadSequence;
import dlim.presentation.DlimEditorPlugin;
import dlim.presentation.DlimModelWizard;
import dlim.presentation.DlimModelWizard.DlimModelWizardInitialObjectCreationPage;
import dlim.presentation.DlimModelWizard.DlimModelWizardNewFileCreationPage;


/**
 * Class for wizard, used for creating a dynamic environment (workload) model
 * @author Vinay Akkasetty Gopal
 */
public class DynamicEnvironmentModelCreationWizard extends Wizard implements INewWizard  {
	
	private static final Logger LOGGER = Logger.getLogger(DynamicEnvironmentModelCreationWizard.class);
	
	private DEModelNewFileCreationPage newFileCreationPage = null;
	private SelectUsageModelPage selectUsageModelPage = null;
	private WorkModelPage workModelPage = null;
	
	protected DlimPackage dlimPackage = DlimPackage.eINSTANCE;
	protected DlimFactory dlimFactory = dlimPackage.getDlimFactory();
	private UsageModel usageModel =  null;
	private String modelFile = null;
	private WorkLoadSequence rootWLSequence;
	
	protected IStructuredSelection selection;
	protected Resource diagram;
	protected IWorkbench workbench;
	private boolean openOnCreate = true;
	
	private Resource resource = null;

	public static final List<String> FILE_EXTENSIONS = Collections.unmodifiableList(Arrays.asList(DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameExtensions").split("\\s*,\\s*")));
	public static final String FORMATTED_FILE_EXTENSIONS = DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameExtensions").replaceAll("\\s*,\\s*", ", ");
	
	public DynamicEnvironmentModelCreationWizard() {
		super();
		rootWLSequence = dlimFactory.createWorkLoadSequence();
	}
	
	/**
	 * Initializes the wizard and creates the object instances of the wizard pages
	 * @param IWorkbench
	 * @param IStructuredSelection
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) 
	{
		this.workbench = workbench;
		this.selection = selection;
		this.setWindowTitle("Model Dynamic Work and Loads");
		this.newFileCreationPage = new DEModelNewFileCreationPage("Create a new Dynamic Environment Model file", selection);
		this.selectUsageModelPage = new SelectUsageModelPage("Select Usage Model", selection);
		this.workModelPage = new WorkModelPage("Work Parameters", rootWLSequence);
		
	}
	
	//reads the file name of the new DLIM file
	private String getModelName() {
		String names[] = newFileCreationPage.getFileName().split("\\.");
		String name = "";
		if (names.length > 1) {
			for (int i = 0; i < names.length - 1; i++) {
				name += "." + names[i];
			}
			return name.substring(1);
		} else if (names.length == 1) {
			return names[0].trim();
		} else {
			return newFileCreationPage.getFileName();
		}
	}

	/**
	 * Do the work after everything is specified.
	 */
	@Override
	public boolean performFinish() {
		try {
			// Remember the file.
			final IFile modelFile = newFileCreationPage.getModelFile();

			// Do the work within an operation.
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
					@Override
					protected void execute(IProgressMonitor progressMonitor) {
						try {
							// Create a resource set
							ResourceSet resourceSet = new ResourceSetImpl();

							// Get the URI of the model file.
							URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

							// Create a resource for this file.
							Resource resource = resourceSet.createResource(fileURI);

							// Add the initial model object to the contents.
							EObject rootObject = createInitialModel();
							if (rootObject != null) {
								resource.getContents().add(rootObject);
							}

							// Save the contents of the resource to the file system.
//							Map<Object, Object> options = new HashMap<Object, Object>();
//							options.put(XMLResource.OPTION_ENCODING, initialObjectCreationPage.getEncoding());
							resource.save(Collections.EMPTY_MAP);			
						}
						catch (Exception exception) {
							DlimEditorPlugin.INSTANCE.log(exception);
						}
						finally {
							progressMonitor.done();
						}
					}
				};

			getContainer().run(false, false, operation);

			// Select the new file resource in the current view.
			//
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(modelFile);
				getShell().getDisplay().asyncExec
					(new Runnable() {
						 public void run() {
							 ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
						 }
					 });
			}

			// Open an editor on the new file.
			try {
				page.openEditor(new FileEditorInput(modelFile),workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());					 	 
			}
			catch (PartInitException exception) {
			MessageDialog.openError(workbenchWindow.getShell(), DlimEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage());
				return false;
			}
		System.out.println("Usage Scenarios: " + this.usageModel.getUsageScenario_UsageModel().get(0).getEntityName());
		return true;
	}
		catch (Exception exception) {
			DlimEditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}
	
	protected EObject createInitialModel() {
		//Sequence rootObject = dlimFactory.createSequence();
		rootWLSequence.setName(getModelName());
		for (UsageScenario us : this.usageModel.getUsageScenario_UsageModel()) {
			TimeDependentWorkFunctionContainer tdwfContainer = dlimFactory.createTimeDependentWorkFunctionContainer();
			tdwfContainer.setName(us.getEntityName());
			tdwfContainer.setWork(us);
			Sequence rootLoadObject = dlimFactory.createSequence();
			tdwfContainer.setLoadSequence(rootLoadObject);
			rootWLSequence.getWorkFunctionContainers().add(tdwfContainer);
		}
		return rootWLSequence;
	}
	
	/**
	 * Adds the pages to the wizard in the specified order
	 */
	@Override
	public void addPages() {
		super.addPages();
		// Create a page, set the title, and the initial model file name.
				
		newFileCreationPage.setTitle("Dynamic Environment Model");
		newFileCreationPage.setDescription("Create a new work (and) load model");
		newFileCreationPage.setFileName(DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameDefaultBase") + "." + FILE_EXTENSIONS.get(0));
		addPage(newFileCreationPage);

		// Try and get the resource selection to determine a current directory for the file dialog.
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if its a file.
				IResource selectedResource = (IResource)selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
						selectedResource = selectedResource.getParent();
				}

				// This gives us a directory...
				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					// Set this for the container.
					newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					String defaultModelBaseFilename = DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameDefaultBase");
					String defaultModelFilenameExtension = FILE_EXTENSIONS.get(0);
					String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
					for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
						modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
					}
					newFileCreationPage.setFileName(modelFilename);
				}
			}
		}
		
		this.addPage(this.selectUsageModelPage);
		this.addPage(this.workModelPage);
	}
	
	public void setUsageModel(UsageModel model, String name) {
		System.out.println("Usage Scenarios: " + model.getUsageScenario_UsageModel().get(0).getEntityName());
		this.usageModel = model;	
		this.workModelPage.setUsageModel(model);
	}
	
	/**
	 * Enables the finish button of the wizard if the last page of the wizard is reached
	 * @return boolean
	 */
	public boolean canFinish() {
//		if (this.getContainer().getCurrentPage() == adaptVerificationPropertiesPage) 
			return true;
//		else
//			return false;
	}
	
	
}
