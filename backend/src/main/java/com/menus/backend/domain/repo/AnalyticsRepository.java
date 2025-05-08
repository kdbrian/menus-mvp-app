package com.menus.backend.domain.repo;

import com.menus.backend.domain.enums.AnalyticEventKey;
import com.menus.backend.domain.enums.AnalyticEventPriority;
import com.menus.backend.domain.model.AnalyticEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyticsRepository extends MongoRepository<AnalyticEvent, String> {

//    @Query("find : {}")
//    List<AnalyticEvent> findByAnalyticEventKey(AnalyticEventKey key);
//
//    List<AnalyticEvent> findByAnalyticEventPriority(AnalyticEventPriority priority);
//
//    List<AnalyticEvent> findByLogTimeBetween(Long start, Long end);
}
