package com.gamexo.backend.dto.user;

import com.gamexo.backend.model.UserEntity;

public record UserInfoDTO(Long id, String name, String email, String role, String accessToken) {
    public UserInfoDTO(UserEntity user) {
        this(
                user.getId(),
                user.getCustomer().getName(),
                user.getEmail(),
                user.getRole().name(),
                null
        );


    }

    public static UserInfoDTO fromDTO(UserEntity user) {
        return new UserInfoDTO(user);
    }
}
