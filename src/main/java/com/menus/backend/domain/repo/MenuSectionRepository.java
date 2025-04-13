package com.menus.backend.domain.repo;

import com.menus.backend.domain.model.MenuSection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuSectionRepository extends MongoRepository<MenuSection, String> {
}
