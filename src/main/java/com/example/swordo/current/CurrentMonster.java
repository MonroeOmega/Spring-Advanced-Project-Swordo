package com.example.swordo.current;

import com.example.swordo.models.entities.MonsterClass;
import com.example.swordo.models.entities.SwordClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentMonster {
    private Long id;
    private Long battlefieldId;
    private String description;
    private Integer hitpoints;
    private MonsterClass monsterClass;
    private Integer strength;
    private SwordClass weakness;

    public CurrentMonster() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBattlefieldId() {
        return battlefieldId;
    }

    public void setBattlefieldId(Long battlefieldId) {
        this.battlefieldId = battlefieldId;
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

    public SwordClass getWeakness() {
        return weakness;
    }

    public void setWeakness(SwordClass weakness) {
        this.weakness = weakness;
    }
}
