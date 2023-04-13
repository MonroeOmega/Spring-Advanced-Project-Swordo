package com.example.swordo.views;

import com.example.swordo.models.entities.Sword;

import java.util.List;

public class ShopViewModel {
    private Long id;
    private Sword sword;

    public ShopViewModel() {
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
