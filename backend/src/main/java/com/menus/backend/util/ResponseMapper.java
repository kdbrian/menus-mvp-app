package com.menus.backend.util;

import lombok.Builder;

@Builder
public record ResponseMapper <T>(
        T data,
        boolean success,
        String message
){
}
