package com.example.swordo.models.binding;

import jakarta.validation.constraints.Size;

public class FighterLoginBindingModel {
    private String username;
    private String password;

    public FighterLoginBindingModel() {
    }

    @Size(min = 5,max = 20,message = "Username length must be between 5 and 20 characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Size(min = 3,max = 20, message = "Password length must be more than 3 characters and less than 20.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
