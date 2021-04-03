package com.rokong.pivot.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class PivotRowHandler extends DefaultHandler {

    // pivot 결과 행을 담는 속성
    private Map<String, Object> pivotRow;

    // element에 있는 문자열을 읽는다
    private StringBuilder content = new StringBuilder();

    // column 데이터 이름 앞에 붙일 접두어 (column 이름)
    private String columnPrefix = null;

    // column 데이터를 저장하기 위한 key 이름이다
    private String dataKey;

    /**
     * XML을 읽기 시작할 때 실행된다.
     */
    @Override
    public void startDocument(){
        pivotRow = new HashMap<>();
    }

    /**
     * element 안에 있는 문자열을 읽는다.
     */
    @Override
    public void characters(char[] ch, int start, int length){
        content.setLength(0);   //StringBuffer 비우기
        content.append(ch, start, length);
    }

    /**
     * element가 시작될 때 실행된다.
     */
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

    /**
     * element 안에 있는 문자열을 trim하여 가져온다.
     * @return trim된 content
     */
    private String getContent(){
        return content.toString().trim();
    }

    /**
     * element가 끝날 때 실행된다.
     */
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
