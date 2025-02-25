package com.tripm.identityservice.service;

import com.tripm.identityservice.dto.request.RoleRequest;
import com.tripm.identityservice.dto.response.RoleResponse;
import com.tripm.identityservice.entity.Permission;
import com.tripm.identityservice.entity.Role;
import com.tripm.identityservice.exception.AppException;
import com.tripm.identityservice.exception.ErrorCode;
import com.tripm.identityservice.mapper.RoleMapper;
import com.tripm.identityservice.repository.PermissionRepository;
import com.tripm.identityservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;


    public RoleResponse create(RoleRequest roleRequest) {
        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());

        Role role = roleMapper.toRole(roleRequest);
        role.setPermissions(new HashSet<>(permissions));
        return roleMapper.toRole(roleRepository.save(role));
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRole).collect(Collectors.toList());
    }

    public void deleteById(String role) {
        roleRepository.deleteById(role);
    }
}
