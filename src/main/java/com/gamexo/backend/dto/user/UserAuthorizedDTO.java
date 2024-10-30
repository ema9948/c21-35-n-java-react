package com.gamexo.backend.dto.user;

public record UserAuthorizedDTO(String email,
                                String message,
                                String token,
                                String role) {
}
