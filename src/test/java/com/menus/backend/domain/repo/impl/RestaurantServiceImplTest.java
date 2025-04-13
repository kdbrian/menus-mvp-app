package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.repo.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceImplTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void restaurants() {
        assertEquals(0, restaurantRepository.findAll().size());
    }

    @Test
    void restaurantsById() {
    }

    @Test
    void restaurantsByLevel() {
    }

    @Test
    void restaurantsByZipCode() {
    }

    @Test
    void restaurantsByPostalCode() {
    }

    @Test
    void registerRestaurant() {
    }

    @Test
    void updateRestaurant() {
    }

    @Test
    void deleteRestaurant() {
    }
}