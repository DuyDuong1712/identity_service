package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.UserCreateRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserCreateRequest userDTO);

    UserResponse toDTO(UserEntity user);

    @Mapping(target = "id", ignore = true) // tránh ghi đè id
    void updateEntity(@MappingTarget UserEntity entity, UserUpdateRequest dto);

    List<UserResponse> toDTOs(List<UserEntity> entities);
}
