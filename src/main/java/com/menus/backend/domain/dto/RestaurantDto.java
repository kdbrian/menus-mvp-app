package com.menus.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    private String name;
    private int level;
    private float lat;
    private float lng;
    private String zipCode;
    private String postalCode;
    private Long createdAt;
    private Long updatedAt;

}
