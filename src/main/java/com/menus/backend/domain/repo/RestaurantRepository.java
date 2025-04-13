package com.menus.backend.domain.repo;

import com.menus.backend.domain.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByLevel(int level);
    List<Restaurant> findByZipCode(String zipCode);
    List<Restaurant> findByPostalCode(String postalCode);
}
