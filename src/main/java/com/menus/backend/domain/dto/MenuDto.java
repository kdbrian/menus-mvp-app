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
public class MenuDto {
    private String restaurant;
    private String tagLine;
    private Float workingHoursFrom;
    private Float workingHoursTo;
    private List<MenuSectionDto> menuSectionDtos;
}
