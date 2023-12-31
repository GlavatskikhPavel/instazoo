package ru.instazoo.backend.security;

public class SecurityConstants {
    public static final String SING_UP_URLS = "/api/v1/auth/**";

    public static final String SECRET = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 600000; // 10 minutes
}
