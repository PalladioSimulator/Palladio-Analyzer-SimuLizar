package org.palladiosimulator.simulizar.measuringpoint.create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StereotypeApplicationListener implements IResourceChangeListener {

	private static final String RESOURCE_URI_ATTRIBUTE_NAME = "resourceURI";
	private static final String RESOURCE_URI_DELIMITER = "/";
	private static final int PROJECT_NAME_POSITION_INSIDE_RESOURCE_URI = 2;
	private static final String CREATE_MEASURING_POINT_FILE_JOB_NAME = "Add a measuring point";
	private static final String APPLIED_TO_ELEMENT_TAG_NAME = "appliedTo";
	private static final String ELEMENT_ID_PREFIX = "platform:/resource";
	private static final String HREF_ATTRIBUTE_NAME = "href";
	private static final String MEASURING_POINT_FILE_EXTENSION = ".measuringpoint";
	private static final String MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION = ".SimulizarMeasuringPointProfile.pa.xmi";
	private static final String REPOSITORY_FILE_EXTENSION = ".repository#";
	private static final String UTF8_ENCODING = "UTF-8";
	private static final String DELETE_RESOURCE_JOB_NAME = "Delete resource job";

	private List<String> elementIDs;

	public StereotypeApplicationListener(List<String> elementIDs) {
		this.elementIDs = elementIDs;
	}

	/**
	 * Responding to the change of a resource. In this case we find all the
	 * profile application files concerning measuring point stereotypes. We
	 * create a measuring point per measuring point stereotype application.
	 * After those measuring points are created, the respective profile
	 * application files are deleted.
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Map<String, IProject> ids = getElementsForMeasuringPoints(event.getDelta());
		if (!ids.isEmpty()) {
			createMeasuringPoints(ids.keySet());
			List<String> resourceURIsToDelete = new ArrayList<>();
			for (final String element : elementIDs) {
				if (ids.containsKey(element)) {
					continue;
				}
				resourceURIsToDelete.add(element);
			}
			deleteMeasuringPoints(resourceURIsToDelete);
			elementIDs = new ArrayList<String>(ids.keySet());
		}
		Set<IProject> projects = getProjectsOfDeletedMeasuringPoints(event.getDelta());
		if (!projects.isEmpty()) {
			for (IProject p : projects) {
				adaptPaXmisToDeletedMeasuringPoints(p);
			}
		}
	}

	/**
	 * If there are some .measuringpoint files within the delta that are
	 * removed, the project to which they belong is returned.
	 * 
	 * @param delta
	 *            IResourceDelta that represents the change.
	 * @return set of projects in which some .measuringpoint files have beed
	 *         deleted.
	 */

	private Set<IProject> getProjectsOfDeletedMeasuringPoints(IResourceDelta delta) {
		Set<IProject> res = new HashSet<>();
		if (delta.getResource() != null) {
			IResource r = delta.getResource();
			String fileName = r.getFullPath().toString();
			if (fileName.endsWith(MEASURING_POINT_FILE_EXTENSION) && delta.getKind() == IResourceDelta.REMOVED) {
				res.add(r.getProject());
				return res;
			}
		}
		// if the delta contains some children they have to be checked as
		// well
		for (IResourceDelta d : delta.getAffectedChildren()) {
			res.addAll(getProjectsOfDeletedMeasuringPoints(d));
		}
		return res;
	}

	/**
	 * Deletes all the measuring points whose resourceURI matches an element of
	 * the elementsToDelete.
	 * 
	 * @param elementsToDelete
	 *            list of resourceURIs for which .measuringPoint files have to
	 *            be deleted.
	 */
	private void deleteMeasuringPoints(List<String> elementsToDelete) {
		try {
			IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
			for (String element : elementsToDelete) {
				String[] res = element.split(RESOURCE_URI_DELIMITER);
				IProject project = wsRoot.getProject(res[PROJECT_NAME_POSITION_INSIDE_RESOURCE_URI]);

				for (IResource r : project.members()) {
					if (r.getFullPath().toString().endsWith(MEASURING_POINT_FILE_EXTENSION)) {
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						Document doc = builder.parse(r.getLocation().toFile());
						String resourceURI = doc.getDocumentElement().getAttribute(RESOURCE_URI_ATTRIBUTE_NAME);
						if (elementsToDelete.contains(resourceURI)) {
							deleteResource(r);
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a measuring point for every resourceURI in the list.
	 * 
	 * @param resourceURIs
	 *            list of resourceURIs.
	 */
	private void createMeasuringPoints(Set<String> resourceURIs) {
		for (String resourceURI : resourceURIs) {
			if (elementIDs.contains(resourceURI)) {
				continue;
			}
			WorkspaceJob mpCreateJob = new WorkspaceJob(CREATE_MEASURING_POINT_FILE_JOB_NAME) {
				@Override
				public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
					createAMeasuringPoint(resourceURI);
					return Status.OK_STATUS;
				}
			};
			mpCreateJob.schedule();
		}
	}

	/**
	 * If there are some .measuringpoint files that have been deleted, the
	 * respective .pa.xmi file will be updated accordingly, i.e. respective
	 * stereotypeApplication elements will be removed from the file.
	 * 
	 * @param delta
	 *            IResourceDelta that represents the change in the project.
	 */
	private void adaptPaXmisToDeletedMeasuringPoints(IProject project) {
		try {
			List<String> existingMeasuringPointElements = new ArrayList<>();
			Map<String, IResource> existingProfileApplications = new HashMap<>();
			for (IResource r : project.members()) {
				if (r.getFullPath().toString().endsWith(MEASURING_POINT_FILE_EXTENSION)) {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(r.getLocation().toFile());
					String resourceURI = doc.getDocumentElement().getAttribute(RESOURCE_URI_ATTRIBUTE_NAME);
					existingMeasuringPointElements.add(resourceURI);
				} else if (r.getFullPath().toString().endsWith(MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION)) {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(r.getLocation().toFile());
					NodeList appliedToList = doc.getElementsByTagName(APPLIED_TO_ELEMENT_TAG_NAME);
					for (int i = 0; i < appliedToList.getLength(); i++) {
						Element appliedTo = (Element) appliedToList.item(i);
						String href = appliedTo.getAttribute(HREF_ATTRIBUTE_NAME);
						existingProfileApplications.put(href, r);
					}
				}
			}
			for (Map.Entry<String, IResource> entry : existingProfileApplications.entrySet()) {
				if (!existingMeasuringPointElements.contains(entry.getKey())) {
					deleteElementFromPaXmi(entry.getKey(), entry.getValue());
					elementIDs.remove(entry.getKey());
				}
			}
		} catch (CoreException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteElementFromPaXmi(String elementURI, IResource resource) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(resource.getLocation().toFile());
			NodeList appliedToList = doc.getElementsByTagName(APPLIED_TO_ELEMENT_TAG_NAME);
			for (int i = 0; i < appliedToList.getLength(); i++) {
				Element appliedTo = (Element) appliedToList.item(i);
				String href = appliedTo.getAttribute(HREF_ATTRIBUTE_NAME);
				if (href.equals(elementURI)) {
					Node stereotypeApplication = appliedTo.getParentNode();
					Node rootNode = stereotypeApplication.getParentNode();
					rootNode.removeChild(stereotypeApplication);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(resource.getLocation().toFile());
					transformer.transform(source, result);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deleting the resource from the workspace.
	 * 
	 * @param resource
	 *            resource to be deleted.
	 */
	public void deleteResource(IResource resource) {
		WorkspaceJob deleteJob = new WorkspaceJob(DELETE_RESOURCE_JOB_NAME) {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				// IResource resource =
				// ResourcesPlugin.getWorkspace().getRoot().findMember(resourcePath);
				resource.delete(true, monitor);
				return Status.OK_STATUS;
			}
		};
		deleteJob.schedule();
	}

	/**
	 * Given the changes in the resource tree (IResourceDelta) it finds those
	 * that are concerning stereotype applications. From these changes it
	 * extracts IDs of components to which stereotypes are applied and returns
	 * map with file names element IDs for the measuring points that should be
	 * created based on the respective stereotype applications.
	 * 
	 * @param delta
	 *            the root of the resource tree which represents the changes
	 * @return map with element ID (key) and file name (value) for the measuring
	 *         points to be created
	 */
	private String getMeasuringPointName(String resourceURI) {
		String fileName = resourceURI.replace(ELEMENT_ID_PREFIX, ""); //$NON-NLS-2$
		fileName = fileName.replace(REPOSITORY_FILE_EXTENSION, ""); //$NON-NLS-2$
		fileName = fileName + MEASURING_POINT_FILE_EXTENSION;
		return fileName;
	}

	/**
	 * Takes those changes in the resource tree (IResourceDelta) concerning
	 * .pa.xmi files and resourceURIs of elements to which the stereotype has
	 * been applied and projects in which the respective .pa.xmi file resides.
	 * 
	 * @param delta
	 *            the root of the resource tree which represents the changes
	 * @return map of resourceIDs of elements stereotypes are applied to and
	 *         projects where the respective .pa.xmi files reside.
	 */
	private Map<String, IProject> getElementsForMeasuringPoints(IResourceDelta delta) {
		// search delta's children in case the delta is folder
		if (delta.getAffectedChildren() != null && delta.getAffectedChildren().length > 0) {
			Map<String, IProject> res = new HashMap<>();
			for (IResourceDelta d : delta.getAffectedChildren()) {
				res.putAll(getElementsForMeasuringPoints(d));
			}
			return res;
		}

		// delta for "profile application file" change is handled right away
		if (delta.getKind() == IResourceDelta.CHANGED
				&& delta.getFullPath().toString().endsWith(MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION)) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				// check for the delta's resource or location
				if (delta.getResource() == null && delta.getResource().getLocation() == null) {
					return new HashMap<>();
				} else {
					Map<String, IProject> res = new HashMap<>();
					// creating a DOM for the representative of the change
					Document doc = builder.parse(delta.getResource().getLocation().toFile());
					NodeList appliedToList = doc.getElementsByTagName(APPLIED_TO_ELEMENT_TAG_NAME);
					for (int i = 0; i < appliedToList.getLength(); i++) {
						Element appliedTo = (Element) appliedToList.item(i);
						String href = appliedTo.getAttribute(HREF_ATTRIBUTE_NAME);
						IProject duplicate = res.put(href, delta.getResource().getProject());
						if(duplicate != null){
							Node stereotypeApplication = appliedTo.getParentNode();
							Node rootNode = stereotypeApplication.getParentNode();
							rootNode.removeChild(stereotypeApplication);

							TransformerFactory transformerFactory = TransformerFactory.newInstance();
							Transformer transformer = transformerFactory.newTransformer();
							DOMSource source = new DOMSource(doc);
							StreamResult result = new StreamResult(delta.getResource().getLocation().toFile());
							transformer.transform(source, result);
						}
					}
					return res;
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				return new HashMap<>();
			} catch (SAXException e) {
				e.printStackTrace();
				return new HashMap<>();
			} catch (IOException e) {
				e.printStackTrace();
				return new HashMap<>();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
				return new HashMap<>();
			} catch (TransformerException e) {
				e.printStackTrace();
				return new HashMap<>();
			}

		} else {
			return new HashMap<>();
		}
	}

	// private void moveProfileApplicationFile(IResource resource) {
	// IProject project = resource.getProject();
	// IPath paFileName = resource.getProjectRelativePath();
	//
	// IFile paFileOld = project.getFile(paFileName);
	// IFolder paFolderNew = project.getFolder("profile_applications");
	//
	// WorkspaceJob moveFile = new WorkspaceJob("Move profile application file")
	// {
	//
	// @Override
	// public IStatus runInWorkspace(IProgressMonitor monitor) throws
	// CoreException {
	// if (!paFolderNew.exists()) {
	// paFolderNew.create(true, true, null);
	// }
	// IPath paFilePathNew = paFolderNew.getFullPath().append(paFileName);
	// paFileOld.move(paFilePathNew, false, null);
	// return Status.OK_STATUS;
	// }
	// };
	// moveFile.schedule();
	// }

	/**
	 * Creating a ResourceURIMeasuringPoint file with the specified file name
	 * and the URI of the element for which we want to create a measuring point.
	 * 
	 * @param fileName
	 *            name of the file to created that represents the measuring
	 *            point.
	 * @param resourceURI
	 *            URI of the element for which we create a measuring point.
	 */
	public void createAMeasuringPoint(String resourceURI) {
		try {
			// Create a resource set
			//
			final ResourceSet resourceSet = new ResourceSetImpl();

			// Get the URI of the model file.
			//
			String fileName = getMeasuringPointName(resourceURI);
			final URI fileURI = URI.createPlatformResourceURI(fileName, true);

			// Create a resource for this file.
			//
			final Resource resource = resourceSet.createResource(fileURI);

			// Add the initial model object to the contents.
			//
			MeasuringpointPackage measuringpointPackage = MeasuringpointPackage.eINSTANCE;
			MeasuringpointFactory measuringpointFactory = measuringpointPackage.getMeasuringpointFactory();

			EClass measuringPoint = measuringpointPackage.getResourceURIMeasuringPoint();

			final EObject rootObject = measuringpointFactory.create(measuringPoint);
			EAttribute resourceURIAttribute = measuringpointPackage.getResourceURIMeasuringPoint_ResourceURI();
			rootObject.eSet(resourceURIAttribute, resourceURI);

			if (rootObject != null) {
				resource.getContents().add(rootObject);
			}

			// Save the contents of the resource to the file system.
			//
			final Map<Object, Object> options = new HashMap<Object, Object>();
			options.put(XMLResource.OPTION_ENCODING, UTF8_ENCODING);
			resource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
