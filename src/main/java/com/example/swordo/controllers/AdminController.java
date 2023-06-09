package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.models.binding.AdminRegisterBindingModel;
import com.example.swordo.models.entities.FighterRoleEnum;
import com.example.swordo.models.service.FighterServiceModel;
import com.example.swordo.service.BattlefieldService;
import com.example.swordo.service.FighterService;
import com.example.swordo.service.ShopService;
import com.example.swordo.views.BattlefieldsViewModel;
import com.example.swordo.views.FighterViewModel;
import com.example.swordo.views.ShopViewModel;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {
    private final FighterService fighterService;
    private final BattlefieldService battlefieldService;
    private final ShopService shopService;
    private final ModelMapper modelMapper;
    private final CurrentFighter currentFighter;

    public AdminController(FighterService fighterService, BattlefieldService battlefieldService, ShopService shopService, ModelMapper modelMapper, CurrentFighter currentFighter) {
        this.fighterService = fighterService;
        this.battlefieldService = battlefieldService;
        this.shopService = shopService;
        this.modelMapper = modelMapper;
        this.currentFighter = currentFighter;
    }

    @GetMapping("/admin/register")
    public String register(){
        if (currentFighter.getId() != null) {
            return "redirect:/";
        }
        return "admin-register";
    }

    @PostMapping("/admin/register")
    public String confirmFighterRegister(@Valid AdminRegisterBindingModel adminRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()||!adminRegisterBindingModel.getRepeatPassword().equals(adminRegisterBindingModel.getPassword())){
            redirectAttributes.addFlashAttribute("adminRegisterBindingModel",adminRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.adminRegisterBindingModel",bindingResult);

            return "redirect:/admin/register";
        }

        fighterService.registerAdmin(modelMapper.map(adminRegisterBindingModel, FighterServiceModel.class));

        return "redirect:/fighters/login";
    }

    @GetMapping("/admin/console")
    public String console(Model model){
        if (currentFighter.getId() == null||currentFighter.getRole() != FighterRoleEnum.ADMIN) {
            return "index";
        }
        List<FighterViewModel>  fighters = fighterService.getAllFighters();
        model.addAttribute("fighters",fighters);
        List<BattlefieldsViewModel> battlefields = battlefieldService.getAllBattlefields();
        model.addAttribute("battlefields",battlefields);
        List<ShopViewModel> shop = shopService.getAllShops();
        model.addAttribute("shop",shop);
        return "admin-console";
    }

    @ModelAttribute
    public AdminRegisterBindingModel adminRegisterBindingModel(){
        return new AdminRegisterBindingModel();
    }
}
