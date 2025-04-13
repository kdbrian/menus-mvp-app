package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.dto.RestaurantDto;
import com.menus.backend.domain.model.Restaurant;
import com.menus.backend.domain.repo.RestaurantRepository;
import com.menus.backend.service.RestaurantService;
import com.menus.backend.util.EntityDtoMapper;
import com.menus.backend.util.mappers.RestaurantMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final EntityDtoMapper<Restaurant, RestaurantDto> restaurantDtoEntityMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, EntityDtoMapper<Restaurant, RestaurantDto> restaurantDtoEntityMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantDtoEntityMapper = restaurantDtoEntityMapper;
    }

    @Override
    public List<Restaurant> restaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant restaurantsById(String id) {
        if (!ObjectId.isValid(id))
            throw new IllegalArgumentException("Invalid ID format");

        return restaurantRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Restaurant> restaurantsByLevel(int level) {
        return restaurantRepository.findByLevel(level);
    }

    @Override
    public List<Restaurant> restaurantsByZipCode(String zipCode) {
        return restaurantRepository.findByZipCode(zipCode);
    }

    @Override
    public List<Restaurant> restaurantsByPostalCode(String postalCode) {
        return restaurantRepository.findByPostalCode(postalCode);
    }

    @Override
    public Restaurant registerRestaurant(RestaurantDto dto) {
        Restaurant restaurant = restaurantDtoEntityMapper.fromDto(dto);
        restaurant.setCreatedAt(System.currentTimeMillis());
        restaurant.setUpdatedAt(System.currentTimeMillis());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(String id, RestaurantDto dto) {
        if (!ObjectId.isValid(id))
            throw new RuntimeException("Invalid restaurant ID.");
        var restaurantToUpdate = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Missing restaurant with given ID."));

        restaurantToUpdate.setName(dto.getName());
        restaurantToUpdate.setZipCode(dto.getZipCode());
        restaurantToUpdate.setPostalCode(dto.getPostalCode());

        restaurantToUpdate.setUpdatedAt(System.currentTimeMillis());

        return restaurantRepository.save(restaurantToUpdate);
    }

    @Override
    public Boolean deleteRestaurant(String id) {
        var ret = restaurantRepository.existsById(id);
        restaurantRepository.deleteById(id);
        return ret;
    }
}
