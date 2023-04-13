package com.example.swordo.service.impl;

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

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, SwordRepository swordRepository) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.swordRepository = swordRepository;
    }

    @Override
    public List<ShopViewModel> getAllShops() {
        return shopRepository.findAll()
                .stream().map(shop -> modelMapper.map(shop, ShopViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void initShop() {
        if (shopRepository.count() != 0){
            return;
        }
        swordRepository.findAll()
                .forEach(sword -> {
                    for (int i = 0; i < 2;i++) {
                        Shop shop = new Shop();
                        shop.setSword(sword);
                        shopRepository.save(shop);
                    }
                });

    }
}
