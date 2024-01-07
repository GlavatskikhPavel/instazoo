package ru.instazoo.backend.payload.request;

import lombok.Data;

@Data
public class SingUpRequest {
    private String email;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String confirmPassword;
}
