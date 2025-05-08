package com.menus.backend.domain.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String message,
        int code,
        String stack
) {
}
