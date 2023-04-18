package com.example.swordo.service;

import com.example.swordo.models.entities.Sword;

public interface SwordService {
    void initSwords();

    Sword getRegular();
}
