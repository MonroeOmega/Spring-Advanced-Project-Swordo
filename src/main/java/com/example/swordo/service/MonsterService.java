package com.example.swordo.service;

import com.example.swordo.models.entities.Monster;

import java.util.List;

public interface MonsterService {
    void initMonsters();

    Monster getJimmyOmega();

    List<Monster> getAllExceptJimmy();

    Monster getMonsterById(Long id);


}
