package org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import dlim.presentation.DlimEditorPlugin;

public class DEModelNewFileCreationPage extends WizardNewFileCreationPage {
	
	public static final List<String> FILE_EXTENSIONS = Collections.unmodifiableList(Arrays.asList(DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameExtensions").split("\\s*,\\s*")));
	public static final String FORMATTED_FILE_EXTENSIONS = DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameExtensions").replaceAll("\\s*,\\s*", ", ");
	
	public DEModelNewFileCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}
	
	@Override
	protected boolean validatePage() {
		if (super.validatePage()) {
			String extension = new Path(getFileName()).getFileExtension();
			if (extension == null || !FILE_EXTENSIONS.contains(extension)) {
				String key = FILE_EXTENSIONS.size() > 1 ? "_WARN_FilenameExtensions" : "_WARN_FilenameExtension";
				setErrorMessage(DlimEditorPlugin.INSTANCE.getString(key, new Object [] { FORMATTED_FILE_EXTENSIONS }));
				return false;
			}
			return true;
		}
		return false;
	}
	
	public IFile getModelFile() {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
	}

}
