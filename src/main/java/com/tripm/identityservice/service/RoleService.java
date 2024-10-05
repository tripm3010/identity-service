package com.tripm.identityservice.service;

import com.tripm.identityservice.dto.request.PermissionRequest;
import com.tripm.identityservice.dto.request.RoleRequest;
import com.tripm.identityservice.dto.response.PermissionResponse;
import com.tripm.identityservice.dto.response.RoleResponse;
import com.tripm.identityservice.entity.Permission;
import com.tripm.identityservice.entity.Role;
import com.tripm.identityservice.mapper.PermissionMapper;
import com.tripm.identityservice.mapper.RoleMapper;
import com.tripm.identityservice.repository.PermissionRepository;
import com.tripm.identityservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest request){
        Role role = roleMapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAll(){
       var roles = roleRepository.findAll();
       return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role){
        roleRepository.deleteById(role);
    }
}
