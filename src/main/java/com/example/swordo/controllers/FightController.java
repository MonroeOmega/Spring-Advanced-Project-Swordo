package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.current.CurrentMonster;
import com.example.swordo.service.BattlefieldService;
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
    private final BattlefieldService battlefieldService;
    private final ModelMapper modelMapper;

    public FightController(CurrentMonster currentMonster, CurrentFighter currentFighter, FighterService fighterService, BattlefieldService battlefieldService, ModelMapper modelMapper) {
        this.currentMonster = currentMonster;
        this.currentFighter = currentFighter;
        this.fighterService = fighterService;
        this.battlefieldService = battlefieldService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String fight(Model model){
        if (currentFighter.getId() == null) {
            return "redirect:/";
        }
        if(currentMonster.getId() == null){
            return "redirect:/battlefields";
        }
        FighterFightView fighterView = modelMapper.map(fighterService.findCurrent(), FighterFightView.class);
        MonsterFightView monsterView = modelMapper.map(currentMonster, MonsterFightView.class);
        model.addAttribute("fighter",fighterView);
        model.addAttribute("monster",monsterView);
        return "fight";
    }

    @GetMapping("/strike")
    public String strike(){
        if (currentFighter.getId() == null) {
            return "redirect:/";
        }
        if(currentMonster.getId() == null){
            return "redirect:/battlefields";
        }
        fighterService.strike();
        fighterService.updateCurrentFighter();
        if(currentFighter.getHitpoints() <= 0){
            return "redirect:/fight/death";
        }
        if(currentMonster.getHitpoints() <= 0){
            return "redirect:/fight/win";
        }
        return "redirect:/fight";
    }

    @GetMapping("/give")
    public String give(){
        if (currentFighter.getId() == null) {
            return "redirect:/";
        }
        currentMonster.setId(null);
        return "redirect:/town";
    }

    @GetMapping("/win")
    public String win(){
        if (currentFighter.getId() == null) {
            return "redirect:/";
        }
        battlefieldService.removeMonster(currentMonster.getBattlefieldId(),currentMonster.getId());
        currentMonster.setId(null);
        currentFighter.setCoins(currentFighter.getCoins() + 100);
        return "redirect:/town";
    }

    @GetMapping("/death")
    public String death(){
        if (currentFighter.getId() == null) {
            return "redirect:/";
        }
        currentMonster.setId(null);
        return "redirect:/town";
    }
}
