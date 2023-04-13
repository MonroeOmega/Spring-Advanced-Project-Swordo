package com.example.swordo.initialization;

import com.example.swordo.service.BattlefieldService;
import com.example.swordo.service.MonsterService;
import com.example.swordo.service.ShopService;
import com.example.swordo.service.SwordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final SwordService swordService;
    private final MonsterService monsterService;
    private final ShopService shopService;
    private final BattlefieldService battlefieldService;

    public DatabaseInit(SwordService swordService, MonsterService monsterService, ShopService shopService, BattlefieldService battlefieldService) {
        this.swordService = swordService;
        this.monsterService = monsterService;
        this.shopService = shopService;
        this.battlefieldService = battlefieldService;
    }


    @Override
    public void run(String... args) throws Exception {
        swordService.initSwords();
        monsterService.initMonsters();
        shopService.initShop();
        battlefieldService.initBattlefield();
    }
}
