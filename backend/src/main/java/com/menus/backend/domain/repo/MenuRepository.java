package com.menus.backend.domain.repo;

import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends MongoRepository<Menu, String> {
    List<Menu> findByRestaurant_Name(String name);
    List<Menu> findByTagLine(String tagLine);
    List<Menu> findByName(String name);
    List<Menu> findByRestaurant(Restaurant restaurant);
}
