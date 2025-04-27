package com.menus.backend.domain.dto;

import com.menus.backend.domain.model.MenuItem;
import com.menus.backend.util.mappers.ReservationDtoMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
    private String appUser;
    private List<MenuItem> items;
}
