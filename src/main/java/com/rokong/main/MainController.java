package com.rokong.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired MainService mainService;

    @GetMapping("/")
    public String helloSpring(Model model){
        model.addAttribute("message", mainService.getWelcomeMessage());
        model.addAttribute("timestamp", mainService.getTimestamp());
        return "/index";
    }
}