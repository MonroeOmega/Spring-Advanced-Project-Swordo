package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.current.CurrentMonster;
import com.example.swordo.service.FighterService;
import com.example.swordo.views.FighterFightView;
import com.example.swordo.views.MonsterFightView;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fight")
public class FightController {
    private final CurrentMonster currentMonster;
    private final CurrentFighter currentFighter;
    private final FighterService fighterService;
    private final ModelMapper modelMapper;

    public FightController(CurrentMonster currentMonster, CurrentFighter currentFighter, FighterService fighterService, ModelMapper modelMapper) {
        this.currentMonster = currentMonster;
        this.currentFighter = currentFighter;
        this.fighterService = fighterService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String fight(Model model){
        FighterFightView fighterView = modelMapper.map(fighterService.findCurrent(), FighterFightView.class);
        MonsterFightView monsterView = modelMapper.map(currentMonster, MonsterFightView.class);
        model.addAttribute("fighter",fighterView);
        model.addAttribute("monster",monsterView);
        return "fight";
    }
}
