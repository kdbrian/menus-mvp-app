package com.menus.backend.controller.graphql;

import com.menus.backend.domain.dto.MenuDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.service.MenuService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MenuGraphQLController {
    private final MenuService menuService;

    public MenuGraphQLController(MenuService menuService) {
        this.menuService = menuService;
    }


    // Query: [Menu]
    @QueryMapping
    public List<Menu> menus() {
        return menuService.menus();
    }

    // Query: menuById(id : ID!) : Menu
    @QueryMapping
    public Menu menuById(@Argument String id) {
        return menuService.menuById(id);
    }

    // Query: menuByRestaurantName(name : String!) : [Menu]
    @QueryMapping
    public List<Menu> menuByRestaurantName(@Argument String name) {
        return menuService.menuByRestaurantName(name);
    }

    // Query: menuByTagline(tagLine : String!) : [Menu]
    @QueryMapping
    public List<Menu> menuByTagline(@Argument String tagLine) {
        return menuService.menuByTagLine(tagLine);
    }

    // Mutation: addMenu(dto : MenuInput!) : Menu
    @MutationMapping
    public Menu addMenu(@Argument MenuDto dto) {
        return menuService.registerMenu(dto);
    }

    // Mutation: updateMenu(dto : MenuInput!) : Menu
    @MutationMapping
    public Menu updateMenu(@Argument String id,@Argument MenuDto dto) {
        return menuService.updateMenu(id,dto);
    }


}
