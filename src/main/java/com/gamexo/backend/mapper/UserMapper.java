package com.gamexo.backend.mapper;

import com.gamexo.backend.dto.user.UserInfoDTO;
import com.gamexo.backend.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mapping(target = "name", source = "user.customer.name")
    UserInfoDTO userToDto(UserEntity user);

    @Mapping(target = "name", source = "user.customer.name")
    @Mapping(target = "accessToken", source = "accessToken")
    UserInfoDTO userToDtoWithToken(UserEntity user, String accessToken);

    @Named("mapPassword")
    default String mapPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
