package com.example.swordo.controllers;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.service.ShopService;
import com.example.swordo.views.ShopViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {
    private final ShopService shopService;
    private final CurrentFighter currentFighter;


    public ShopController(ShopService shopService, CurrentFighter currentFighter) {
        this.shopService = shopService;
        this.currentFighter = currentFighter;
    }

    @GetMapping("/shop")
    public String shop(Model model){
        if (currentFighter.getId() == null) {
            return "index";
        }
        List<ShopViewModel> shop = shopService.getAllShops();
        model.addAttribute("shop",shop);
        return "shop";
    }
}
