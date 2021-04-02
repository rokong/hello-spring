package com.rokong.pivot;

import com.rokong.pivot.parser.PivotBlobParser;
import com.rokong.pivot.parser.PivotClobParser;
import com.rokong.pivot.parser.PivotRowParser;
import com.rokong.pivot.parser.PivotStringParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations={"file:src/main/webapp/WEB-INF/context/root-context.xml" })
public class PivotRowTest {

    @Autowired
    private SqlSessionTemplate sqlSession;
    private final String PREFIX = "com.rokong.pivot";
    private final String XML_KEY = "PRIZE_XML";

    @Test(expected = NoClassDefFoundError.class)
    public void selectXMLWithException(){
        Map<String, Object> row = sqlSession.selectOne(PREFIX+"selectXML");
        // can not find oracle/xdb/XMLType
    }

    @Test
    public void selectXMLWithoutException(){
        //select CLOB
        Map<String, Object> row = sqlSession.selectOne(PREFIX+"selectXMLClob");
        assertThat(row.get(XML_KEY), is(notNullValue()));
        assertThat(row.get(XML_KEY), is(instanceOf(Clob.class)));

        //select BLOB
        row = sqlSession.selectOne(PREFIX+"selectXMLBlob");
        assertThat(row.get(XML_KEY), is(notNullValue()));
        assertThat(row.get(XML_KEY), is(instanceOf(Blob.class)));

        row = sqlSession.selectOne(PREFIX+"selectXMLString");
        assertThat(row.get(XML_KEY), is(notNullValue()));
        assertThat(row.get(XML_KEY), is(instanceOf(String.class)));
    }

    private void assertPivotColumn(Map<String, Object> pivotRow){
        assertThat(pivotRow.get("GOLD_WINNER"), is(equalTo("가")));
        assertThat(pivotRow.get("SILV_SCORE"), is(equalTo("93")));
    }

    @Test
    public void pivotClob(){
        //select CLOB
        Map<String, Object> row = sqlSession.selectOne(PREFIX+"selectXMLClob");

        PivotRowParser pivotRowParser = new PivotClobParser(XML_KEY);
        row = pivotRowParser.parsePivotRow(row);

        assertPivotColumn(row);
    }

    @Test
    public void pivotBlob(){
        //select BLOB
        Map<String, Object> row = sqlSession.selectOne(PREFIX+"selectXMLBlob");

        PivotRowParser pivotRowParser = new PivotBlobParser(XML_KEY);
        row = pivotRowParser.parsePivotRow(row);

        assertPivotColumn(row);
    }

    @Test
    public void pivotString(){
        //select BLOB
        Map<String, Object> row = sqlSession.selectOne(PREFIX+"selectXMLString");

        PivotRowParser pivotRowParser = new PivotStringParser(XML_KEY);
        row = pivotRowParser.parsePivotRow(row);

        assertPivotColumn(row);
    }
}
