package com.menus.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private Float price;
    private List<String> categories;
    private List<String> tags;
    private String imageUrl;

    private Long createdAt;
    private Long updatedAt;

}
