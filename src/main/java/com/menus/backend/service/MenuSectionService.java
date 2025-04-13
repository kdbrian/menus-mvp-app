package com.menus.backend.service;

import com.menus.backend.domain.dto.MenuSectionDto;
import com.menus.backend.domain.model.MenuSection;

public interface MenuSectionService {
    MenuSection addSectionToMenu(String menuId, MenuSectionDto menuSectionDto);
    MenuSection updateMenuSection(String sectionId, MenuSectionDto menuSectionDto);
    Boolean deleteMenuSection(String sectionId);
}
