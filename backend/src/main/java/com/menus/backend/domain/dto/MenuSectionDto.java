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
public class MenuSectionDto {
    private String title;
    private String description;
    private String bannerImage;
    private List<MenuItemDto> menuItemDtos;
}
