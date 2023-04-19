package com.example.swordo.views;

import com.example.swordo.models.entities.FighterRoleEnum;
import com.example.swordo.models.entities.Sword;

public class FighterProfileViewModel {
    private String username;
    private String firstName;
    private String lastName;
    private FighterRoleEnum role;
    private String email;
    private Integer hitpoints;
    private String backstory;
    private Integer coins;
    private Sword sword;
    private Integer strikesLeft;

    public FighterProfileViewModel() {
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

    public Integer getStrikesLeft() {
        return strikesLeft;
    }

    public void setStrikesLeft(Integer strikesLeft) {
        this.strikesLeft = strikesLeft;
    }
}
