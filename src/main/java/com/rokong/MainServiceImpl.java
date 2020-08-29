package com.rokong;

import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {
    public String getWelcomeMessage() {
        return "Hello Spring~!";
    }    
}