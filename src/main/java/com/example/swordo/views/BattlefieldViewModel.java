package com.example.swordo.views;

import com.example.swordo.models.entities.BattlefieldSize;
import com.example.swordo.models.entities.Monster;

import java.util.List;

public class BattlefieldViewModel {
    private String name;
    private List<Monster> monsters;
    private BattlefieldSize size;
    private String description;

    public BattlefieldViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public BattlefieldSize getSize() {
        return size;
    }

    public void setSize(BattlefieldSize size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
