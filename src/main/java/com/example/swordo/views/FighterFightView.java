package com.example.swordo.views;

import com.example.swordo.models.entities.Sword;

public class FighterFightView {
    private Integer hitpoints;
    private Sword sword;

    public FighterFightView() {
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }
}
