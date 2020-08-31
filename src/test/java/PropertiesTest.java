import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {
    @Test
    public void loadFile() throws IOException {
        String propertyFileName = "";
        //relative path
        propertyFileName = "src/main/webapp/WEB-INF/properties/hikari.properties";
        
        //absolute path
        //propertyFileName = "/src/main/webapp/WEB-INF/properties/hikari.properties";

        File propFile = new File(propertyFileName);
        assertTrue(propFile.exists());
        assertTrue(propFile.isFile());
        
        InputStream is = new FileInputStream(propFile);
        Properties props = new Properties();
        props.load(is);

        System.out.println("loaded properties : "+props.toString());
    }
}