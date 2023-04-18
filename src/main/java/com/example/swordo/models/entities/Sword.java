package com.example.swordo.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "swords")
public class Sword extends BaseEntity{
    private SwordClass swordClass;
    private Integer damage;
    private Integer durability;
    private Integer price;

    public Sword() {
    }
    @Enumerated(EnumType.STRING)
    public SwordClass getSwordClass() {
        return swordClass;
    }

    public void setSwordClass(SwordClass swordClass) {
        this.swordClass = swordClass;
    }
    @Column(nullable = false)
    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
    @Column(nullable = false)
    public Integer getDurability() {
        return durability;
    }

    public void setDurability(Integer durability) {
        this.durability = durability;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
