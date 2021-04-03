package com.rokong.pivot;

import com.rokong.pivot.handler.PivotRowHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;

public class PivotXMLReader {

    protected XMLReader pivotReader;
    protected PivotRowHandler pivotRowHandler;

    public PivotXMLReader(){
        initXMLReader();
    }

    /**
     * XMLReader를 초기화한다.
     */
    private void initXMLReader(){
        try {
            pivotReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            throw new IllegalStateException("XMLReaderFactory can not create XMLReader", e);
        }

        pivotRowHandler = new PivotRowHandler();

        pivotReader.setContentHandler(pivotRowHandler);
        pivotReader.setEntityResolver(pivotRowHandler);
        pivotReader.setErrorHandler(pivotRowHandler);
        pivotReader.setDTDHandler(pivotRowHandler);
    }

    /**
     * PIVOT XML을 Map 형태로 parse한다
     * @param inputSource PIVOT XML
     * @return Map형태의 PIVOT 테이블
     */
    public Map<String, Object> parsePivotColumn(InputSource inputSource){
        Map<String, Object> pivotColumns = null;

        try {
            pivotReader.parse(inputSource);
            pivotColumns = pivotRowHandler.getPivotRow();
        } catch (IOException | SAXException e) {
            throw new IllegalArgumentException("Can not read XML", e);
        }

        return pivotColumns;
    }
}
