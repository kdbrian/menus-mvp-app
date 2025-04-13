package com.menus.backend.service;

import com.menus.backend.domain.dto.MenuItemDto;
import com.menus.backend.domain.model.MenuItem;

public interface MenuItemService {
    MenuItem addMenuItemToMenuSection(String sectionId, MenuItemDto menuItemDto);
    Boolean deleteMenuItemFromSection(String sectionId, MenuItemDto menuItemDto);
}
