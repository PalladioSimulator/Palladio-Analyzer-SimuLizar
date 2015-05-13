package org.palladiosimulator.simulizar.measuringpoint.create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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

	private final Logger logger = Logger.getLogger(StereotypeApplicationListener.class.getName());
	public static final String RESOURCE_URI_ATTRIBUTE_NAME = "resourceURI";
	private static final String RESOURCE_URI_DELIMITER = "/";
	private static final int PROJECT_NAME_POSITION_INSIDE_RESOURCE_URI = 2;
	private static final String CREATE_MEASURING_POINT_FILE_JOB_NAME = "Add a measuring point";
	public static final String APPLIED_TO_ELEMENT_TAG_NAME = "appliedTo";
	private static final String ELEMENT_ID_PREFIX = "platform:/resource";
	public static final String HREF_ATTRIBUTE_NAME = "href";
	public static final String MEASURING_POINT_FILE_EXTENSION = ".measuringpoint";
	public static final String MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION = ".SimulizarProfile.pa.xmi";
	private static final String REPOSITORY_FILE_EXTENSION = ".repository#";
	private static final String UTF8_ENCODING = "UTF-8";
	private static final String DELETE_RESOURCE_JOB_NAME = "Delete resource job";

	private Set<String> oldResourceURIs;

	public StereotypeApplicationListener(Set<String> currentIds) {
		this.oldResourceURIs = currentIds;
	}

	/**
	 * Responding to the change of a resource. In this case we find all the
	 * profile application files concerning measuring point stereotypes. We
	 * create a measuring point per measuring point stereotype application.
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			IProject[] allWorkspaceProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			Set<String> newResourceURIs;

			newResourceURIs = getMonitoredElementsForMeasuringPoints(allWorkspaceProjects);

			if (!newResourceURIs.isEmpty()) {
				/*
				 * new resourceURIs that are not contained in old resourceURIs
				 * are collected to be created
				 */
				Set<String> resourceURIsToCreate = new HashSet<>();
				for (final String resourceURI : newResourceURIs) {
					if (oldResourceURIs.contains(resourceURI)) {
						continue;
					}
					resourceURIsToCreate.add(resourceURI);
				}
				/*
				 * old resourceURIs that are not contained in new resourceURIs
				 * are collected to be removed
				 */
				List<String> resourceURIsToDelete = new ArrayList<>();
				for (final String resourceURI : oldResourceURIs) {
					if (newResourceURIs.contains(resourceURI)) {
						continue;
					}
					resourceURIsToDelete.add(resourceURI);
				}
				createMeasuringPoints(resourceURIsToCreate);
				deleteMeasuringPoints(resourceURIsToDelete);
				// new IDs become old IDs
				oldResourceURIs = new HashSet<>(newResourceURIs);
			}
			Set<IProject> projectsOfDeletedMPs = getProjectsOfCreatedOrDeletedMeasuringPoints(event.getDelta(),
					IResourceDelta.REMOVED);
			if (projectsOfDeletedMPs != null) {
				for (IProject p : projectsOfDeletedMPs) {
					adaptPaXmisToDeletedMeasuringPoints(p);
				}
			}
			Set<IProject> projectsOfCreatedMPs = getProjectsOfCreatedOrDeletedMeasuringPoints(event.getDelta(),
					IResourceDelta.ADDED);
			if (projectsOfCreatedMPs != null) {
				for (IProject p : projectsOfCreatedMPs) {
					adaptPaXmisToDeletedMeasuringPoints(p);
				}
			}
		} catch (CoreException | ParserConfigurationException | SAXException | IOException | TransformerException e) {
			logger.log(Level.SEVERE, "An error occured while handling measuring point stereotype appliciont!");
			e.printStackTrace();
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

	private Set<IProject> getProjectsOfCreatedOrDeletedMeasuringPoints(IResourceDelta delta, int status) {
		Set<IProject> res = new HashSet<>();
		if (delta != null && delta.getResource() != null) {
			IResource r = delta.getResource();
			String fileName = r.getFullPath().toString();
			if (fileName.endsWith(MEASURING_POINT_FILE_EXTENSION) && delta.getKind() == status) {
				res.add(r.getProject());
				return res;
			}
		}
		// if the delta contains some children they have to be checked too
		if (delta != null) {
			for (IResourceDelta d : delta.getAffectedChildren()) {
				res.addAll(getProjectsOfCreatedOrDeletedMeasuringPoints(d, status));
			}
		}
		return res;
	}

	/**
	 * Deletes all the measuring points whose resourceURI matches an element of
	 * the elementsToDelete.
	 * 
	 * @param measuringPointsToDelete
	 *            list of resourceURIs for which .measuringPoint files have to
	 *            be deleted.
	 * @throws ParserConfigurationException
	 *             exception thrown if the document could not be parsed.
	 * @throws SAXException
	 *             an exception thrown by the SAX.
	 * @throws IOException
	 *             an exception indicating some IO operation on the resource
	 *             could not be performed correctly.
	 * @throws CoreException
	 *             indicates a problem with accessing projects resources.
	 */
	private void deleteMeasuringPoints(List<String> measuringPointsToDelete) throws CoreException,
			ParserConfigurationException, SAXException, IOException {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

		// mapping from project to list of measuring points to be deleted within
		// the project
		Map<IProject, List<String>> projectsWithMPsToDelete = new HashMap<>();
		for (String mp : measuringPointsToDelete) {
			String[] res = mp.split(RESOURCE_URI_DELIMITER);
			IProject project = wsRoot.getProject(res[PROJECT_NAME_POSITION_INSIDE_RESOURCE_URI]);
			List<String> mps = projectsWithMPsToDelete.get(project);
			if (mps == null) {
				mps = new ArrayList<>();
			}
			mps.add(mp);
			projectsWithMPsToDelete.put(project, mps);
		}

		for (Map.Entry<IProject, List<String>> entry : projectsWithMPsToDelete.entrySet()) {
			for (IResource r : entry.getKey().members()) {
				if (r.getFullPath().toString().endsWith(MEASURING_POINT_FILE_EXTENSION)) {
					Document doc = getParsedDocument(r);
					String resourceURI = doc.getDocumentElement().getAttribute(RESOURCE_URI_ATTRIBUTE_NAME);
					if (entry.getValue().contains(resourceURI)) {
						deleteResource(r);
					}
				}
			}
		}
	}

	/**
	 * Creates a measuring point for every resourceURI in the list.
	 * 
	 * @param resourceURIs
	 *            list of resourceURIs.
	 */
	private void createMeasuringPoints(Set<String> resourceURIs) {
		for (final String resourceURI : resourceURIs) {
			WorkspaceJob mpCreateJob = new WorkspaceJob(CREATE_MEASURING_POINT_FILE_JOB_NAME) {
				@Override
				public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
					try {
						createMeasuringPoint(resourceURI);
					} catch (IOException e) {
						logger.log(Level.SEVERE, "Measuring point for \"" + resourceURI + "\" could not be created!");
						e.printStackTrace();
						return Status.CANCEL_STATUS;
					}
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
	 * @throws ParserConfigurationException
	 *             exception thrown if the document could not be parsed.
	 * @throws SAXException
	 *             an exception thrown by the SAX.
	 * @throws IOException
	 *             an exception indicating some IO operation on the resource
	 *             could not be performed correctly.
	 * @throws TransformerException
	 *             indicates that the XML document represented by the resource
	 *             could not be transformed.
	 * @throws CoreException
	 */
	private void adaptPaXmisToDeletedMeasuringPoints(IProject project) throws ParserConfigurationException,
			SAXException, IOException, TransformerException, CoreException {

		List<String> existingMPs = new ArrayList<String>();
		Map<String, IResource> existingSAs = new HashMap<String, IResource>();
		if (project.isOpen()) {
			project.accept(new ResourceURIsFromMPsExtractor(existingMPs, existingSAs), IResource.DEPTH_INFINITE, true);
		}
		for (Map.Entry<String, IResource> entry : existingSAs.entrySet()) {
			if (!existingMPs.contains(entry.getKey())) {
				deleteElementFromPaXmi(entry.getKey(), entry.getValue());
				oldResourceURIs.remove(entry.getKey());
			}
		}
	}

	/**
	 * Parses the document represented by the resource.
	 * 
	 * @param r
	 *            the resource that represents the document which should be
	 *            parsed.
	 * @return parsed document.
	 * @throws ParserConfigurationException
	 *             exception thrown if the document could not be parsed.
	 * @throws SAXException
	 *             an exception thrown by the SAX.
	 * @throws IOException
	 *             an exception indicating some IO operation on the resource
	 *             could not be performed correctly.
	 */
	public static Document getParsedDocument(IResource r) throws ParserConfigurationException, SAXException,
			IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(r.getLocation().toFile());
		return doc;
	}

	/**
	 * Removes the XML element from the resource. Resource should represent
	 * stereotype application MeasuringPoint.
	 * 
	 * @param resourceURI
	 *            resourceURI of the element to be deleted from MeasuringPoint
	 *            stereotype application.
	 * @param resource
	 *            MeasuringPoint stereotype application file from which the XML
	 *            element should be deleted.
	 * @throws ParserConfigurationException
	 *             exception thrown if the document could not be parsed.
	 * @throws SAXException
	 *             an exception thrown by the SAX.
	 * @throws IOException
	 *             an exception indicating some IO operation on the resource
	 *             could not be performed correctly.
	 * @throws TransformerException
	 *             indicates that the XML document represented by the resource
	 *             could not be transformed.
	 */
	private void deleteElementFromPaXmi(String resourceURI, IResource resource) throws ParserConfigurationException,
			SAXException, IOException, TransformerException {
		Document doc = getParsedDocument(resource);
		NodeList appliedToList = doc.getElementsByTagName(APPLIED_TO_ELEMENT_TAG_NAME);
		for (int i = 0; i < appliedToList.getLength(); i++) {
			Element appliedTo = (Element) appliedToList.item(i);
			String href = appliedTo.getAttribute(HREF_ATTRIBUTE_NAME);
			if (href.equals(resourceURI)) {
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

	}

	/**
	 * Deleting the resource from the workspace.
	 * 
	 * @param resource
	 *            resource to be deleted.
	 */
	private void deleteResource(final IResource resource) {
		WorkspaceJob deleteJob = new WorkspaceJob(DELETE_RESOURCE_JOB_NAME) {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) {
				try {
					resource.delete(true, monitor);
				} catch (CoreException ex) {
					logger.log(Level.SEVERE, "The resourece \"" + resource.getName() + "\" could not be deleted!");
					ex.printStackTrace();
					return Status.CANCEL_STATUS;
				}

				return Status.OK_STATUS;
			}
		};
		deleteJob.schedule();
	}

	/**
	 * Given a resourceURI in a form
	 * "platform:/resource/ui/loadbalancer.repository#_7FXSsAEma34" it extracts
	 * the file name for measuring point. In this case it would be
	 * "/ui/loadbalancer.measuringpoint".
	 * 
	 * @param resourceURI
	 *            resourceURI from which measuring point file name should be
	 *            extracted.
	 * @return file name for measuring point.
	 */
	private String getMeasuringPointName(String resourceURI) {
		String fileName = resourceURI.replace(ELEMENT_ID_PREFIX, ""); //$NON-NLS-2$
		fileName = fileName.replace(REPOSITORY_FILE_EXTENSION, ""); //$NON-NLS-2$
		fileName = fileName + MEASURING_POINT_FILE_EXTENSION;
		return fileName;
	}

	/**
	 * Collects resourceURIs of all the elements in the projects to which
	 * MeasuringPoint stereotype was applied. It is done by examining
	 * .SimulizarProfile.pa.xmi files.
	 * 
	 * @param projects
	 *            projects whose .SimulizarProfile.pa.xmi files are examined.
	 * @return
	 * @throws ParserConfigurationException
	 *             exception thrown if the document could not be parsed.
	 * @throws SAXException
	 *             an exception thrown by the SAX.
	 * @throws IOException
	 *             an exception indicating some IO operation on the resource
	 *             could not be performed correctly.
	 * @throws TransformerException
	 *             indicates that the XML document represented by the resource
	 *             could not be transformed.
	 * @throws CoreException
	 *             indicates a problem with accessing projects resources.
	 */
	private Set<String> getMonitoredElementsForMeasuringPoints(IProject[] projects)
			throws ParserConfigurationException, SAXException, IOException, TransformerException, CoreException {
		Map<String, IProject> map = new HashMap<>();
		for (IProject project : projects) {
			if (project.isOpen())
				project.accept(new ResourceURIsFromSAsExtractor(map), IResource.DEPTH_INFINITE, true);
		}
		return map.keySet();
	}

	/**
	 * Creating a ResourceURIMeasuringPoint file with the specified file name
	 * and the resourceURI of the element for which we want to create a
	 * measuring point.
	 * 
	 * @param fileName
	 *            name of the file to created that represents the measuring
	 *            point.
	 * @param resourceURI
	 *            resourceURI of the element for which we create a measuring
	 *            point.
	 * @throws IOException
	 *             indicates that there was a problem with saving the resource.
	 */
	public void createMeasuringPoint(String resourceURI) throws IOException {
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
		final Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_ENCODING, UTF8_ENCODING);
		resource.save(options);
	}

}
