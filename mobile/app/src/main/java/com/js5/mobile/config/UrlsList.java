package com.js5.mobile.config;

public final class UrlsList {
    public static final String BASE_URL = AppConfig.PRODUCTION
            ? "to be set"
            : "http://172.16.130.102:8000/";
    public static final String API_URL = BASE_URL + "api/";
    public static final String LOGIN_URL = API_URL + "login";
    public static final String USERS_URL = API_URL + "users";
}
