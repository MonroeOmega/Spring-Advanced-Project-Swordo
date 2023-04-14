package com.example.swordo.views;

import com.example.swordo.models.entities.BattlefieldSize;

public class BattlefieldsViewModel {
    private Long id;
    private BattlefieldSize size;
    private String description;
    private Integer count;

    public BattlefieldsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
