package ru.instazoo.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Info {
    private String info;
    private boolean success;
}
