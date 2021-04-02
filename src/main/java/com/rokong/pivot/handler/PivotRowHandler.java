package com.rokong.pivot.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class PivotRowHandler extends DefaultHandler {
    private Map<String, Object> pivotRow;
    private StringBuilder content = new StringBuilder();
    private String columnPrefix = null;
    private String dataKey;

    @Override
    public void startDocument(){
        pivotRow = new HashMap<>();
    }

    @Override
    public void characters(char[] ch, int start, int length){
        content.setLength(0);
        content.append(ch, start, length);
    }

    @Override
    public void startElement(String url, String localName, String qName, Attributes attributes){
        if("item".equals(qName)){
            //start bundle of column group
            columnPrefix = null;
        }else if("column".equals(qName) && columnPrefix != null){
            //belongs to column group
            dataKey = columnPrefix + attributes.getValue("name");
        }
    }

    private String getContent(){
        return content.toString().trim();
    }

    @Override
    public void endElement(String url, String localName, String qName){
        if("column".equals(qName)){
            if(columnPrefix == null){
                //this element becomes header of column group
                columnPrefix = getContent() + "_";
            }else{
                //the others belong to header
                pivotRow.put(dataKey, getContent());
            }
        }
    }

    public Map<String, Object> getPivotRow(){
        return pivotRow;
    }
}
