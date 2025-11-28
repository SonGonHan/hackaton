package com.hackaton.task.trip.application.usecase;

import com.hackaton.task.trip.application.in.AttractionUseCase;
import com.hackaton.task.trip.application.in.command.ParseObjectsCommand;
import com.hackaton.task.trip.application.out.AttractionRepository;
import com.hackaton.task.trip.domain.Attraction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParseObjectsService implements AttractionUseCase {

	private final AttractionRepository attractionRepository;

	@Override
	@Transactional
	public Boolean parseObjectsFromXml(ParseObjectsCommand command) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(command.xmlPath()));

		NodeList nodes = doc.getElementsByTagName("node");

		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			NodeList tags = node.getElementsByTagName("tag");

			String name = null;
			boolean isNeed = false;

			for (int j = 0; j < tags.getLength(); j++) {
				Element tag = (Element) tags.item(j);
				String k = tag.getAttribute("k");
				String v = tag.getAttribute("v");

				if ("name".equals(k)) name = v;
				if ("tourism".equals(k) || "historic".equals(k)) isNeed = true;
			}

			if (name != null && isNeed) {
				Attraction attr = Attraction.builder()
											.id(Long.valueOf(node.getAttribute("id")))
											.name(name)
											.latitude(Double.parseDouble(node.getAttribute("lat")))
											.longitude(Double.parseDouble(node.getAttribute("lon")))
											.build();

				attractionRepository.save(attr);
			}
		}

		return true;
	}
}
