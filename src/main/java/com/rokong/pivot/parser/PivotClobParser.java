package com.rokong.pivot.parser;

import com.rokong.pivot.parser.AbstractPivotParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.SQLException;

public class PivotClobParser extends AbstractPivotParser<Clob> {

    public PivotClobParser(String pivotColumn){
        super(pivotColumn);
    }

    @Override
    protected InputStream convert(Clob xml) {
        Reader reader = null;
        InputStream is = null;

        try {
            reader = xml.getCharacterStream();
            is = IOUtils.toInputStream(IOUtils.toString(reader), StandardCharsets.UTF_8);

        } catch (SQLException | IOException e) {
            throw new IllegalArgumentException("Can not read character stream", e);
        } finally {
            if(reader != null) { try { reader.close(); } catch (IOException e) { } }
            if(is != null) { try { is.close(); } catch (IOException e) { } }
        }

        return is;
    }
}
