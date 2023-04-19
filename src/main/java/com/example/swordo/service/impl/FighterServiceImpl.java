package com.example.swordo.service.impl;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.models.entities.Fighter;
import com.example.swordo.models.entities.FighterRoleEnum;
import com.example.swordo.models.service.FighterServiceModel;
import com.example.swordo.repository.FighterRepository;
import com.example.swordo.service.FighterService;
import com.example.swordo.service.SwordService;
import com.example.swordo.views.FighterFightView;
import com.example.swordo.views.FighterViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FighterServiceImpl implements FighterService {
    private final FighterRepository fighterRepository;
    private final ModelMapper modelMapper;
    private final CurrentFighter currentFighter;
    private final SwordService swordService;

    public FighterServiceImpl(FighterRepository fighterRepository, ModelMapper modelMapper, CurrentFighter currentFighter, SwordService swordService) {
        this.fighterRepository = fighterRepository;
        this.modelMapper = modelMapper;
        this.currentFighter = currentFighter;
        this.swordService = swordService;
    }


    @Override
    public void registerFighter(FighterServiceModel fighterServiceModel)
    {
        Fighter fighter = modelMapper.map(fighterServiceModel, Fighter.class);
        fighter.setRole(FighterRoleEnum.USER);
        fighter.setHitpoints(1000);
        fighter.setCoins(0);
        fighter.setSword(swordService.getRegular());
        fighter.setSwordDurability(fighter.getSword().getDurability());
        fighterRepository.save(fighter);
    }

    @Override
    public FighterServiceModel findByUsernameAndPassword(String username, String password) {
        return fighterRepository.findByUsernameAndPassword(username,password)
                .map(fighter -> modelMapper.map(fighter, FighterServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginFighter(FighterServiceModel fighterServiceModel) {
        currentFighter.setBackstory(fighterServiceModel.getBackstory());
        currentFighter.setId(fighterServiceModel.getId());
        currentFighter.setEmail(fighterServiceModel.getEmail());
        currentFighter.setUsername(fighterServiceModel.getUsername());
        currentFighter.setFirstName(fighterServiceModel.getFirstName());
        currentFighter.setHitpoints(fighterServiceModel.getHitpoints());
        currentFighter.setRole(fighterServiceModel.getRole());
        currentFighter.setLastName(fighterServiceModel.getLastName());
        currentFighter.setCoins(fighterServiceModel.getCoins());
        currentFighter.setPassword(fighterServiceModel.getPassword());
        currentFighter.setSwordDurability(fighterServiceModel.getSwordDurability());
    }

    @Override
    public void registerAdmin(FighterServiceModel fighterServiceModel) {
        Fighter fighter = modelMapper.map(fighterServiceModel, Fighter.class);
        fighter.setRole(FighterRoleEnum.ADMIN);
        fighter.setHitpoints(Integer.MAX_VALUE);
        fighter.setSwordDurability(0);
        fighter.setCoins(9999999);
        fighterRepository.save(fighter);
    }

    @Override
    public List<FighterViewModel> getAllFighters() {
        return fighterRepository.findAllByRole(FighterRoleEnum.USER)
                .stream().map(fighter -> modelMapper.map(fighter,FighterViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editFighter(FighterServiceModel fighterServiceModel) {
        currentFighter.setBackstory(fighterServiceModel.getBackstory());
        currentFighter.setEmail(fighterServiceModel.getEmail());
        currentFighter.setUsername(fighterServiceModel.getUsername());
    }


    @Override
    public void updateCurrentFighter() {
        fighterRepository.save(modelMapper.map(currentFighter, Fighter.class));
    }

    @Override
    public void healCurrentFighter() {
        if(currentFighter.getCoins() >= 50){
            currentFighter.setHitpoints(currentFighter.getHitpoints() + 100);
            if (currentFighter.getHitpoints() > 1000){
                currentFighter.setHitpoints(1000);
            }
        }
    }

    @Override
    public Fighter findCurrent() {
        return fighterRepository.findById(currentFighter.getId()).orElse(null);
    }
}
