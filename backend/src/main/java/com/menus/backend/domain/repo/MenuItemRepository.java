package com.menus.backend.domain.repo;

import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<MenuItem> findByNameContainingIgnoringCase(String name);

    @Query("{ 'description': { $regex: ?0, $options: 'i' } }")
    List<MenuItem> findByDescriptionContainingIgnoringCase(String description);

    @Query("{'price' : { $gte : ?0, $lte : ?1}}")
    List<MenuItem> findByPriceInRange(Double from, Double to);

    List<MenuItem> findByTagsIn(List<String> tags);

    List<MenuItem> findByCategoriesIn(List<String> categories);

}
