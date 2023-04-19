package com.example.swordo.service.impl;

import com.example.swordo.current.CurrentMonster;
import com.example.swordo.models.entities.Battlefield;
import com.example.swordo.models.entities.BattlefieldSize;
import com.example.swordo.models.entities.Monster;
import com.example.swordo.repository.BattlefieldRepository;
import com.example.swordo.service.BattlefieldService;
import com.example.swordo.service.MonsterService;
import com.example.swordo.views.BattlefieldsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BattlefieldServiceImpl implements BattlefieldService {
    private final BattlefieldRepository battlefieldRepository;
    private final ModelMapper modelMapper;
    private final MonsterService monsterService;
    private final CurrentMonster currentMonster;

    public BattlefieldServiceImpl(BattlefieldRepository battlefieldRepository, ModelMapper modelMapper, MonsterService monsterService, CurrentMonster currentMonster) {
        this.battlefieldRepository = battlefieldRepository;
        this.modelMapper = modelMapper;
        this.monsterService = monsterService;
        this.currentMonster = currentMonster;
    }

    @Override
    public List<BattlefieldsViewModel> getAllBattlefields() {
        return battlefieldRepository.findAll()
                .stream().map(battlefield -> {
                     BattlefieldsViewModel battlefieldsViewModel = modelMapper.map(battlefield, BattlefieldsViewModel.class);
                     battlefieldsViewModel.setCount(battlefield.getMonsters().size());
                     return battlefieldsViewModel;
                    })
                .collect(Collectors.toList());
    }

    @Override
    public void initBattlefield() {
        if(battlefieldRepository.count() != 0){
            return;
        }
        Arrays.stream(BattlefieldSize.values())
                .forEach(battlefieldSize -> {
                    Battlefield battlefield = new Battlefield();
                    battlefield.setSize(battlefieldSize);
                    switch (battlefieldSize){
                        case SMALL -> {
                            battlefield.setDescription("It's a battlefield but a small one.");
                            battlefield.setMonsters(getBasicMonsterSets(1));
                            battlefieldRepository.save(battlefield);
                        }
                        case MEDIUM -> {
                            battlefield.setDescription("This again is a battlefield but a bigger one.");
                            battlefield.setMonsters(getBasicMonsterSets(2));
                            battlefieldRepository.save(battlefield);
                        }
                        case BIG -> {
                            battlefield.setDescription("Now that is a very big battlefield. Some people say a guy called Jimmy Omega lurks somewhere around here");
                            List<Monster> monsters = getBasicMonsterSets(3);
                            monsters.add(monsterService.getJimmyOmega());
                            battlefield.setMonsters(monsters);
                            battlefieldRepository.save(battlefield);
                        }
                    }
                });
    }

    @Override
    public Battlefield getBattlefieldById(Long bid) {
        return battlefieldRepository.findById(bid).orElse(null);
    }

    @Override
    public void setCurrentMonster(Long bid, Long id) {
        Battlefield battlefield = battlefieldRepository.findById(bid).orElse(null);
        if(battlefield == null){
            return;
        }
        Monster monster = battlefield.getMonsters().stream()
                .filter(monter -> monter.getId().equals(id)).findFirst()
                .orElse(null);
        if(monster == null){
            return;
        }
        if(Objects.equals(monster.getId(), id)){
            currentMonster.setId(id);
            currentMonster.setBattlefieldId(bid);
            currentMonster.setDescription(monster.getDescription());
            currentMonster.setMonsterClass(monster.getMonsterClass());
            currentMonster.setHitpoints(monster.getHitpoints());
            currentMonster.setStrength(monster.getStrength());
            currentMonster.setWeakness(monster.getWeakness());
        }
    }

    @Override
    public void removeMonster(Long battlefieldId, Long id) {
        Battlefield battlefield = battlefieldRepository.findById(battlefieldId).orElse(null);
        List<Monster> monsters = battlefield.getMonsters();
        monsters.remove(monsterService.getMonsterById(id));
        battlefield.setMonsters(monsters);
        battlefieldRepository.save(battlefield);
    }

    private List<Monster> getBasicMonsterSets(int num){
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < num; i++){
            monsters.addAll(monsterService.getAllExceptJimmy());
        }
        return monsters;
    }
}
