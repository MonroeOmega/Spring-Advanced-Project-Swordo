package com.example.swordo.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "battlefields")
public class Battlefield extends BaseEntity{
    private List<Monster> monsters;
    private BattlefieldSize size;
    private String description;

    public Battlefield() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
    @Enumerated(EnumType.STRING)
    public BattlefieldSize getSize() {
        return size;
    }

    public void setSize(BattlefieldSize size) {
        this.size = size;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
