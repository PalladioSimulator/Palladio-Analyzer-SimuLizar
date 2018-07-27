package org.palladiosimulator.simulizar.measuringpoint.create;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements IStartup {

	private static final String HREF_ATTRIBUTE_NAME = "href";
	private static final String APPLIED_TO_ELEMENT_NAME = "appliedTo";
	private static final String MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION = ".SimulizarProfile.pa.xmi";
	private static final String RESOURCE_URI_ATTRIBUTE_NAME = "resourceURI";
	private static final String MEASURING_POINT_FILE_EXTENSION = ".measuringpoint";
	// The plug-in ID
	public static final String PLUGIN_ID = "org.palladiosimulator.simulizar.example"; //$NON-NLS-1$
	// The shared instance
	private static Activator plugin;

    /**
     * The constructor
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

	@Override
	public void earlyStartup() {
		Set<String> elements = getElementsWithStereotypes();
		filterOutElementsWithNotMeasuringPointFiles(elements);
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		ws.addResourceChangeListener(new StereotypeApplicationListener(elements));
    }

	/**
	 * Filters out the elements for which there are no .measuringpoint files
	 * with the same resourceURI.
	 * 
	 * @param elements
	 *            resourceURIs of elements to which MeasuringPoint stereotype
	 *            was applied.
	 */
	private void filterOutElementsWithNotMeasuringPointFiles(Set<String> elements) {
		try {
			IWorkspace ws = ResourcesPlugin.getWorkspace();
			IProject[] projects = ws.getRoot().getProjects();
			for (IProject project : projects) {
				if (project.isOpen()) {
				IResource[] resources = project.members();
				for (IResource r : resources) {
					if (r.getFullPath().lastSegment() != null
							&& r.getFullPath().lastSegment().endsWith(MEASURING_POINT_FILE_EXTENSION)) {
						String resourceURI = getResourceUriOfTheElement(r);
						if (elements.contains(resourceURI)) {
							elements.add(resourceURI);
						}
					}
				}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

    /**
     * Gets the resourceURI of the measuring point.
     * 
     * @param r
     *            the resource which represents .measuringpoint file.
     * @return resourceURI of the measuring point.
     */
    private String getResourceUriOfTheElement(IResource r) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(r.getLocation().toFile());
            String resourceURI = doc.getDocumentElement().getAttribute(RESOURCE_URI_ATTRIBUTE_NAME);
            return resourceURI;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * Gets resourceURIs of all elements to which MeasuringPoint stereotype was
	 * applied. It collects those elements from
	 * .SimulizarMeasuringPointProfile.pa.xmi from all projects in the
	 * workspace.
	 * 
	 * @return list of collected resourceURIs.
	 */
	private Set<String> getElementsWithStereotypes() {
		try {
			Set<String> res = new HashSet<>();
			IWorkspace ws = ResourcesPlugin.getWorkspace();
			IProject[] projects = ws.getRoot().getProjects();
			for (IProject project : projects) {
				if (project.isOpen()) {
				IResource[] resources = project.members();
				for (IResource r : resources) {
					if (r.getFullPath().lastSegment() != null
							&& r.getFullPath().lastSegment()
									.endsWith(MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION)) {
						res.addAll(collectResourceUris(r));
					}
				}
			}
			}
			return res;
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Collects all the resourceURIs which represent application of
	 * MeasuringPoint stereotype.
	 * 
	 * @param r
	 *            resource which represents
	 *            .SimulizarMeasuringPointProfile.pa.xmi file.
	 * @return all the resourceURIs from .SimulizarMeasuringPointProfile.pa.xmi
	 *         file.
	 */
	private Set<String> collectResourceUris(IResource r) {
		try {
			Set<String> res = new HashSet<>();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(r.getLocation().toFile());
			NodeList appliedToList = doc.getElementsByTagName(APPLIED_TO_ELEMENT_NAME);
			for (int i = 0; i < appliedToList.getLength(); i++) {
				Element appliedTo = (Element) appliedToList.item(i);
				String href = appliedTo.getAttribute(HREF_ATTRIBUTE_NAME);
				res.add(href);
			}
			return res;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}