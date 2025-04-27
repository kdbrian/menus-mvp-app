package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.dto.MenuItemDto;
import com.menus.backend.domain.model.Menu;
import com.menus.backend.domain.model.MenuItem;
import com.menus.backend.domain.repo.MenuItemRepository;
import com.menus.backend.domain.repo.MenuSectionRepository;
import com.menus.backend.service.MenuItemService;
import com.menus.backend.util.EntityDtoMapper;
import com.menus.backend.util.ImageUrlValidator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuSectionRepository menuSectionRepository;
    private final EntityDtoMapper<MenuItem, MenuItemDto> menuItemDtoEntityDtoMapper;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, MenuSectionRepository menuSectionRepository, EntityDtoMapper<MenuItem, MenuItemDto> menuItemDtoEntityDtoMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuSectionRepository = menuSectionRepository;
        this.menuItemDtoEntityDtoMapper = menuItemDtoEntityDtoMapper;
    }

    @Override
    public List<MenuItem> items() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem itemById(String id) {
        if (!ObjectId.isValid(id))
            throw new RuntimeException("Invalid ID");

        return menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Item"));
    }

    @Override
    public List<MenuItem> itemsFromSection(String sectionId) {

        if (!ObjectId.isValid(sectionId))
            throw new RuntimeException("Invalid ID");

        var section = menuSectionRepository.findById(sectionId).orElseThrow(() -> new RuntimeException("Invalid Section"));
        return section.getMenuItems();
    }

    @Override
    public List<MenuItem> itemsByName(String name) {
        return menuItemRepository.findByNameContainingIgnoringCase(name);
    }

    @Override
    public List<MenuItem> itemsByDescription(String description) {
        return menuItemRepository.findByDescriptionContainingIgnoringCase(description);
    }

    @Override
    public List<MenuItem> itemsByCategories(List<String> categories) {
        return menuItemRepository.findByCategoriesIn(categories);
    }

    @Override
    public List<MenuItem> itemsByTags(List<String> tags) {
        return menuItemRepository.findByTagsIn(tags);
    }

    @Override
    public List<MenuItem> itemsByPriceRange(Double from, Double to) {
        return menuItemRepository.findByPriceInRange(from, to);
    }

    @Override
    public MenuItem addToSection(String sectionId, MenuItemDto menuItemDto) {

        if (!ObjectId.isValid(sectionId))
            throw new RuntimeException("Invalid ID");

        var section = menuSectionRepository.findById(sectionId).orElseThrow(() -> new RuntimeException("Invalid Section"));

        MenuItem menuItem = menuItemDtoEntityDtoMapper.fromDto(menuItemDto);
        menuItem.setCreatedAt(System.currentTimeMillis());

        MenuItem savedItem = menuItemRepository.save(menuItem);

        section.addMenuItem(savedItem);
        menuSectionRepository.save(section);

        return savedItem;
    }

    @Override
    public MenuItem update(String itemId, MenuItemDto menuItemDto) {
        if (!ObjectId.isValid(itemId))
            throw new RuntimeException("Invalid ID");

        var menuItem = menuItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Invalid Item"));

        if (menuItemDto.getDescription() != null && !menuItemDto.getDescription().isEmpty())
            menuItem.setDescription(menuItemDto.getDescription());

        if (menuItemDto.getTags() != null && !menuItemDto.getTags().isEmpty())
            menuItem.addTags(menuItemDto.getTags());

        if (menuItemDto.getCategory() != null && !menuItemDto.getCategory().isEmpty())
            menuItem.addCategories(menuItemDto.getCategory());

        if (menuItemDto.getName() != null && !menuItemDto.getName().isEmpty())
            menuItem.setName(menuItemDto.getName());

        if (menuItemDto.getImageUrl() != null && ImageUrlValidator.isValidImageUrl(menuItemDto.getImageUrl()))
            menuItem.setImageUrl(menuItem.getImageUrl());

        return menuItemRepository.save(menuItem);
    }

    @Override
    public Boolean deleteItemFromSections(String itemId) {
        var exists = menuItemRepository.existsById(itemId);
        menuItemRepository.deleteById(itemId);
        return exists;
    }
}
