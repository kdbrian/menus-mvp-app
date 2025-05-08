package com.menus.backend.domain.model;

import com.menus.backend.domain.enums.AnalyticEventKey;
import com.menus.backend.domain.enums.AnalyticEventPriority;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record AnalyticEvent(
        @Id String id,
        AnalyticEventKey key,
        AnalyticEventPriority priority,
        String info,
        Long logTime
) {

}
