package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.dto.MenuItemDto;
import com.menus.backend.domain.dto.MenuSectionDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.MenuItem;
import com.menus.backend.domain.model.MenuSection;
import com.menus.backend.domain.repo.MenuItemRepository;
import com.menus.backend.domain.repo.MenuRepository;
import com.menus.backend.domain.repo.MenuSectionRepository;
import com.menus.backend.service.MenuSectionService;
import com.menus.backend.util.EntityDtoMapper;
import com.menus.backend.util.ImageUrlValidator;
import com.menus.backend.util.errors.EntityNotFoundError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuSectionServiceImpl implements MenuSectionService {

    private final MenuSectionRepository menuSectionRepository;
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final EntityDtoMapper<MenuSection, MenuSectionDto> menuSectionDtoEntityDtoMapper;
    private final EntityDtoMapper<MenuItem, MenuItemDto> menuItemDtoEntityDtoMapper;

    @Override
    public List<MenuSection> sectionsMenu() {
        return menuSectionRepository.findAll();
    }

    @Override
    public MenuSection sectionById(String id) {
        return menuSectionRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(id, MenuSection.class.getName()));
    }

    @Override
    public List<MenuSection> sectionsMenuByMenu(String menuId) {
        var menu = menuRepository.findById(menuId).orElseThrow(() -> new EntityNotFoundError(menuId, Menu.class.getName()));
        return menu.getMenuSections();
    }

    @Override
    public MenuSection addSectionToMenu(String menuId, MenuSectionDto menuSectionDto) {

        if (!ObjectId.isValid(menuId)) {
            throw new RuntimeException("Invalid ID");
        }
        var menu = menuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Invalid Menu"));

        MenuSection menuSection = menuSectionRepository.save(menuSectionDtoEntityDtoMapper.fromDto(menuSectionDto));
        menu.addSection(menuSection);
        menu.setUpdatedAt(System.currentTimeMillis());
        menuRepository.save(menu);
        return menuSection;
    }

    @Override
    public MenuSection updateMenuSection(String sectionId, MenuSectionDto menuSectionDto) {
        if (!ObjectId.isValid(sectionId)) {
            throw new RuntimeException("Invalid ID");
        }
        var section = menuSectionRepository.findById(sectionId).orElseThrow(() -> new RuntimeException("Invalid Section"));

        if (menuSectionDto.getMenuItemDtos() != null && !menuSectionDto.getMenuItemDtos().isEmpty()) {
            //save all menu items and add to section
            section.addMenuItems(menuSectionDto.getMenuItemDtos().stream().map(menuItemDtoEntityDtoMapper::fromDto).map(menuItemRepository::save).toList());
        }

        if (menuSectionDto.getTitle() != null && !menuSectionDto.getTitle().isEmpty()) {
            section.setTitle(menuSectionDto.getTitle());
        }

        if (menuSectionDto.getDescription() != null) {
            section.setDescription(menuSectionDto.getDescription());
        }

        if (menuSectionDto.getDescription() != null) {
            section.setDescription(menuSectionDto.getDescription());
        }

        log.info("matches {} {}", ImageUrlValidator.isValidImageUrl(menuSectionDto.getBannerImage()), menuSectionDto.getBannerImage());
        if (menuSectionDto.getBannerImage() != null) {
            section.setBannerImage(menuSectionDto.getBannerImage());
        }

        return menuSectionRepository.save(section);
    }

    @Override
    public Boolean deleteMenuSection(String sectionId) {
        return null;
    }

}
