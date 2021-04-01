package com.rokong.xml.parse;

import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * ORACLE PIVOT XML의 결과로 조회되는 Document를
 * Parsing 하기 위한 Handler이다.
 *
 * @author rokong
 * @since 2021.04.01
 */
public class PivotHandler extends DefaultHandler {

    // Parse된 Pivot 행
    private Map<String, Object> pivotRow = new HashMap<>();
    // 문자열을 읽을 때 사용될 stringBuffer
    private StringBuffer sbuf = new StringBuffer();

    public Map<String, Object> getPivotRow(){
        return this.pivotRow;
    }
}
