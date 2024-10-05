package com.tripm.identityservice.mapper;


import com.tripm.identityservice.dto.request.PermissionRequest;
import com.tripm.identityservice.dto.request.UserUpdateRequest;
import com.tripm.identityservice.dto.response.PermissionResponse;
import com.tripm.identityservice.entity.Permission;
import com.tripm.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
