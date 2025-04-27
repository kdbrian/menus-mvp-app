package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.dto.AppUserDto;
import com.menus.backend.domain.model.AppUser;
import com.menus.backend.domain.repo.UserRepository;
import com.menus.backend.service.AppUserService;
import com.menus.backend.util.EntityDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final UserRepository userRepository;
    private final EntityDtoMapper<AppUser, AppUserDto> userDtoEntityDtoMapper;

    public AppUserServiceImpl(UserRepository userRepository, EntityDtoMapper<AppUser, AppUserDto> userDtoEntityDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoEntityDtoMapper = userDtoEntityDtoMapper;
    }

    @Override
    public List<AppUser> all() {
        return userRepository.findAll();
    }

    @Override
    public AppUser create(AppUserDto appUserDto) {
        var user = userDtoEntityDtoMapper.fromDto(appUserDto);
        user.setDateJoined(System.currentTimeMillis());
        AppUser saved = userRepository.save(user);
//        log.info("user {}", user);
        return saved;
    }

    @Override
    public List<AppUser> withName(String name) {
        return userRepository.findByNameIgnoringCase(name);
    }

    @Override
    public List<AppUser> withEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> withPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public AppUser profile(String uid) {
        if (!ObjectId.isValid(uid))
            throw new RuntimeException("Invalid Id");

        if (!userRepository.existsById(uid))
            throw new RuntimeException("Invalid Id(m)");

        return userRepository.findById(uid).orElseThrow();
    }

    @Override
    public AppUser updateProfile(String uid, AppUserDto appUserDto) {
        if (!ObjectId.isValid(uid))
            throw new RuntimeException("Invalid Id");
        var user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("Invalid Id(m)"));

        if (appUserDto.getName() != null && !appUserDto.getName().equals(user.getName()))
            user.setName(appUserDto.getName());

        if (appUserDto.getEmail() != null && !appUserDto.getEmail().equals(user.getEmail()))
            user.setName(appUserDto.getEmail());

        if (appUserDto.getPhone() != null && !appUserDto.getPhone().equals(user.getPhone()))
            user.setName(appUserDto.getPhone());

        user.setDateUpdated(System.currentTimeMillis());
        return userRepository.save(user);
    }

    @Override
    public List<AppUser> inDateRange(Long start, Long end) {
        //TODO: 2 check in cache and update
        //TODO: 1 send to cache
        List<AppUser> list = userRepository.findAll().stream().filter(user -> user.getDateJoined() >= start || user.getDateJoined() <= end).toList();

        //TODO: 3 return
        return userRepository.findByDatesInRange(start,end);
    }

    @Override
    public Boolean delete(String uid) {
        //TODO: increment atomic counter
        var ret = userRepository.existsById(uid) && ObjectId.isValid(uid);
        if (!ret) return false;
        else userRepository.deleteById(uid);
        return true;
    }
}
