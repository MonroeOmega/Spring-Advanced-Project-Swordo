package com.example.swordo.service.impl;

import com.example.swordo.models.entities.Monster;
import com.example.swordo.models.entities.MonsterClass;
import com.example.swordo.models.entities.SwordClass;
import com.example.swordo.repository.MonsterRepository;
import com.example.swordo.service.MonsterService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MonsterServiceImpl implements MonsterService {
    private final MonsterRepository monsterRepository;

    public MonsterServiceImpl(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    @Override
    public void initMonsters() {
        if (monsterRepository.count() != 0){
            return;
        }
        Arrays.stream(MonsterClass.values())
                .forEach(mon -> {
                    Monster monster = new Monster();
                    monster.setMonsterClass(mon);
                    switch (mon){
                        case BEARER -> {
                            monster.setWeakness(SwordClass.KATANA);
                            monster.setDescription("A giant Bear-like creature");
                            monster.setHitpoints(1000);
                            monster.setStrength(20);
                        }
                        case SNAKER -> {
                            monster.setWeakness(SwordClass.KNIFE);
                            monster.setDescription("A giant Snake-like creature");
                            monster.setHitpoints(200);
                            monster.setStrength(40);
                        }
                        case HUMANER -> {
                            monster.setWeakness(SwordClass.REGULAR);
                            monster.setDescription("A giant Human-like creature");
                            monster.setHitpoints(500);
                            monster.setStrength(35);
                        }
                        case LIONER ->{
                            monster.setWeakness(SwordClass.GREATSWORD);
                            monster.setDescription("A giant Lion-like creature");
                            monster.setHitpoints(750);
                            monster.setStrength(30);
                        }
                        case SOMETHING_WITH_ER_AT_THE_BACKER ->
                        {
                            monster.setWeakness(null);
                            monster.setDescription("You can't actually kill that that thing. Some say it's name is Jimmy Omega but what do they know? Just don't do it");
                            monster.setHitpoints(Integer.MAX_VALUE);
                            monster.setStrength(Integer.MAX_VALUE);
                        }
                    }
                    monsterRepository.save(monster);
                });
    }

    @Override
    public Monster getJimmyOmega() {
        return monsterRepository.findById(5L).orElse(null);
    }

    @Override
    public List<Monster> getAllExceptJimmy() {
        List<Monster> monsters = monsterRepository.findAll();
        monsters.remove(4);
        return monsters;
    }

    @Override
    public Monster getMonsterById(Long id) {
        return monsterRepository.findById(id).orElse(null);
    }
}
