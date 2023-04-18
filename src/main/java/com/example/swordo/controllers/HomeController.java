package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final CurrentFighter currentFighter;

    public HomeController(CurrentFighter currentFighter) {
        this.currentFighter = currentFighter;
    }

    @GetMapping()
    public String home(Model model){
        if (currentFighter.getId() == null) {
            return "index";
        }
        return "town";
    }

}
