package com.menus.backend.util;

import java.util.regex.Pattern;


public class ImageUrlValidator {
    private static final Pattern urlPattern = Pattern.compile("^(https?://[^\\s]+/[^\\s]+\\.(?i)(jpg|jpeg|png|gif|bmp|webp))$");
    private static final Pattern urlPatternLocalImageServer = Pattern.compile("^(http?://[^\\s]+/[^\\s]+\\.(?i)(jpg|jpeg|png|gif|bmp|webp))$");
    private static final Pattern urlPatternServerFolder = Pattern.compile("^(http?://[^\\s]+/[^\\s]+\\.(?i)(jpg|jpeg|png|gif|bmp|webp))$");

    public static boolean isValidImageUrl(String url) {
        if (url == null) return false;
        return
                urlPattern.matcher(url).matches() ||
                        urlPatternLocalImageServer.matcher(url).matches() ||
                        urlPatternServerFolder.matcher(url).matches()
                ;
    }
}
