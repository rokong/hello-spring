import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MainTest {
    @Test
    public void JunitTest(){
        String prefix = "Hello";
        String suffix = "~!";

        assertTrue("Hello Spring~!".equals(prefix+" Spring"+suffix));
    }
}