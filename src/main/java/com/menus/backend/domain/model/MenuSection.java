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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuSection {

    @Id
    private String id;
    private String title;
    private String description;
    private String bannerImage;

    @DBRef
    private List<MenuItem> menuItems;

    public void addMenuItem(MenuItem menuItem) {
        if (this.menuItems == null)
            this.menuItems = new ArrayList<>();
        menuItems.add(menuItem);
    }
    public void addMenuItems(List<MenuItem> menuItems) {
        if (this.menuItems == null)
            this.menuItems = new ArrayList<>();
        this.menuItems.addAll(menuItems);
    }
}