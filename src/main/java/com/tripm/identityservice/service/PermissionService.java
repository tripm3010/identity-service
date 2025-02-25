package com.tripm.identityservice.service;

import com.tripm.identityservice.dto.request.PermissionRequest;
import com.tripm.identityservice.dto.response.PermissionResponse;
import com.tripm.identityservice.entity.Permission;
import com.tripm.identityservice.mapper.PermissionMapper;
import com.tripm.identityservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        permissionRepository.save(permissionMapper.toPermission(request));
        return permissionMapper.toPermissionResponse(permissionRepository.save(permissionMapper.toPermission(request)));
    }

    public List<PermissionResponse> getAll() {
//        List<Permission> permissions = permissionRepository.findAll();
//        List<PermissionResponse> responses = new ArrayList<>();
//        for (Permission permission : permissions) {
//            responses.add(permissionMapper.toPermissionResponse(permission));
//        }
//        return responses;
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
