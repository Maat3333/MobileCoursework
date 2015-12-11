package com.example.matthew.mobilecoursework;

/**
 * Created by matthew on 09/12/2015.
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class RssParseHandler extends DefaultHandler {

    private List<RssItem> rssItems;

    // Used to reference item while parsing
    private RssItem currentItem;

    // Parsing title indicator
    private boolean bTitle;
    // Parsing link indicator
    //ToDo: implement list listner?
    private boolean bLink;// currently not using the link may come back and implement a listner if i have time

    public RssParseHandler() {
        rssItems = new ArrayList<RssItem>();
    }

    public List<RssItem> getItems() {
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RssItem();
        } else if ("title".equals(qName)) {
            bTitle = true;
        } else if ("link".equals(qName)) {
            bLink = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            bTitle = false;
        } else if ("link".equals(qName)) {
            bLink = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (bLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                bLink = false;
            }
        }
    }

}
