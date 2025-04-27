package com.menus.backend.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserDto {
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^(\\+2541\\d{7}|\\+2547\\d{8}|01\\d{8})$",
            message = "Invalid Kenyan phone number"
    )
    private String phone;

    Map<String, Object> meta = new HashMap<>();
    private Long dateUpdated;
}
