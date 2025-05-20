package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.dto.RestaurantDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.Restaurant;
import com.menus.backend.domain.repo.RestaurantRepository;
import com.menus.backend.service.RestaurantService;
import com.menus.backend.util.EntityDtoMapper;
import com.menus.backend.util.errors.EntityNotFoundError;
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
    public List<Restaurant> restaurantsByName(String name) {
        return restaurantRepository.findByNameContainingIgnoringCase(name);
    }

    @Override
    public Restaurant restaurantsById(String id) {
        if (!ObjectId.isValid(id))
            throw new IllegalArgumentException("Invalid ID format");

        return restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(id, RestaurantService.class.getName()));
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

        if (dto.getName() != null && !dto.getName().equals(restaurantToUpdate.getName()))
            restaurantToUpdate.setName(dto.getName());

        if (dto.getZipCode() != null && !dto.getZipCode().equals(restaurantToUpdate.getZipCode()))
            restaurantToUpdate.setZipCode(dto.getZipCode());

        if (dto.getPostalCode() != null && !dto.getPostalCode().equals(restaurantToUpdate.getPostalCode()))
            restaurantToUpdate.setPostalCode(dto.getPostalCode());

        if (dto.getLat() != 0.0 && dto.getLat() != (restaurantToUpdate.getLat()))
            restaurantToUpdate.setLat(dto.getLat());

        if (dto.getLng() != 0.0 && dto.getLng() != (restaurantToUpdate.getLng()))
            restaurantToUpdate.setLng(dto.getLng());

        if (dto.getBannerImage() != null && !dto.getBannerImage().equals(restaurantToUpdate.getBannerImage()))
            restaurantToUpdate.setBannerImage(dto.getBannerImage());

        if (dto.getTagLine() != null && !dto.getTagLine().equals(restaurantToUpdate.getTagLine()))
            restaurantToUpdate.setTagLine(dto.getTagLine());

        if (dto.getLocationName() != null && !dto.getLocationName().equals(restaurantToUpdate.getLocationName()))
            restaurantToUpdate.setLocationName(dto.getLocationName());

        restaurantToUpdate.setUpdatedAt(System.currentTimeMillis());

        return restaurantRepository.save(restaurantToUpdate);
    }

    @Override
    public Boolean deleteRestaurant(String id) {
        var ret = restaurantRepository.existsById(id);
        restaurantRepository.deleteById(id);
        return ret;
    }

    @Override
    public Restaurant thumbUpRestaurant(String restaurantId) {
        if (!restaurantRepository.existsById(restaurantId) || !ObjectId.isValid(restaurantId))
            throw new RuntimeException("Invalid menu Id");

        Restaurant toUpdate = restaurantRepository.findById(restaurantId).orElseThrow();


        Long toUpdateThumbsUp = toUpdate.getThumbsUp();
        if (toUpdateThumbsUp != null)
            toUpdate.setThumbsUp(toUpdateThumbsUp + 1);
        else
            toUpdate.setThumbsUp(1L);

        return restaurantRepository.save(toUpdate);
    }

    @Override
    public Restaurant thumbDownRestaurant(String restaurantId) {
        if (!restaurantRepository.existsById(restaurantId) || !ObjectId.isValid(restaurantId))
            throw new RuntimeException("Invalid restaurant Id");

        Restaurant toUpdate = restaurantRepository.findById(restaurantId).orElseThrow();


        Long toUpdateThumbsDown = toUpdate.getThumbsDown();
        if (toUpdateThumbsDown != null)
            toUpdate.setThumbsDown(toUpdateThumbsDown + 1);
        else
            toUpdate.setThumbsDown(1L);

        return restaurantRepository.save(toUpdate);
    }
}
