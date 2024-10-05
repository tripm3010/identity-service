package com.tripm.identityservice.mapper;


import com.tripm.identityservice.dto.request.UserCreationRequest;
import com.tripm.identityservice.dto.request.UserUpdateRequest;
import com.tripm.identityservice.dto.response.UserResponse;
import com.tripm.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
