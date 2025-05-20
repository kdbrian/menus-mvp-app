package com.menus.backend.util.errors;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@NoArgsConstructor
@Slf4j
public class EntityNotFoundError extends RuntimeException {
    private String itemId, callingClass;

    public EntityNotFoundError(String itemId, String callingClass) {
        super(String.format("Entity %s Not found.", itemId));
        this.itemId = itemId;
        this.callingClass = callingClass;//resolve to a class
        log.info("calling {}", callingClass); //log internally
    }
}
