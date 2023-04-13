package com.example.swordo.controllers;

import com.example.swordo.service.ShopService;
import com.example.swordo.views.ShopViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {
    private final ShopService shopService;


    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    public String shop(Model model){
        List<ShopViewModel> shops = shopService.getAllShops();
        model.addAttribute("shops",shops);
        return "shop";
    }
}
