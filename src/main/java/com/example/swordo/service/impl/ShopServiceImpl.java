package com.example.swordo.service.impl;

import com.example.swordo.current.CurrentFighter;
import com.example.swordo.models.entities.Shop;
import com.example.swordo.repository.ShopRepository;
import com.example.swordo.repository.SwordRepository;
import com.example.swordo.service.ShopService;
import com.example.swordo.views.ShopViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final SwordRepository swordRepository;
    private final CurrentFighter currentFighter;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, SwordRepository swordRepository, CurrentFighter currentFighter) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.swordRepository = swordRepository;
        this.currentFighter = currentFighter;
    }

    @Override
    public List<ShopViewModel> getAllShops() {
        return shopRepository.findAll()
                .stream().map(shop -> modelMapper.map(shop, ShopViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void initShop() {
        if (shopRepository.count() != 0) {
            return;
        }
        swordRepository.findAll()
                .forEach(sword -> {
                    for (int i = 0; i < 2; i++) {
                        Shop shop = new Shop();
                        shop.setSword(sword);
                        shopRepository.save(shop);
                    }
                });

    }

    @Override
    public void sellSword(Long id) {
        shopRepository.findById(id).ifPresent(shop -> {
            if (currentFighter.getCoins() >= shop.getSword().getPrice()) {
                currentFighter.setSword(shop.getSword());
                currentFighter.setCoins(currentFighter.getCoins() - shop.getSword().getPrice());
                currentFighter.setStrikesLeft(shop.getSword().getDurability());
                shopRepository.deleteById(id);
            }
        });
    }
}
