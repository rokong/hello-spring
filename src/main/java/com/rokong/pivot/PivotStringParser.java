package com.rokong.pivot;

import jdk.internal.util.xml.impl.Input;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PivotStringParser extends AbstractPivotParser<String> {

    protected PivotStringParser(String pivotColumn) {
        super(pivotColumn);
    }

    @Override
    protected InputStream convert(String xml) {
        InputStream is = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        return is;
    }
}
