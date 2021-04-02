package com.rokong.pivot.parser;

import java.util.List;
import java.util.Map;

public interface PivotRowParser {
    public Map<String, Object> parsePivotRow(Map<String, Object> row);
    public List<Map<String, Object>> parsePivotRows(List<Map<String, Object>> rows);
}
