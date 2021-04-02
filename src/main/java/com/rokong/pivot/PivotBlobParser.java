package com.rokong.pivot;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class PivotBlobParser extends AbstractPivotParser<Blob> {

    protected PivotBlobParser(String pivotColumn) {
        super(pivotColumn);
    }

    @Override
    protected InputStream convert(Blob xml) {
        InputStream is;

        try {
            is = xml.getBinaryStream();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Can not read binary stream", e);
        }

        return is;
    }

}
