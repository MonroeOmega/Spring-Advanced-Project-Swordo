package com.example.swordo.service;

import com.example.swordo.models.entities.Fighter;
import com.example.swordo.models.service.FighterServiceModel;
import com.example.swordo.views.FighterViewModel;

import java.util.List;

public interface FighterService {
    void registerFighter(FighterServiceModel fighterServiceModel);

    FighterServiceModel findByUsernameAndPassword(String username, String password);

    void loginFighter(FighterServiceModel fighterServiceModel);

    void registerAdmin(FighterServiceModel fighterServiceModel);

    List<FighterViewModel> getAllFighters();

    void editFighter(FighterServiceModel fighterServiceModel);

    void updateCurrentFighter();

    void healCurrentFighter();

    Fighter findCurrent();

    void strike();
}
