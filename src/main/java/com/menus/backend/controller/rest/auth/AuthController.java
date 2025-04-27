package com.menus.backend.controller.rest.auth;


import com.menus.backend.domain.dto.AppUserDto;
import com.menus.backend.domain.model.AppUser;
import com.menus.backend.service.AppUserService;
import com.menus.backend.util.EntityDtoMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final EntityDtoMapper<AppUser, AppUserDto> userDtoEntityDtoMapper;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> hello() {
        var ret = new HashMap<String, Object>();
        ret.put("user", appUserService.all().stream().map(userDtoEntityDtoMapper::toDto).toList());
        ret.put("count", appUserService.all().size());
        return ResponseEntity.ok(ret);
    }

    @PostMapping("/")
    public ResponseEntity<AppUser> createAccount(
            @Valid @RequestBody AppUserDto dto
    ) {
        var user = appUserService.create(dto);
        return ResponseEntity.ok(user);
    }


}
