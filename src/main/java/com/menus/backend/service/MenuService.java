package com.menus.backend.service;

import com.menus.backend.domain.dto.MenuDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.Restaurant;

import java.util.List;

public interface MenuService {
    List<Menu> menus();
    Menu menuById(String id);
    List<Menu> menuByRestaurant(String id);
    List<Menu> menuByRestaurantName(String restaurantName);
    List<Menu> menuByTagLine(String tagLine);
    List<Menu> menuByName(String name);
    Menu registerMenu(MenuDto menuDto);
    Menu updateMenu(String menuId,MenuDto menuDto);
}
