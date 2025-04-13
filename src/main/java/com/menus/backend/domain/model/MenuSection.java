package com.menus.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class MenuSection {

    @Id
    private String id;

    private String bannerImage;
    private String title;
    private String description;

    @DBRef
    private List<MenuItem> menuItems;

}
