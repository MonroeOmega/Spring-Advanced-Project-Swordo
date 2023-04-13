package com.example.swordo.models.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class FighterEditBindingModel {
    private String username;
    private String email;
    private String backstory;

    public FighterEditBindingModel() {
    }

    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Size(min = 10,max = 20)
    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }
}
