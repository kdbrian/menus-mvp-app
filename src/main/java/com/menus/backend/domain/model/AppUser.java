package com.menus.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {

    @Id
    private String uid;
    private String name, email, phone;
    Map<String, Object> meta = new HashMap<>();

    @Builder.Default
    private Long dateJoined = System.currentTimeMillis();

    private Long dateUpdated;

    @Builder.Default
    private transient Boolean isActive = true;

}
