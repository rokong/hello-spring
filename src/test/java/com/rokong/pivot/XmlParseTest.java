package com.rokong.pivot;

import com.rokong.pivot.handler.ReadDocHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * xml.parse에 있는 XML PARSER를 테스트한다.
 *
 * @author rokong
 * @since 2021.04.01
 */
public class XmlParseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String URI = "src/test/java/com/rokong/pivot/pivot-column.xml";

    private SAXParser getSaxParser(){
        SAXParser saxParser;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);

        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new IllegalArgumentException("Can not create SAXParser", e);
        }

        return saxParser;
    }

    @Test
    public void readFile() throws IOException, SAXException {
        SAXParser saxParser = getSaxParser();
        saxParser.parse(URI, new ReadDocHandler());
    }

    @Test
    public void readPivotXML(){
        InputSource inputSoruce = new InputSource(URI);
        PivotXMLReader pivotXMLReader = new PivotXMLReader();

        Map<String, Object> pivotColumns = pivotXMLReader.parsePivotColumn(inputSoruce);
        logger.debug(pivotColumns.toString());

        assertThat(pivotColumns.containsKey("GOLD_WINNER"), is(equalTo(true)));
        assertThat(pivotColumns.get("GOLD_WINNER"), is(equalTo("가")));
    }
}
