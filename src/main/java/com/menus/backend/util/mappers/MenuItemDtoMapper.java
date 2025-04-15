package com.menus.backend.util.mappers;

import com.menus.backend.domain.dto.MenuItemDto;
import com.menus.backend.domain.model.MenuItem;
import com.menus.backend.util.EntityDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class MenuItemDtoMapper extends EntityDtoMapper<MenuItem, MenuItemDto> {

    @Override
    public MenuItem fromDto(MenuItemDto menuItemDto) {
        return MenuItem.builder()
                .categories(menuItemDto.getCategory())
                .tags(menuItemDto.getTags())
                .name(menuItemDto.getName())
                .description(menuItemDto.getDescription())
                .imageUrl(menuItemDto.getImageUrl())
                .price(menuItemDto.getPrice())
                .build();
    }

    @Override
    public MenuItemDto toDto(MenuItem menuItem) {
        return MenuItemDto.builder()
                .category(menuItem.getCategories())
                .tags(menuItem.getTags())
                .description(menuItem.getDescription())
                .imageUrl(menuItem.getImageUrl())
                .price(menuItem.getPrice())
                .name(menuItem.getName())
                .build();
    }


}
