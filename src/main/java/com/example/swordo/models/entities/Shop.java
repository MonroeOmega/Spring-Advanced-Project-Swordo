package com.example.swordo.models.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "shop")
public class Shop extends BaseEntity{
    private Sword sword;

    public Shop() {
    }
    @ManyToOne
    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }
}
