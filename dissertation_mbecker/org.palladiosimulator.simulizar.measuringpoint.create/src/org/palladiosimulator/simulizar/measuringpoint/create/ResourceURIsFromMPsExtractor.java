package org.palladiosimulator.simulizar.measuringpoint.create;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResourceURIsFromMPsExtractor implements IResourceVisitor {

	private List<String> existingMPs;
	private Map<String, IResource> existingSAs;

	public ResourceURIsFromMPsExtractor(List<String> existingMPs, Map<String, IResource> existingSAs) {
		super();
		this.existingMPs = existingMPs;
		this.existingSAs = existingSAs;
	}

	@Override
	public boolean visit(IResource resource) throws CoreException {
		try {
			if (resource.getFullPath().toString()
					.endsWith(StereotypeApplicationListener.MEASURING_POINT_FILE_EXTENSION)) {
				Document doc = StereotypeApplicationListener.getParsedDocument(resource);
				String resourceURI = doc.getDocumentElement().getAttribute(
						StereotypeApplicationListener.RESOURCE_URI_ATTRIBUTE_NAME);
				existingMPs.add(resourceURI);
			} else if (resource.getFullPath().toString()
					.endsWith(StereotypeApplicationListener.MEASURING_POINT_STEREOTYPE_APPLICATION_FILE_EXTENSION)) {
				Document doc = StereotypeApplicationListener.getParsedDocument(resource);
				NodeList appliedToList = doc
						.getElementsByTagName(StereotypeApplicationListener.APPLIED_TO_ELEMENT_TAG_NAME);
				for (int i = 0; i < appliedToList.getLength(); i++) {
					Element appliedTo = (Element) appliedToList.item(i);
					String href = appliedTo.getAttribute(StereotypeApplicationListener.HREF_ATTRIBUTE_NAME);
					existingSAs.put(href, resource);
				}
			}
		} catch (IOException ioex) {
			throw new RuntimeException("A problem with IO occured.");
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("A problem with configuring the parser occured.");
		} catch (SAXException e) {
			throw new RuntimeException("A problem with SAX parser occured.");
		}
		return true;
	}
}
