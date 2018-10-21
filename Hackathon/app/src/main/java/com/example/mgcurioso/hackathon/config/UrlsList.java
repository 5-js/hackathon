package com.example.mgcurioso.hackathon.config;

public final class UrlsList {
    public static final String BASE_URL = AppConfig.PRODUCTION
            ? "to be set"
            : "http://http://172.16.130.71:8000/";
    public static final String API_URL = BASE_URL + "api/";
    public static final String TASKS_URL = API_URL + "tasks/";
}
