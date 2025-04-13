package com.menus.backend.util.mappers;

import com.menus.backend.domain.dto.RestaurantDto;
import com.menus.backend.domain.model.Restaurant;
import com.menus.backend.util.EntityDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper extends EntityDtoMapper<Restaurant, RestaurantDto> {

    @Override
    public Restaurant fromDto(RestaurantDto dto) {
        return Restaurant.builder()
                .name(dto.getName())
                .lng(dto.getLng())
                .lat(dto.getLat())
                .level(dto.getLevel())
                .postalCode(dto.getPostalCode())
                .zipCode(dto.getZipCode())
                .build();
    }

    @Override
    public RestaurantDto toDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .name(restaurant.getName())
                .lat(restaurant.getLat())
                .level(restaurant.getLevel())
                .lng(restaurant.getLng())
                .zipCode(restaurant.getZipCode())
                .postalCode(restaurant.getPostalCode())
                .createdAt(restaurant.getCreatedAt())
                .updatedAt(restaurant.getUpdatedAt())
                .build();
    }


}
