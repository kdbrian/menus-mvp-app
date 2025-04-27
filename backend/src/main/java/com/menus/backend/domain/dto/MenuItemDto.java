package com.menus.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemDto {
    private String name;
    private String description;
    private Double price;
    private List<String> category;
    private List<String> tags;
    private String imageUrl;
    @Builder.Default
    private Map<String, Object> meta = new HashMap<>();
}
