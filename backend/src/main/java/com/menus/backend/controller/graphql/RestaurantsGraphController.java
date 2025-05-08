package com.menus.backend.controller.graphql;

import com.menus.backend.domain.dto.RestaurantDto;
import com.menus.backend.domain.model.Restaurant;
import com.menus.backend.service.RestaurantService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RestaurantsGraphController {

    private final RestaurantService restaurantService;

    public RestaurantsGraphController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @QueryMapping
    public List<Restaurant> restaurants() {
        return restaurantService.restaurants();
    }

    @QueryMapping
    public Restaurant restaurantById(@Argument String id) {
        return restaurantService.restaurantsById(id);
    }

    @QueryMapping
    public List<Restaurant> restaurantByName(@Argument String name) {
        return restaurantService.restaurantsByName(name);
    }

    @QueryMapping
    public List<Restaurant> restaurantByLevel(@Argument int level) {
        return restaurantService.restaurantsByLevel(level);
    }

    @QueryMapping
    public List<Restaurant> restaurantByZipCode(@Argument String zipCode) {
        return restaurantService.restaurantsByZipCode(zipCode);
    }

    @QueryMapping
    public List<Restaurant> restaurantByPostalCode(@Argument String postalCode) {
        return restaurantService.restaurantsByPostalCode(postalCode);
    }

    @MutationMapping
    public Restaurant registerRestaurant(@Argument("dto") RestaurantDto dto) {
        return restaurantService.registerRestaurant(dto);
    }

    @MutationMapping
    public Restaurant updateRestaurant(@Argument String id, @Argument("dto") RestaurantDto dto) {
        return restaurantService.updateRestaurant(id, dto);
    }

    @MutationMapping
    public Boolean deleteRestaurant(@Argument String id) {
        return restaurantService.deleteRestaurant(id);
    }


    @MutationMapping
    public Boolean thumbUpRestaurant(@Argument String restaurantId) {
        return restaurantService.thumbUpRestaurant(restaurantId) != null;
    }

    @MutationMapping
    public Boolean thumbDownRestaurant(@Argument String restaurantId) {
        return restaurantService.thumbDownRestaurant(restaurantId) != null;
    }


}
