package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.dto.MenuDto;
import com.menus.backend.domain.dto.MenuSectionDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.MenuSection;
import com.menus.backend.domain.model.Restaurant;
import com.menus.backend.domain.repo.MenuRepository;
import com.menus.backend.domain.repo.MenuSectionRepository;
import com.menus.backend.domain.repo.RestaurantRepository;
import com.menus.backend.service.MenuService;
import com.menus.backend.util.EntityDtoMapper;
import com.menus.backend.util.mappers.MenuDtoMapper;
import com.menus.backend.util.mappers.MenuSectionDtoMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuSectionRepository menuSectionRepository;

    private final MenuDtoMapper menuDtoMapper;
    private final MenuSectionDtoMapper menuSectionDtoEntityDtoMapper;
    private final RestaurantRepository restaurantRepository;

    public MenuServiceImpl(MenuRepository menuRepository,
                           MenuSectionRepository menuSectionRepository,
                           MenuDtoMapper menuDtoMapper,
                           MenuSectionDtoMapper menuSectionDtoEntityDtoMapper,
                           RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.menuSectionRepository = menuSectionRepository;
        this.menuDtoMapper = menuDtoMapper;
        this.menuSectionDtoEntityDtoMapper = menuSectionDtoEntityDtoMapper;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public List<Menu> menus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu menuById(String id) {
        if (!ObjectId.isValid(id))
            throw new RuntimeException("Invalid ID");
        return menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public List<Menu> menuByRestaurant(String id) {
        var restaurant = restaurantRepository.findById(id).orElseThrow();
        return menuRepository.findAll().stream().filter(menu -> menu.getRestaurant().equals(restaurant)).toList();
    }
    
    @Override
    public List<Menu> menuByRestaurantName(String restaurantName) {
        return menuRepository.findByRestaurant_Name(restaurantName);
    }

    @Override
    public List<Menu> menuByTagLine(String tagLine) {
        return menuRepository.findByTagLine(tagLine);
    }

    @Override
    public List<Menu> menuByName(String name) {
        return menuRepository.findByName(name);
    }

    @Override
    public Menu registerMenu(MenuDto menuDto) {
        var menu = menuDtoMapper.fromDto(menuDto);

        Restaurant restaurant = restaurantRepository.findById(menuDto.getRestaurant()).orElseThrow(() -> new RuntimeException("Restaurant Not found"));

        menu.setRestaurant(restaurant);


        if (menuDto.getMenuSectionDtos() != null && !menuDto.getMenuSectionDtos().isEmpty()) {
            List<MenuSection> menuSections = menuDto.getMenuSectionDtos()
                    .stream()
                    .map(menuSectionDtoEntityDtoMapper::fromDto)
                    .map(menuSectionRepository::save)
                    .toList();
            menu.setMenuSections(menuSections);
        }

        menu.setCreatedAt(System.currentTimeMillis());
        menu.setUpdatedAt(System.currentTimeMillis());

        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(String menuId, MenuDto menuDto) {
        if (!menuRepository.existsById(menuId) || !ObjectId.isValid(menuId))
            throw new RuntimeException("Invalid menu Id");

        Menu toUpdate = menuRepository.findById(menuId).orElseThrow();

        if (menuDto.getWorkingHoursFrom() != null)
            toUpdate.setWorkingHoursFrom(menuDto.getWorkingHoursFrom());

        if (menuDto.getName() != null)
            toUpdate.setName(menuDto.getName());

        if (menuDto.getWorkingHoursTo() != null)
            toUpdate.setWorkingHoursTo(menuDto.getWorkingHoursTo());

        toUpdate.setUpdatedAt(System.currentTimeMillis());

        return menuRepository.save(toUpdate);
    }
}
