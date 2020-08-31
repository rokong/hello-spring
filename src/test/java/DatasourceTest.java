import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations={"file:src/main/webapp/WEB-INF/context/root-context.xml" })
public class DatasourceTest {

    @Autowired DataSource ds;

    @Test
    public void DatabaseConnectionTest() throws Exception {
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
}