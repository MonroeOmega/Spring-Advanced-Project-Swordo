package com.example.swordo.views;

import com.example.swordo.models.entities.MonsterClass;

public class MonsterFightView {
    private String description;
    private Integer hitpoints;
    private MonsterClass monsterClass;
    private Integer strength;

    public MonsterFightView() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public MonsterClass getMonsterClass() {
        return monsterClass;
    }

    public void setMonsterClass(MonsterClass monsterClass) {
        this.monsterClass = monsterClass;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }
}
