package com.menus.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    private String id;

    @DBRef
    private Restaurant restaurant;

    private String name;
    private String tagLine;
    private String bannerImage;
    private Long workingHoursFrom;
    private Long workingHoursTo;
    private Long createdAt;
    private Long updatedAt;

    @DBRef
    private List<MenuSection> menuSections;


    public void addSection(MenuSection menuSection) {
        if (this.getMenuSections() == null)
            this.setMenuSections(new ArrayList<>());

        this.getMenuSections().add(menuSection);
    }

}
