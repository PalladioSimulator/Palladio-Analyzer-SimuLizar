package org.palladiosimulator.simulizar.measuringpoint.create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
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
import org.xml.sax.SAXException;

public class StereotypeApplicationListener implements IResourceChangeListener {

	public static String CREATE_MEASURING_POINT_FILE_JOB_NAME = "Add a measuring point";
	public static String APPLIED_TO_ELEMENT_TAG_NAME = "appliedTo";
	public static String ELEMENT_ID_PREFIX = "platform:/resource";
	public static String HREF_ATTRIBUTE_NAME = "href";
	public static String MEASURING_POINT_FILE_EXTENSION = ".measuringpoint";
	public static String PROFILE_APPLICATION_FILE_EXTENSION = ".pa.xmi";
	public static String REPOSITORY_FILE_EXTENSION = ".repository#";
	public static String STEREOTYPE_APPLICATION_ELEMENT_TAG_NAME = "stereotypeApplications";
	public static String UTF8_ENCODING = "UTF-8";
	public static String DELETE_RESOURCE_JOB_NAME = "Delete resource job";
	public static String XSI_TYPE_ATTRIBUTE_NAME = "xsi:type";
	public static String XSI_TYPE_ATTRIBUTE_VALUE = "myprofile:MeasuringPoint";
	
	/**
	 * Responding to the change of a resource. In this case we find all the profile application
	 * files concerning measuring point stereotypes. We create a measuring point
	 * per measuring point stereotype application. After those measuring points are created,
	 * the respective profile application files are deleted.
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Map<String, String> ids = getElementIdsForMeasuringPoints(event.getDelta());
		for (final Map.Entry<String, String> entry : ids.entrySet()) {
			WorkspaceJob job = new WorkspaceJob(CREATE_MEASURING_POINT_FILE_JOB_NAME) {
				@Override
				public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
					createAMeasuringPoint(entry.getKey(), entry.getValue());
					return Status.OK_STATUS;
				}
			};
			job.schedule();
		}
	}
	
	/**
	 * Deleting the resource from the workspace.
	 * @param resource resource to be deleted.
	 */
	public void deleteResource(final IResource resource){
		WorkspaceJob deleteJob = new WorkspaceJob(DELETE_RESOURCE_JOB_NAME) {
			
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
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
	 * @return map with file name (key) and element ID (value) for the measuring
	 *         points to be created
	 */
	private Map<String, String> getElementIdsForMeasuringPoints(IResourceDelta delta) {
		List<String> elements = getElementsForMeasuringPoints(delta);
		Map<String, String> res = new HashMap<String, String>();
		for (String s : elements) {
			String fileName = s.replace(ELEMENT_ID_PREFIX, ""); //$NON-NLS-2$
			fileName = fileName.replace(REPOSITORY_FILE_EXTENSION, ""); //$NON-NLS-2$
			fileName = fileName + MEASURING_POINT_FILE_EXTENSION;
			res.put(fileName, s);
		}
		return res;
	}

	/**
	 * Takes those changes in the resource tree (IResourceDelta) concerning
	 * stereotype application and extracts IDs of elements to which the
	 * stereotype has been applied.
	 * 
	 * @param delta
	 *            the root of the resource tree which represents the changes
	 * @return list of IDs of elements to which stereotypes are applied to.
	 */
	private List<String> getElementsForMeasuringPoints(IResourceDelta delta) {
		// search delta's children in case the delta is folder
		if (delta.getAffectedChildren() != null && delta.getAffectedChildren().length > 0) {
			List<String> res = new ArrayList<String>();
			for (IResourceDelta d : delta.getAffectedChildren()) {
				res.addAll(getElementsForMeasuringPoints(d));
			}
			return res;
		}

		// delta for "profile application file" change is handled right away
		if (delta.getKind() == IResourceDelta.CHANGED) {
			if (delta.getFullPath().toString().endsWith(PROFILE_APPLICATION_FILE_EXTENSION)) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setIgnoringElementContentWhitespace(true);
				try {
					DocumentBuilder builder = factory.newDocumentBuilder();
					// check for the delta's resource or location
					if (delta.getResource() == null && delta.getResource().getLocation() == null) {
						return new ArrayList<String>();
					} else {
						// creating a DOM for the representative o
						Document doc = builder.parse(delta.getResource().getLocation().toFile());
						Element docElement = doc.getDocumentElement();
						List<String> res = new ArrayList<String>();
						for (int i = 0; i < docElement.getChildNodes().getLength(); i++) {
							Node node = docElement.getChildNodes().item(i);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element stereotypeApplications = (Element) node;
								if (stereotypeApplications.getTagName().equals(STEREOTYPE_APPLICATION_ELEMENT_TAG_NAME)) {
									String xsiType = stereotypeApplications.getAttribute(XSI_TYPE_ATTRIBUTE_NAME);
									// it has to be a measuring point stereotype application not an arbitrary one
									if(xsiType == null || !xsiType.equals(XSI_TYPE_ATTRIBUTE_VALUE)){
										continue;
									}
									for (int j = 0; j < stereotypeApplications.getChildNodes().getLength(); j++) {
										node = stereotypeApplications.getChildNodes().item(j);
										if (node.getNodeType() == Node.ELEMENT_NODE) {
											Element appliedTo = (Element) node;
											if (appliedTo.getTagName().equals(APPLIED_TO_ELEMENT_TAG_NAME)) {
												res.add(appliedTo.getAttribute(HREF_ATTRIBUTE_NAME));
												deleteResource(delta.getResource());
											}
										}
									}
								}
							}
						}
						return res;
					}
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
					return new ArrayList<String>();
				} catch (SAXException e) {
					e.printStackTrace();
					return new ArrayList<String>();
				} catch (IOException e) {
					e.printStackTrace();
					return new ArrayList<String>();
				}
			} else {
				return new ArrayList<String>();
			}
		} else {
			return new ArrayList<String>();
		}
	}

	/**
	 * Creating a ResourceURIMeasuringPoint file with the specified file name and the URI of the
	 * element for which we want to create a measuring point.
	 * @param fileName name of the file to created that represents the measuring point.
	 * @param elementURI URI of the element for which we create a measuring point.
	 */
	public void createAMeasuringPoint(String fileName, String elementURI) {
		try {
			// Create a resource set
			//
			final ResourceSet resourceSet = new ResourceSetImpl();

			// Get the URI of the model file.
			//
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
			rootObject.eSet(resourceURIAttribute, elementURI);

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
