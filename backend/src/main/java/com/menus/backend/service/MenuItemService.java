package com.menus.backend.service;

import com.menus.backend.domain.dto.MenuItemDto;
import com.menus.backend.domain.model.MenuItem;

import java.util.List;

public interface MenuItemService {

    List<MenuItem> items();

    MenuItem itemById(String id);

    List<MenuItem> itemsFromSection(String sectionId);

    List<MenuItem> itemsByName(String name);

    List<MenuItem> itemsByDescription(String description);

    List<MenuItem> itemsByCategories(List<String> categories);

    List<MenuItem> itemsByTags(List<String> tags);

    List<MenuItem> itemsByPriceRange(Double from, Double to);

    MenuItem addToSection(String sectionId, MenuItemDto menuItemDto);

    MenuItem update(String itemId, MenuItemDto menuItemDto);

    Boolean deleteItemFromSections(String itemId);

}
