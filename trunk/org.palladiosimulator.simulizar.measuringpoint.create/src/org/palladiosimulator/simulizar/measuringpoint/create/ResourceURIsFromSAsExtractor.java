package org.palladiosimulator.simulizar.measuringpoint.create;

import java.io.IOException;
import java.util.Map;

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
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResourceURIsFromSAsExtractor implements IResourceVisitor {

	private Map<String, IProject> res;

	public ResourceURIsFromSAsExtractor(Map<String, IProject> res) {
		this.res = res;
	}

	@Override
	public boolean visit(IResource resource) throws CoreException {
		if (resource.getFullPath().toString()
				.endsWith(StereotypeApplicationListener.MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION)) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setIgnoringElementContentWhitespace(true);

				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(resource.getLocation().toFile());
				NodeList appliedToList = doc
						.getElementsByTagName(StereotypeApplicationListener.APPLIED_TO_ELEMENT_TAG_NAME);
				for (int i = 0; i < appliedToList.getLength(); i++) {
					Element appliedTo = (Element) appliedToList.item(i);
					String href = appliedTo.getAttribute(StereotypeApplicationListener.HREF_ATTRIBUTE_NAME);
					// check if encountered the element that already
					// exists
					IProject duplicate = res.put(href, resource.getProject());
					// if yes, delete the element from the stereotype
					// application file
					if (duplicate != null) {
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
			} catch (ParserConfigurationException pcex) {
				throw new RuntimeException("A problem occured while configuring parser");
			} catch (SAXException e) {
				throw new RuntimeException("An exception thrown by SAX parser");
			} catch (IOException e) {
				throw new RuntimeException("A problem occured while performing IO");
			} catch (TransformerConfigurationException e) {
				throw new RuntimeException(
						"A problem while configuring the transformator of the stereotype application file");
			} catch (TransformerException e) {
				throw new RuntimeException("A problem while transforming the stereotype application file");
			}
		}
		return true;
	}

}
