package com.tripm.identityservice.controller;

import com.tripm.identityservice.dto.request.ApiResponse;
import com.tripm.identityservice.dto.request.RoleRequest;
import com.tripm.identityservice.dto.response.RoleResponse;
import com.tripm.identityservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(roleRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> findAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{roleId}")
    public ApiResponse<String> delete(@PathVariable String roleId) {
        roleService.deleteById(roleId);
        return ApiResponse.<String>builder()
                .message("Role deleted")
                .build();
    }
}
