package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.models.binding.FighterEditBindingModel;
import com.example.swordo.models.binding.FighterLoginBindingModel;
import com.example.swordo.models.binding.FighterRegisterBindingModel;
import com.example.swordo.models.service.FighterServiceModel;
import com.example.swordo.service.FighterService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fighters")
public class FighterController {
    private final FighterService fighterService;
    private final ModelMapper modelMapper;
    private final CurrentFighter currentFighter;

    public FighterController(FighterService fighterService, ModelMapper modelMapper, CurrentFighter currentFighter) {
        this.fighterService = fighterService;
        this.modelMapper = modelMapper;
        this.currentFighter = currentFighter;
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String confirmFighterRegister(@Valid FighterRegisterBindingModel fighterRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()||!fighterRegisterBindingModel.getRepeatPassword().equals(fighterRegisterBindingModel.getPassword())){
            redirectAttributes.addFlashAttribute("fighterRegisterBindingModel",fighterRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.fighterRegisterBindingModel",bindingResult);

            return "redirect:register";
        }

        fighterService.registerFighter(modelMapper.map(fighterRegisterBindingModel, FighterServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    private String login(Model model){
        if(!model.containsAttribute("isFound")){
            model.addAttribute("isFound",true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String confirmLogin(@Valid FighterLoginBindingModel fighterLoginBindingModel, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("fighterLoginBindingModel",fighterLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.fighterLoginBindingModel",bindingResult);

            return "redirect:login";
        }

        FighterServiceModel fighterServiceModel =
                fighterService.findByUsernameAndPassword(fighterLoginBindingModel.getUsername(),fighterLoginBindingModel.getPassword());
        if(fighterServiceModel == null){
            redirectAttributes.addFlashAttribute("fighterLoginBindingModel",fighterLoginBindingModel);
            redirectAttributes.addFlashAttribute("isFound",false);
            return "redirect:login";
        }

        fighterService.loginFighter(fighterServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession){
        fighterService.confirmLogout(currentFighter);
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    private String profile(Model model){
        model.addAttribute("currentFighter",currentFighter);
        return "profile";
    }

    @PostMapping("/profile/edit")
    private String edit(@Valid FighterEditBindingModel fighterEditBindingModel,BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("fighterEditBindingModel",fighterEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.fighterEditBindingModel",bindingResult);

            return "redirect:/profile/edit";
        }

        fighterService.editFighter(modelMapper.map(fighterEditBindingModel, FighterServiceModel.class));

        return "redirect:profile";
    }

    @ModelAttribute
    public FighterRegisterBindingModel fighterRegisterBindingModel(){
        return new FighterRegisterBindingModel();
    }

    @ModelAttribute
    public FighterLoginBindingModel fighterLoginBindingModel(){
        return new FighterLoginBindingModel();
    }

    @ModelAttribute
    public FighterEditBindingModel fighterEditBindingModel(){
        return new FighterEditBindingModel();
    }
}