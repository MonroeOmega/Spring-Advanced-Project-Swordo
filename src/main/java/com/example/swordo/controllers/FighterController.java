package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.models.binding.FighterEditBindingModel;
import com.example.swordo.models.binding.FighterLoginBindingModel;
import com.example.swordo.models.binding.FighterRegisterBindingModel;
import com.example.swordo.models.service.FighterServiceModel;
import com.example.swordo.service.FighterService;
import com.example.swordo.views.FighterEditViewModel;
import com.example.swordo.views.FighterProfileViewModel;
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
        if (currentFighter.getId() != null) {
            return "redirect:/";
        }
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
        if (currentFighter.getId() != null) {
            return "redirect:/";
        }
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

        return "redirect:/town";
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession){
        fighterService.updateCurrentFighter();
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    private String profile(Model model){
        if (currentFighter.getId() == null) {
            return "index";
        }
        FighterProfileViewModel profileViewModel = modelMapper.map(fighterService.findCurrent(), FighterProfileViewModel.class);
        model.addAttribute("profileViewModel",profileViewModel);
        if(profileViewModel.getSword() != null) {
            model.addAttribute("sword", profileViewModel.getSword().getSwordClass());
        }else{
            model.addAttribute("sword",null);
        }
        return "profile";
    }

    @GetMapping("/profile/edit")
    private String edit(Model model){
        if (currentFighter.getId() == null) {
            return "index";
        }
        FighterEditViewModel editViewModel = modelMapper.map(currentFighter, FighterEditViewModel.class);
        model.addAttribute("editViewModel",editViewModel);

        return "edit";
    }

    @PostMapping("/profile/edit")
    private String edit(@Valid FighterEditBindingModel fighterEditBindingModel,BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (currentFighter.getId() == null) {
            return "index";
        }
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("fighterEditBindingModel",fighterEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.fighterEditBindingModel",bindingResult);

            return "redirect:/fighters/profile/edit";
        }

        fighterService.editFighter(modelMapper.map(fighterEditBindingModel, FighterServiceModel.class));
        fighterService.updateCurrentFighter();

        return "redirect:/fighters/profile";
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
