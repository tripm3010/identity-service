package com.tripm.identityservice.mapper;


import com.tripm.identityservice.dto.request.PermissionRequest;
import com.tripm.identityservice.dto.request.RoleRequest;
import com.tripm.identityservice.dto.request.UserUpdateRequest;
import com.tripm.identityservice.dto.response.PermissionResponse;
import com.tripm.identityservice.dto.response.RoleResponse;
import com.tripm.identityservice.entity.Permission;
import com.tripm.identityservice.entity.Role;
import com.tripm.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);}
