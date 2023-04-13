package com.example.swordo.service;

import com.example.swordo.views.BattlefieldViewModel;

import java.util.List;

public interface BattlefieldService {
    List<BattlefieldViewModel> getAllBattlefields();

    void initBattlefield();

}
