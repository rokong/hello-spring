package com.rokong.pivot.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadDocHandler extends DefaultHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private StringBuffer sbuf = new StringBuffer();

    @Override
    public void characters(char[] ch, int start, int length){
        sbuf.setLength(0);
        sbuf.append(ch, start, length);
    }

    @Override
    public void startDocument() {
        logger.debug("startDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        logger.debug("startElement qName : {}", qName);
        for(int i=0; i< attributes.getLength(); i++){
            logger.debug("attributes[{}] : {}", attributes.getQName(i), attributes.getValue(i));
        }
    }

    private String getContent(){
        return sbuf.toString().trim();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        logger.debug("endElement : {}", qName);
        String content = getContent();
        if(StringUtils.isNotBlank(content)){
            logger.debug("ch : {}", content);
        }
    }
}
