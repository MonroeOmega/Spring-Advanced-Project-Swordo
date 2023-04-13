package com.example.swordo.service.impl;

import com.example.swordo.models.entities.Battlefield;
import com.example.swordo.models.entities.BattlefieldSize;
import com.example.swordo.models.entities.Monster;
import com.example.swordo.repository.BattlefieldRepository;
import com.example.swordo.repository.MonsterRepository;
import com.example.swordo.service.BattlefieldService;
import com.example.swordo.views.BattlefieldViewModel;
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
    private final MonsterRepository monsterRepository;

    public BattlefieldServiceImpl(BattlefieldRepository battlefieldRepository, ModelMapper modelMapper, MonsterRepository monsterRepository) {
        this.battlefieldRepository = battlefieldRepository;
        this.modelMapper = modelMapper;
        this.monsterRepository = monsterRepository;
    }

    @Override
    public List<BattlefieldViewModel> getAllBattlefields() {
        return battlefieldRepository.findAll()
                .stream().map(battlefield -> modelMapper.map(battlefield, BattlefieldViewModel.class))
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
                            battlefield.setMonsters(monsterLister(1));
                            battlefieldRepository.save(battlefield);
                        }
                        case MEDIUM -> {
                            battlefield.setDescription("This again is a battlefield but a bigger one.");
                            battlefield.setMonsters(monsterLister(2));
                            battlefieldRepository.save(battlefield);
                        }
                        case BIG -> {
                            battlefield.setDescription("Now that is a very big battlefield. Some people say a guy called Jimmy Omega lurks somewhere around here");
                            List<Monster> monsters = monsterLister(3);
                            monsters.add(monsterRepository.findById(5L).orElse(null));
                            battlefield.setMonsters(monsters);
                            battlefieldRepository.save(battlefield);
                        }
                    }
                });
    }

    private List<Monster> monsterLister(int num){
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < num; i++){
            monsters.add(monsterRepository.findById(1L).orElse(null));
            monsters.add(monsterRepository.findById(2L).orElse(null));
            monsters.add(monsterRepository.findById(3L).orElse(null));
            monsters.add(monsterRepository.findById(4L).orElse(null));
        }
        return monsters;
    }
}
