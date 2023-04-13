package com.example.swordo.service;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.models.service.FighterServiceModel;
import com.example.swordo.views.FighterViewModel;

import java.util.List;

public interface FighterService {
    FighterServiceModel registerFighter(FighterServiceModel fighterServiceModel);

    FighterServiceModel findByUsernameAndPassword(String username, String password);

    void loginFighter(FighterServiceModel fighterServiceModel);

    FighterServiceModel registerAdmin(FighterServiceModel fighterServiceModel);

    List<FighterViewModel> getAllFighters();

    void editFighter(FighterServiceModel fighterServiceModel);

    void confirmLogout(CurrentFighter currentFighter);
}
