package com.example.swordo.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "monsters")
public class Monster extends BaseEntity{
    private String description;
    private Integer hitpoints;
    private MonsterClass monsterClass;
    private Integer strength;
    private List<Battlefield> battlefields;
    private SwordClass weakness;

    public Monster() {
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable = false)
    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }
    @Enumerated(value = EnumType.STRING)
    public MonsterClass getMonsterClass() {
        return monsterClass;
    }

    public void setMonsterClass(MonsterClass monsterClass) {
        this.monsterClass = monsterClass;
    }
    @Column(nullable = false)
    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    @ManyToMany(mappedBy = "monsters",fetch = FetchType.EAGER)
    public List<Battlefield> getBattlefields() {
        return battlefields;
    }

    public void setBattlefields(List<Battlefield> battlefields) {
        this.battlefields = battlefields;
    }

    @Enumerated(value = EnumType.STRING)
    public SwordClass getWeakness() {
        return weakness;
    }

    public void setWeakness(SwordClass weakness) {
        this.weakness = weakness;
    }
}
