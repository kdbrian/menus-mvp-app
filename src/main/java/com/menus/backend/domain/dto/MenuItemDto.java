package com.menus.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemDto {
    private String name;
    private String description;
    private Float price;
    private List<String> category;
    private List<String> tags;
    private String imageUrl;
}
