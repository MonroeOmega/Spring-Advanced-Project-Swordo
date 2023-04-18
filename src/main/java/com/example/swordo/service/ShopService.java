package com.example.swordo.service;

import com.example.swordo.views.ShopViewModel;

import java.util.List;

public interface ShopService {

    List<ShopViewModel> getAllShops();

    void initShop();

    void sellSword(Long id);
}
