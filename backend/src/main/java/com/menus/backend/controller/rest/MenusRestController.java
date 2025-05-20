package com.menus.backend.controller.rest;

import com.menus.backend.domain.model.Menu;
import com.menus.backend.service.MenuService;
import com.menus.backend.util.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenusRestController {


    private final MenuService menuService;

    @GetMapping("/")
    public ResponseEntity<ResponseMapper<List<Menu>>> getMenus() {
        List<Menu> menus = menuService.menus();
        return ResponseEntity.ok(ResponseMapper
                .<List<Menu>>builder()
                .data(menus)
                .success(true)
                .message("Retrieved " + menus.size() + " Menus")
                .build()
        );
    }
}
