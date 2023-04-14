package com.example.swordo.controllers;

import com.example.swordo.models.entities.Battlefield;
import com.example.swordo.models.entities.Monster;
import com.example.swordo.service.BattlefieldService;
import com.example.swordo.service.MonsterService;
import com.example.swordo.views.BattlefieldViewModel;
import com.example.swordo.views.BattlefieldsViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("battlefields")
public class BattlefieldController {
    private final BattlefieldService battlefieldService;
    private final MonsterService monsterService;

    public BattlefieldController(BattlefieldService battlefieldService, MonsterService monsterService) {
        this.battlefieldService = battlefieldService;
        this.monsterService = monsterService;
    }

    @GetMapping("")
    public String battlefield(Model model){
        List<BattlefieldsViewModel> battlefields = battlefieldService.getAllBattlefields();
        model.addAttribute("battlefields",battlefields);
        return "battlefields";
    }

    @GetMapping("/battlefield/{bid}")
    public String battlefield(Model model,@PathVariable Long bid){
        Battlefield battlefield = battlefieldService.getBattlefieldById(bid);
        model.addAttribute("battlefield",battlefield);
        return "battlefield";
    }

    @GetMapping("/battlefield/{bid}/fight/{id}")
    public String fight(Model model, @PathVariable Long id, @PathVariable String bid) {
        Monster monster = monsterService.getMonsterById(id);
        model.addAttribute("monster",monster);
        return "fight";
    }
}
