package com.menus.backend.controller.rest;

import com.menus.backend.domain.dto.*;
import com.menus.backend.service.MenuItemService;
import com.menus.backend.service.MenuSectionService;
import com.menus.backend.service.MenuService;
import com.menus.backend.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@Slf4j
public class ImagesController {

    private final Path anyPath = Paths.get("public/uploads/any").toAbsolutePath().normalize();
    private final MenuService menuService;
    private final MenuSectionService menuSectionService;
    private final MenuItemService menuItemService;
    private final RestaurantService restaurantService;

    public ImagesController(MenuService menuService, MenuSectionService menuSectionService, MenuItemService menuItemService, RestaurantService restaurantService) {
        this.menuService = menuService;
        this.menuSectionService = menuSectionService;
        this.menuItemService = menuItemService;
        this.restaurantService = restaurantService;
        try {
            Files.createDirectories(anyPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    @PostMapping(value = "public/uploads/any", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResponse> uploadAnyImage(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Received file: {}", file.getOriginalFilename());

        if (file.isEmpty())
            throw new IllegalArgumentException("Invalid or empty file");

        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/"))
            throw new IllegalArgumentException("Invalid file type! Only images are allowed.");

        if (file.getSize() > 3_500_000)
            throw new IllegalArgumentException("File size exceeds limit!");

        String reservedName = UUID.randomUUID().toString().split("-")[0] + "_" + file.getOriginalFilename();
        Path targetLocation = anyPath.resolve(reservedName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return ResponseEntity.ok(ImageUploadResponse.builder()
                .url("/images/any/" + reservedName)
                .message("Use the URL to access the image")
                .build());
    }

    @GetMapping("public/uploads/any/{filename:.+}")
    public ResponseEntity<Resource> getAnyImages(@PathVariable String filename) {
        try {
            Path filePath = anyPath.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new IllegalArgumentException("File not found or not readable: " + filename);
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read file: " + filename, e);
        }
    }


    @PostMapping(value = "/uploads/{type}/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResponse> uploadEntityImages(
            @RequestParam("file") MultipartFile file,
            @PathVariable(value = "type") String type,
            @PathVariable(value = "id") String itemId
    ) throws IOException {

        var allowedTypes = List.of("menu", "menuItem", "menuSection", "restaurants");
        List<String> list = allowedTypes.stream().filter(it -> it.equalsIgnoreCase(type)).toList();
        if (list.isEmpty())
            throw new IllegalArgumentException("Invalid entity mapping. Use " + allowedTypes);

        int indexOf = allowedTypes.stream().map(it -> it.toLowerCase(Locale.ROOT))
                .toList()
                .indexOf(type.toLowerCase(Locale.ROOT));

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Invalid or empty file");
        }

        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("Invalid file type! Only images are allowed.");
        }

//        if (file.getSize() > 3_500_000) {
//            throw new IllegalArgumentException("File size exceeds 3.5MB limit!");
//        }


        // Determine target folder and ID
        String folder = null;

        if (indexOf == 0) {
            if (menuService.menuById(itemId) != null)
                folder = "public/uploads/menus/";
        } else if (indexOf == 1) {
            if (menuItemService.itemById(itemId) != null)
                folder = "public/uploads/menuItems/";
        } else if (indexOf == 2) {
            log.debug("sid {}",itemId);
            if (menuSectionService.sectionById(itemId) != null)
                folder = "public/uploads/menuSections/";
        } else if (indexOf == 3) {
            if (restaurantService.restaurantsById(itemId) != null)
                folder = "public/uploads/restaurants/";
        } else {
            throw new IllegalArgumentException("Provide only one of: menu, menuItem, restaurant or menuSection.");
        }

        if (folder == null)
            throw new IllegalArgumentException("Invalid item Id.");

        Path uploadPath = Paths.get(folder).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        String originalFileName = Objects.requireNonNull(file.getOriginalFilename());
        String finalFileName = itemId + "_" + originalFileName;
        Path targetLocation = uploadPath.resolve(finalFileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        log.info("final name {}", targetLocation);
        String url = "images/" + folder + finalFileName;


        if (indexOf == 0) {
            menuService.updateMenu(itemId, MenuDto.builder().bannerImage(url).build());
        } else if (indexOf == 1) {
            menuItemService.update(itemId, MenuItemDto.builder().imageUrl(url).build());
        } else if (indexOf == 2) {
            menuSectionService.updateMenuSection(itemId, MenuSectionDto.builder().bannerImage(url).build());
        } else {
            restaurantService.updateRestaurant(itemId, RestaurantDto.builder().bannerImage(url).build());
        }


        return ResponseEntity.ok(
                ImageUploadResponse.builder()
                        .url(url)
                        .message("Image saved in path.")
                        .build()
        );
    }

//    @GetMapping("public/uploads/{type}/")
//    public ResponseEntity<Resource> getEntityImages(
//            @PathVariable String type
//    ) {

    /// /        TODO: resolve all type images and return
//        return null;
//    }
    @GetMapping("public/uploads/{type}/{filename}")
    public ResponseEntity<Resource> getEntityImage(
            @PathVariable String type,
            @PathVariable String filename
    ) {
        try {
            // Validate allowed types
            if (!type.equalsIgnoreCase("menus") && !type.equalsIgnoreCase("menuItems") && !type.equalsIgnoreCase("menuSections") && !type.equalsIgnoreCase("restaurants")) {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            Path filePath = Paths.get("public/uploads/" + type).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new IllegalArgumentException("File not found: " + filename);
            }

            // You can dynamically detect media type if needed
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Error reading file: " + e.getMessage());
        }
    }

}
