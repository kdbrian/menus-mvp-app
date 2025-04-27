package com.menus.backend.controller.graphql;

import com.menus.backend.domain.dto.AppUserDto;
import com.menus.backend.domain.model.AppUser;
import com.menus.backend.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class AppUserGraph {

    private final AppUserService appUserService;

    public AppUserGraph(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @QueryMapping
    public List<AppUser> allUsers() {
        return appUserService.all();
    }

    @QueryMapping
    public List<AppUser> usersWithEmail(@Argument String email) {
        return appUserService.withEmail(email);
    }

    @QueryMapping
    public List<AppUser> usersWithPhone(@Argument String phone) {
        return appUserService.withPhone(phone);
    }

    @QueryMapping
    public List<AppUser> usersWithName(@Argument String name) {
        return appUserService.withName(name);
    }

    @QueryMapping
    public AppUser userProfile(@Argument String id) {
        return appUserService.profile(id);
    }


    @MutationMapping
    public AppUser create(@Argument AppUserDto dto) {
        return appUserService.create(dto);
    }

    private Boolean delete(@Argument String uid) {
        return appUserService.delete(uid);
    }

    @QueryMapping
    public List<AppUser> usersCreatedBetween(@Argument Long start, @Argument Long end) {
        return appUserService.inDateRange(start, end);
    }
}
