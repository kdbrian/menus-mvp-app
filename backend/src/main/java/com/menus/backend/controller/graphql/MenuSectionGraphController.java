package com.menus.backend.controller.graphql;

import com.menus.backend.domain.dto.MenuSectionDto;
import com.menus.backend.domain.model.MenuSection;
import com.menus.backend.service.MenuSectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class MenuSectionGraphController {

    private final MenuSectionService menuSectionService;

    public MenuSectionGraphController(MenuSectionService menuSectionService) {
        this.menuSectionService = menuSectionService;
    }


    @QueryMapping
    public List<MenuSection> sectionsMenu() {
        return menuSectionService.sectionsMenu();
    }


    @QueryMapping
    public MenuSection sectionsById(@Argument String id) {
        return menuSectionService.sectionById(id);
    }

    @QueryMapping
    public List<MenuSection> sectionsByMenuId(@Argument String menuId) {
        return menuSectionService.sectionsMenuByMenu(menuId);
    }

    @MutationMapping
    public MenuSection addSectionToMenu(@Argument String menuId, @Argument MenuSectionDto section) {
        log.info("section dto : {}", section);
        return menuSectionService.addSectionToMenu(menuId, section);
    }

    @MutationMapping
    public MenuSection updateMenuSection(@Argument String sectionId, @Argument MenuSectionDto menuSectionInput) {
        return menuSectionService.updateMenuSection(sectionId, menuSectionInput);
    }

    @MutationMapping
    public Boolean deleteMenuSection(@Argument String sectionId) {
        return menuSectionService.deleteMenuSection(sectionId);
    }

}
