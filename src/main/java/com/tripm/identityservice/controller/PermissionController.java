package com.tripm.identityservice.controller;

import com.tripm.identityservice.dto.request.ApiResponse;
import com.tripm.identityservice.dto.request.PermissionRequest;
import com.tripm.identityservice.dto.response.PermissionResponse;
import com.tripm.identityservice.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping()
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping()
    public ApiResponse<List<PermissionResponse>> findAllPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    public ApiResponse<String> deletePermission(@PathVariable("permissionId") String permissionId) {
        permissionService.delete(permissionId);
        return ApiResponse.<String>builder()
                .message("Permission deleted")
                .build();
    }
}
