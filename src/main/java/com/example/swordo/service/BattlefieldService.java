package com.example.swordo.service;

import com.example.swordo.models.entities.Battlefield;
import com.example.swordo.models.entities.Monster;
import com.example.swordo.views.BattlefieldsViewModel;

import java.util.List;

public interface BattlefieldService {
    List<BattlefieldsViewModel> getAllBattlefields();

    void initBattlefield();

    Battlefield getBattlefieldById(Long bid);

    void setCurrentMonster(Long bid, Long id);

    void removeMonster(Long battlefieldId, Long id);
}
