package com.menus.backend.service;

import com.menus.backend.domain.dto.RestaurantDto;
import com.menus.backend.domain.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> restaurants();

    List<Restaurant> restaurantsByName(String name);
    Restaurant restaurantsById(String id);

    List<Restaurant> restaurantsByLevel(int level);

    List<Restaurant> restaurantsByZipCode(String zipCode);

    List<Restaurant> restaurantsByPostalCode(String postalCode);

    Restaurant registerRestaurant(RestaurantDto dto);

    Restaurant updateRestaurant(String id, RestaurantDto dto);

    Boolean deleteRestaurant(String id);

}
