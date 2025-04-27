package com.menus.backend.domain.repo;

import com.menus.backend.domain.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    List<AppUser> findByEmail(String email);

    List<AppUser> findByPhone(String phone);

    @Query("{{dateJoined : {$lte : ?1, $gte: ?0}}}")
//     start<=data<=end
    List<AppUser> findByDatesInRange(Long start, Long end);

    List<AppUser> findByNameIgnoringCase(String name);
}
