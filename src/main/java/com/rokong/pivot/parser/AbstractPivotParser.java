package com.rokong.pivot.parser;

import com.rokong.pivot.PivotXMLReader;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PIVOT XML이 담긴 행(또는 행 목록)을 일반적인 형태(Map)으로 변환한다.
 * {{@link #convert(Object)}} 메서드를 @Override하여 활용 가능하다.
 *
 * @param <E> PIVOT XML의 클래스
 */
public abstract class AbstractPivotParser<E> implements PivotRowParser {

    private final String pivotColumn;
    private PivotXMLReader pivotXMLReader;

    /**
     * AbstractPivotParser의 생성자.
     *
     * {{@link #convert(Object)}} 메서드를 @Override 하는 경우에는
     * pivotColumn에 null이 들어갈 수 있다.
     *
     * @param pivotColumn 단일 행(Map)에서 PIVOT XML의 key
     */
    public AbstractPivotParser(String pivotColumn) {
        this.pivotColumn = pivotColumn;
        this.pivotXMLReader = new PivotXMLReader();
    }

    public List<Map<String, Object>> parsePivotRow(List<Map<String, Object>> rows) {
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

    /**
     * 단일 행에서 PIVOT XML에 해당하는 객체를 가져온다.
     * XML을 가져올 때 전처리가 필요한 경우가 있을 수 있으므로
     * 필요 시 현재 메서드를 @Override하면 된다
     *
     * @param row PIVOT XML이 있는 행
     * @return PIVOT XML
     */
    protected E getPivotXML(Map<String, Object> row){
        if(pivotColumn == null){
            throw new IllegalArgumentException("pivotColumn is not defined");
        }
        return (E) row.get(pivotColumn);
    }

    /**
     * PIVOT XML에서 PIVOT column들을 가져온다.
     *
     * @param xml PIVOT XML
     * @return Map 형태의 PIVOT column
     */
    private Map<String, Object> parsePivot(E xml){
        InputStream inputStream = convert(xml);
        return pivotXMLReader.parsePivotColumn(new InputSource(inputStream));
    }

    /**
     * PIVOT XML의 클래스에 따라 InputStream으로 바꾸는 메서드이다.
     *
     * @param xml PIVOT XML
     * @return InputStream 타입의 PIVOT XML
     */
    protected abstract InputStream convert(E xml);
}
