package com.menus.backend.util.mappers;

import com.menus.backend.domain.dto.MenuItemDto;
import com.menus.backend.domain.dto.MenuSectionDto;
import com.menus.backend.domain.model.MenuItem;
import com.menus.backend.domain.model.MenuSection;
import com.menus.backend.util.EntityDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class MenuSectionDtoMapper extends EntityDtoMapper<MenuSection, MenuSectionDto> {

    private final EntityDtoMapper<MenuItem, MenuItemDto> menuItemMenuItemDtoEntityDtoMapper;

    public MenuSectionDtoMapper(EntityDtoMapper<MenuItem, MenuItemDto> menuItemMenuItemDtoEntityDtoMapper) {
        this.menuItemMenuItemDtoEntityDtoMapper = menuItemMenuItemDtoEntityDtoMapper;
    }

    @Override
    public MenuSection fromDto(MenuSectionDto menuSectionDto) {
        return MenuSection.builder()
                .title(menuSectionDto.getTitle())
                .description(menuSectionDto.getDescription())
                .menuItems(menuSectionDto.getMenuItemDtos().stream().map(menuItemMenuItemDtoEntityDtoMapper::fromDto).toList())
                .build();
    }

    @Override
    public MenuSectionDto toDto(MenuSection menuSection) {
        return MenuSectionDto.builder()
                .title(menuSection.getTitle())
                .description(menuSection.getDescription())
                .menuItemDtos(menuSection.getMenuItems().stream().map(menuItemMenuItemDtoEntityDtoMapper::toDto).toList())
                .build();
    }
}
