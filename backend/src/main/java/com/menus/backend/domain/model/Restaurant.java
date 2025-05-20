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

    private String bannerImage;

    //TODO: Add validation 0-5
    private int level; // not that important
    private float lat;
    private float lng;
    private String locationName;
    private String tagLine;
    private String zipCode;
    private String postalCode;
    private Long createdAt;
    private Long updatedAt;
    @Builder.Default
    private Long thumbsUp = 0L;

    @Builder.Default
    private Long thumbsDown = 0L;
    private transient boolean is_verified;
}
