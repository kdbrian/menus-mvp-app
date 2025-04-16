package com.menus.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String description;
    private Double price;
    private List<String> categories;
    private List<String> tags;
    private String imageUrl;

    @Builder.Default
    private Map<String, Object> meta = new HashMap<>();
    private Long createdAt;
    private Long updatedAt;


    public void addTag(String tag) {
        if (this.tags == null)
            this.tags = new ArrayList<>();
        if (!this.tags.contains(tag))
            this.tags.add(tag);
    }

    public void addTags(List<String> tag) {
        if (this.tags == null)
            this.tags = new ArrayList<>();
        this.tags.addAll(tag.stream().filter(this.tags::contains).toList());
    }


    public void addCategory(String category) {
        if (this.categories == null)
            this.categories = new ArrayList<>();
        if (!this.categories.contains(category))
            this.categories.add(category);
    }

    public void addCategories(List<String> categories) {
        if (this.categories == null)
            this.categories = new ArrayList<>();
        this.categories.addAll(categories.stream().filter(this.categories::contains).toList());
    }

}
