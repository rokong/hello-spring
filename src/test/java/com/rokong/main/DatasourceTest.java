package com.rokong.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;

import org.apache.ibatis.annotations.Select;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations={"file:src/main/webapp/WEB-INF/context/root-context.xml" })
public class DatasourceTest {

    // from xml beans
    @Autowired
    @Qualifier("hikariConfig")
    HikariConfig hc;

    @Autowired
    DataSource ds;

    @Autowired
    SqlSessionFactoryBean sqlSessionFactory;

    @Autowired
    SqlSessionTemplate sqlSession;

    @Test
    public void hikariConfigTest(){
        assertTrue(hc != null);
        assertFalse(hc.isAutoCommit());
    }

    @Test
    public void datasourceTest(){
        assertTrue(ds != null);
    }

    @Test
    public void databaseConnectionTest() throws Exception {        
        Connection con = null;
        try{
            con = ds.getConnection();
            assertTrue(con != null);
        }catch(SQLException e){
            throw new Exception(e);
        }finally{
            if(con!=null){
                con.close();
            }
        }
    }

    @Test
    public void mybatisTest(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String javaDate = fmt.format(Calendar.getInstance().getTime());

        //inject mapper without xml, only with @Select annotation
        sqlSession.getConfiguration().addMapper(TestMapper.class);
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);

        String dbDate = testMapper.getTime();

        assertTrue(javaDate.equals(dbDate));
    }

    public interface TestMapper {
        @Select("SELECT TO_CHAR(now(), 'YYYYMMDD')")
        String getTime();
    }
}