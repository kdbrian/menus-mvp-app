package com.menus.backend.service;

import com.menus.backend.domain.dto.MenuSectionDto;
import com.menus.backend.domain.model.MenuSection;

import java.util.List;

public interface MenuSectionService {
    List<MenuSection> sectionsMenu();
    MenuSection sectionById(String id);
    List<MenuSection> sectionsMenuByMenu(String menuId);
    MenuSection addSectionToMenu(String menuId, MenuSectionDto menuSectionDto);
    MenuSection updateMenuSection(String sectionId, MenuSectionDto menuSectionDto);
    Boolean deleteMenuSection(String sectionId);
}
