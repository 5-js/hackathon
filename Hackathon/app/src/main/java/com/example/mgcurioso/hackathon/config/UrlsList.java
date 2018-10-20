package com.example.mgcurioso.hackathon.config;

public final class UrlsList {
    public static final String BASE_URL = AppConfig.PRODUCTION
            ? "to be set"
            : "http://192.168.1.10:8000/";
    public static final String API_URL = BASE_URL + "api/";
    public static final String TASKS_URL = API_URL + "tasks";
}
