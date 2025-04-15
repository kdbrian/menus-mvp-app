package com.menus.backend.util;

import java.util.regex.Pattern;


public class ImageUrlValidator {
    private static final Pattern urlPattern = Pattern.compile("^(https?://[^\\s]+/[^\\s]+\\.(?i)(jpg|jpeg|png|gif|bmp|webp))$");

    public static boolean isValidImageUrl(String url) {
        if (url == null) return false;
        return urlPattern.matcher(url).matches();
    }
}
