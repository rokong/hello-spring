package com.rokong.pivot.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PivotStringParser extends AbstractPivotParser<String> {

    public PivotStringParser(String pivotColumn) {
        super(pivotColumn);
    }

    @Override
    protected InputStream convert(String xml) {
        InputStream is = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        return is;
    }
}
