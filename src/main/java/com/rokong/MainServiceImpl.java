package com.rokong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Autowired MainDAO mainDAO;

    public String getWelcomeMessage() {
        return "Hello Spring~!";
    }

    public String getTimestamp() {
        return mainDAO.selectCurrentDate();
    }
}