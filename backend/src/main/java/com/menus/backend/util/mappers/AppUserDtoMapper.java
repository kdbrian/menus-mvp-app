package com.menus.backend.util.mappers;

import com.menus.backend.domain.dto.AppUserDto;
import com.menus.backend.domain.model.AppUser;
import com.menus.backend.util.EntityDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class AppUserDtoMapper extends EntityDtoMapper<AppUser, AppUserDto> {
    @Override
    public AppUser fromDto(AppUserDto appUserDto) {
        return AppUser.builder()
                .meta(appUserDto.getMeta())
                .name(appUserDto.getName())
                .phone(appUserDto.getPhone())
                .email(appUserDto.getEmail())
                .build();
    }

    @Override
    public AppUserDto toDto(AppUser appUser) {
        return AppUserDto.builder()
                .name(appUser.getName())
                .email(appUser.getEmail())
                .phone(appUser.getPhone())
                .meta(appUser.getMeta())
                .dateUpdated(appUser.getDateUpdated() != null ? appUser.getDateUpdated() : appUser.getDateJoined())
                .build();
    }
}
