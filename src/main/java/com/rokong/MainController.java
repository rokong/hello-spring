package com.rokong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired MainService mainService;

    @GetMapping("/")
    public String helloSpring(Model model){
        model.addAttribute("message", mainService.getWelcomeMessage());
        return "/index";
    }
}