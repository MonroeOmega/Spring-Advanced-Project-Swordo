package com.example.swordo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("battlefield")
public class BattlefieldController {
    @GetMapping("")
    public String battlefield(){
        return "battlefield";
    }

    @GetMapping("/fight")
    public String fight(){
        return "fight";
    }
}
