package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.service.FighterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/town")
public class HomeController {
    private final CurrentFighter currentFighter;
    private final FighterService fighterService;

    public HomeController(CurrentFighter currentFighter, FighterService fighterService) {
        this.currentFighter = currentFighter;
        this.fighterService = fighterService;
    }

    @GetMapping()
    public String town(Model model){
        if (currentFighter.getId() == null) {
            return "index";
        }
        model.addAttribute("hitpoints", currentFighter.getHitpoints());
        return "town";
    }

    @GetMapping("/heal")
    public String heal(){
        if (currentFighter.getId() == null) {
            return "index";
        }
        fighterService.healCurrentFighter();
        fighterService.updateCurrentFighter();
        return "redirect:/town";
    }

}
