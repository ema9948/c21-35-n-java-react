package com.gamexo.backend.dto.user;

import com.gamexo.backend.model.enums.Role;

public record UserRegistrationDTO(String name,
                                  String email,
                                  String password,
                                  Role rol
                                  ) {
}
