package com.rokong.pivot.parser;

import com.rokong.pivot.PivotXMLReader;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractPivotParser<E> extends PivotXMLReader implements PivotRowParser {

    private final String pivotColumn;

    protected AbstractPivotParser(String pivotColumn) {
        this.pivotColumn = pivotColumn;
    }

    public List<Map<String, Object>> parsePivotRows(List<Map<String, Object>> rows) {
        List<Map<String, Object>> result = new ArrayList<>();

        for(Map<String, Object> row : rows){
            result.add(parsePivotRow(row));
        }

        return result;
    }

    public Map<String, Object> parsePivotRow(Map<String, Object> row) {
        E document = getPivotXML(row);
        Map<String, Object> pivotRow = parsePivot(document);
        row.putAll(pivotRow);
        return row;
    }

    protected E getPivotXML(Map<String, Object> row){
        if(pivotColumn == null){
            throw new IllegalArgumentException("pivotColumn is not defined");
        }
        return (E) row.get(pivotColumn);
    }

    private Map<String, Object> parsePivot(E xml){
        InputStream inputStream = convert(xml);
        return parsePivotColumn(new InputSource(inputStream));
    }

    protected abstract InputStream convert(E xml);
}
