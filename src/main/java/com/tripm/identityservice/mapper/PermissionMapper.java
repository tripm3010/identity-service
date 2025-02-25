package com.tripm.identityservice.mapper;

import com.tripm.identityservice.dto.request.PermissionRequest;
import com.tripm.identityservice.dto.response.PermissionResponse;
import com.tripm.identityservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
