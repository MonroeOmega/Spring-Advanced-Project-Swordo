package com.example.swordo.service.impl;

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
import java.util.stream.Collectors;

@Service
public class BattlefieldServiceImpl implements BattlefieldService {
    private final BattlefieldRepository battlefieldRepository;
    private final ModelMapper modelMapper;
    private final MonsterService monsterService;

    public BattlefieldServiceImpl(BattlefieldRepository battlefieldRepository, ModelMapper modelMapper, MonsterService monsterService) {
        this.battlefieldRepository = battlefieldRepository;
        this.modelMapper = modelMapper;
        this.monsterService = monsterService;
    }

    @Override
    public List<BattlefieldsViewModel> getAllBattlefields() {
        return battlefieldRepository.findAll()
                .stream().map(battlefield -> modelMapper.map(battlefield, BattlefieldsViewModel.class))
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

    private List<Monster> getBasicMonsterSets(int num){
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < num; i++){
            monsters.addAll(monsterService.getAllExceptJimmy());
        }
        return monsters;
    }
}
