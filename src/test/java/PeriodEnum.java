import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 주기 enum 테스트이다.
 *
 * @author rokong
 * @since 2021.03.22
 * @see com.rokong.period.PERIOD
 */
public class PeriodEnum {

    @Test
    public void assertion(){
        assertThat(1 + 1, is(equalTo(2)));
    }

    @Test
    public void stringTest(){
        String string = null;
        string = (String) null;

        assertThat(string, is(equalTo(null)));
        assertThat(StringUtils.defaultString((String) string), is(equalTo("")));
    }
}
