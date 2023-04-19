package com.example.swordo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "fighters")
public class Fighter extends BaseEntity{
    private String username;
    private String firstName;
    private String lastName;
    private FighterRoleEnum role;
    private String email;
    private String password;
    private Integer hitpoints;
    private String backstory;
    private Sword sword;
    private Integer coins;
    private Integer swordDurability;

    public Fighter() {
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(nullable = false)
    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }
    @Column(nullable = false,columnDefinition = "TEXT")
    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }
    @ManyToOne
    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    @Enumerated(value = EnumType.STRING)
    public FighterRoleEnum getRole() {
        return role;
    }

    public void setRole(FighterRoleEnum role) {
        this.role = role;
    }

    @Column
    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getSwordDurability() {
        return swordDurability;
    }

    public void setSwordDurability(Integer swordDurability) {
        this.swordDurability = swordDurability;
    }
}
