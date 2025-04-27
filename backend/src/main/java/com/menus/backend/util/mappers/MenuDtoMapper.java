package com.menus.backend.util.mappers;

import com.menus.backend.domain.dto.MenuDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.util.EntityDtoMapper;
import com.menus.backend.util.ImageUrlValidator;
import org.springframework.stereotype.Component;


@Component
public class MenuDtoMapper extends EntityDtoMapper<Menu, MenuDto> {

    private final MenuSectionDtoMapper menuSectionDtoMapper;

    public MenuDtoMapper(MenuSectionDtoMapper menuSectionDtoMapper) {
        this.menuSectionDtoMapper = menuSectionDtoMapper;
    }

    @Override
    public Menu fromDto(MenuDto menuDto) {
        return Menu.builder()
                .tagLine(menuDto.getTagLine())
                .workingHoursFrom(menuDto.getWorkingHoursFrom())
                .bannerImage(ImageUrlValidator.isValidImageUrl(menuDto.getBannerImage()) ? menuDto.getBannerImage() : null)
                .workingHoursTo(menuDto.getWorkingHoursTo())
                .build();
    }

    @Override
    public MenuDto toDto(Menu menu) {
        return MenuDto.builder()
                .tagLine(menu.getTagLine())
                .workingHoursFrom(menu.getWorkingHoursFrom())
                .workingHoursTo(menu.getWorkingHoursTo())
                .restaurant(menu.getRestaurant().getId())
                .menuSectionDtos(menu.getMenuSections().stream().map(menuSectionDtoMapper::toDto).toList())
                .build();
    }
}
