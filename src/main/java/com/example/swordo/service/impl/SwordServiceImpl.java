package com.example.swordo.service.impl;

import com.example.swordo.models.entities.Sword;
import com.example.swordo.models.entities.SwordClass;
import com.example.swordo.repository.SwordRepository;
import com.example.swordo.service.SwordService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SwordServiceImpl implements SwordService {
    private final SwordRepository swordRepository;

    public SwordServiceImpl(SwordRepository swordRepository) {
        this.swordRepository = swordRepository;
    }


    @Override
    public void initSwords() {
        if (swordRepository.count() != 0) {
            return;
        }
        Arrays.stream(SwordClass.values())
                .forEach(swor -> {
                    Sword sword = new Sword();
                    sword.setSwordClass(swor);
                    switch (swor) {
                        case KATANA -> {
                            sword.setDurability(20);
                            sword.setDamage(100);
                        }
                        case KNIFE -> {
                            sword.setDurability(50);
                            sword.setDamage(20);
                        }
                        case GREATSWORD -> {
                            sword.setDurability(75);
                            sword.setDamage(50);
                        }
                        case REGULAR -> {
                            sword.setDurability(40);
                            sword.setDamage(40);
                        }
                    }
                    swordRepository.save(sword);
                });

    }
}
