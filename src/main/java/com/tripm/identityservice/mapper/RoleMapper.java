package com.tripm.identityservice.mapper;

import com.tripm.identityservice.dto.request.RoleRequest;
import com.tripm.identityservice.dto.response.RoleResponse;
import com.tripm.identityservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRole(Role role);
}
