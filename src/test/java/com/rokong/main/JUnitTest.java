package com.rokong.main;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class JUnitTest {

    @Test
    public void stringBufferTrim(){
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("     abc    ");

        sbuf.trimToSize();

        assertThat(sbuf.toString(), is(not(equalTo("abc"))));
    }
}
