package com.rokong.main;

import static org.junit.Assert.assertTrue;

import com.rokong.main.MainDAO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations={"file:src/main/webapp/WEB-INF/context/root-context.xml" })
public class MainTest {

    @Autowired MainDAO mainDAO;

    @Test
    public void JunitTest(){
        String prefix = "Hello";
        String suffix = "~!";

        assertTrue("Hello Spring~!".equals(prefix+" Spring"+suffix));
    }

    @Test
    public void MainDAOTest(){
        String time = mainDAO.selectCurrentDate();
        assertTrue(time != null && !"".equals(time));
    }
}