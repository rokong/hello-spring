package com.rokong.pivot.parser;

import java.util.List;
import java.util.Map;

public interface PivotRowParser {

    /**
     * 단일 행의 PIVOT을 다룬다.
     *
     * @param row PIVOT XML이 있는 행
     * @return 일반적인 PIVOT 형태의 행
     */
    public Map<String, Object> parsePivotRow(Map<String, Object> row);

    /**
     * 여러 행의 PIVOT을 다룬다.
     *
     * @param rows PIVOT XML이 있는 행 목록
     * @return 일반적인 PIVOT 형태의 행 목록
     */
    public List<Map<String, Object>> parsePivotRow(List<Map<String, Object>> rows);
}
