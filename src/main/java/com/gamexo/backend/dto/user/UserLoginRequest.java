package com.gamexo.backend.dto.user;

public record UserLoginRequest(String email,
                               String password) {
}
