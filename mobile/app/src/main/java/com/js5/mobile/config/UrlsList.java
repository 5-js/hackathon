package com.js5.mobile.config;

public final class UrlsList {
    public static final String BASE_URL = AppConfig.PRODUCTION
            ? "to be set"
            : "http://172.16.130.102/";
    public static final String API_URL = BASE_URL + "api/";
}
