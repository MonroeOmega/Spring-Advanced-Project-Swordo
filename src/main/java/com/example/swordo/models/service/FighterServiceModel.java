package com.example.swordo.models.service;

import com.example.swordo.models.entities.FighterRoleEnum;
import com.example.swordo.models.entities.Sword;

public class FighterServiceModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private FighterRoleEnum role;
    private String email;
    private String password;
    private Integer hitpoints;
    private String backstory;
    private Integer coins;
    private Sword sword;
    private Integer swordDurability;

    public FighterServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public FighterRoleEnum getRole() {
        return role;
    }

    public void setRole(FighterRoleEnum role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public Integer getSwordDurability() {
        return swordDurability;
    }

    public void setSwordDurability(Integer swordDurability) {
        this.swordDurability = swordDurability;
    }
}
