package com.menus.backend.controller.graphql;

import com.menus.backend.domain.dto.MenuItemDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.MenuItem;
import com.menus.backend.service.MenuItemService;
import com.menus.backend.service.MenuSectionService;
import com.menus.backend.util.EntityDtoMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class MenuItemGraphController {

    private final MenuItemService menuItemService;
    private final MenuSectionService menuSectionService;
    private final EntityDtoMapper<MenuItem, MenuItemDto> menuItemDtoEntityDtoMapper;

    public MenuItemGraphController(MenuItemService menuItemService, MenuSectionService menuSectionService, EntityDtoMapper<MenuItem, MenuItemDto> menuItemDtoEntityDtoMapper) {
        this.menuItemService = menuItemService;
        this.menuSectionService = menuSectionService;
        this.menuItemDtoEntityDtoMapper = menuItemDtoEntityDtoMapper;
    }

    @MutationMapping
    public MenuItem addMenuItemToMenuSection(
            @Argument String sectionId,
            @Argument MenuItemDto menuItemInput) {
        // Placeholder logic
        return menuItemService.addToSection(sectionId, menuItemInput);
    }

    @MutationMapping
    public MenuItem updateMenuItem(@Argument String itemId,
                                   @Argument MenuItemDto menuItemInput) {
        return menuItemService.update(itemId, menuItemInput);
    }

//    @MutationMapping
//    public Boolean deleteMenuItem(@Argument String itemId) {
//        return menuItemService.deleteItemFromSections(itemId);
//    }

    @MutationMapping
    public Boolean deleteMenuItemFromSection(@Argument String itemId) {
        return menuItemService.deleteItemFromSections(itemId);
    }

    @QueryMapping
    public List<MenuItem> menuItems() {
        return menuItemService.items();
    }

    @QueryMapping
    public MenuItem menuItemById(@Argument String id) {
        return menuItemService.itemById(id);
    }

    @QueryMapping
    public List<MenuItem> menuItemsFromSections(@Argument String sectionId) {
        return menuItemService.itemsFromSection(sectionId);
    }

    @QueryMapping
    public List<MenuItem> menuItemsByName(@Argument String name) {
        return menuItemService.itemsByName(name);
    }

    @QueryMapping
    public List<MenuItem> menuItemsByDescription(@Argument String description) {
        return menuItemService.itemsByDescription(description);
    }

    @QueryMapping
    public List<MenuItem> menuItemsByCategories(@Argument List<String> categories) {
        return menuItemService.itemsByCategories(categories);
    }

    @QueryMapping
    public List<MenuItem> menuItemsByTags(@Argument List<String> tags) {
        return menuItemService.itemsByTags(tags);
    }

    @QueryMapping
    public List<MenuItem> menuItemsByPriceRange(@Argument Double from,
                                                @Argument Double to) {
        return menuItemService.itemsByPriceRange(from, to);
    }
}