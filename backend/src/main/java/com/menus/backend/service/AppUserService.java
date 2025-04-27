package com.menus.backend.service;


import com.menus.backend.domain.dto.AppUserDto;
import com.menus.backend.domain.model.AppUser;

import java.util.List;

public interface AppUserService {

    List<AppUser> all();

    AppUser create(AppUserDto appUserDto);

    List<AppUser> withName(String name);

    List<AppUser> withEmail(String email);

    List<AppUser> withPhone(String phone);

    AppUser profile(String uid);

    AppUser updateProfile(String uid, AppUserDto appUserDto);
    List<AppUser> inDateRange(Long start, Long end);

    Boolean delete(String uid);
}
