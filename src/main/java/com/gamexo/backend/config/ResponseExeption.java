package com.gamexo.backend.config;

import lombok.Getter;

@Getter
public class ResponseExeption extends RuntimeException {
    private final String code;

    public ResponseExeption(String code, String message) {
        super(message);
        this.code = code;
    }
}