package net.etfbl.ip.projektni.rss;


import java.io.StringWriter;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class RSSFeedWriter {

    private Feed rssfeed;

    public RSSFeedWriter(Feed rssfeed) {
        this.rssfeed = rssfeed;
    }

    public String write() throws Exception {

        // create a XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        // create XMLEventWriter
       /* XMLEventWriter eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(outputFile));*/

        StringWriter sw=new StringWriter();
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(sw);
        

        // create a EventFactory

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");

        // create and write Start Tag

        StartDocument startDocument = eventFactory.createStartDocument();

        eventWriter.add(startDocument);

        // create open tag
        eventWriter.add(end);

        StartElement rssStart = eventFactory.createStartElement("", "", "rss");
        eventWriter.add(rssStart);
        eventWriter.add(eventFactory.createAttribute("version", "2.0"));
        eventWriter.add(eventFactory.createAttribute("xmlns:xcal", "urn:ietf:params:xml:ns:xcal"));
        eventWriter.add(end);

        eventWriter.add(eventFactory.createStartElement("", "", "channel"));
        eventWriter.add(end);

        // Write the different nodes

        createNode(eventWriter, "title", rssfeed.getTitle());

        createNode(eventWriter, "link", rssfeed.getLink());

        createNode(eventWriter, "description", rssfeed.getDescription());

        createNode(eventWriter, "language", rssfeed.getLanguage());

        createNode(eventWriter, "copyright", rssfeed.getCopyright());

        createNode(eventWriter, "pubDate", rssfeed.getPubDate());

        for (FeedMessage entry : rssfeed.getMessages()) {
            eventWriter.add(eventFactory.createStartElement("", "", "item"));
            eventWriter.add(end);
            createNode(eventWriter, "title", entry.getTitle());
            createNode(eventWriter, "description", entry.getDescription());
            createNode(eventWriter, "link", entry.getLink());
            createNode(eventWriter, "category", entry.getCategory());
            createNode(eventWriter, "guid", entry.getGuid());
            createNode(eventWriter, "pubDate", entry.getPubDate());
            createNode(eventWriter, "xcal:dtstart", entry.getStartDate());
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndElement("", "", "item"));
            eventWriter.add(end);

        }

        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndElement("", "", "channel"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndElement("", "", "rss"));

        eventWriter.add(end);

        eventWriter.add(eventFactory.createEndDocument());

        eventWriter.close();
        String s=sw.toString();
        System.out.println(s);
        return s;
        
    }

    private void createNode(XMLEventWriter eventWriter, String name,

    String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}