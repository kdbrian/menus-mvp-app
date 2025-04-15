package com.menus.backend.util.mappers;

import com.menus.backend.domain.dto.MenuSectionDto;
import com.menus.backend.domain.model.MenuSection;
import com.menus.backend.util.EntityDtoMapper;
import com.menus.backend.util.ImageUrlValidator;
import org.springframework.stereotype.Component;

@Component
public class MenuSectionDtoMapper extends EntityDtoMapper<MenuSection, MenuSectionDto> {

    private final MenuItemDtoMapper menuItemMenuItemDtoEntityDtoMapper;

    public MenuSectionDtoMapper(MenuItemDtoMapper menuItemMenuItemDtoEntityDtoMapper) {
        this.menuItemMenuItemDtoEntityDtoMapper = menuItemMenuItemDtoEntityDtoMapper;
    }

    @Override
    public MenuSection fromDto(MenuSectionDto menuSectionDto) {
        return MenuSection.builder()
                .title(ImageUrlValidator.isValidImageUrl(menuSectionDto.getBannerImage()) ? menuSectionDto.getTitle() : null)
                .description(menuSectionDto.getDescription())
                .menuItems(menuSectionDto.getMenuItemDtos() == null || menuSectionDto.getMenuItemDtos().isEmpty() ? null : menuSectionDto.getMenuItemDtos().stream().map(menuItemMenuItemDtoEntityDtoMapper::fromDto).toList())
                .bannerImage(menuSectionDto.getBannerImage() == null || !ImageUrlValidator.isValidImageUrl(menuSectionDto.getBannerImage()) ? null : menuSectionDto.getBannerImage())
                .build();
    }

    @Override
    public MenuSectionDto toDto(MenuSection menuSection) {
        return MenuSectionDto.builder()
                .title(menuSection.getTitle())
                .description(menuSection.getDescription())
                .menuItemDtos(menuSection.getMenuItems().stream().map(menuItemMenuItemDtoEntityDtoMapper::toDto).toList())
                .bannerImage(menuSection.getBannerImage())
                .build();
    }
}
