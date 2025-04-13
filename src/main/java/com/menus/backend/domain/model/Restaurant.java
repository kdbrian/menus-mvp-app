package com.menus.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private int level;
    private float lat;
    private float lng;
    private String zipCode;
    private String postalCode;
    private Long createdAt;
    private Long updatedAt;
    private transient boolean is_verified;
}
