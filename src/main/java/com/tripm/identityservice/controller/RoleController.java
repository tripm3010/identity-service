package com.tripm.identityservice.controller;


import com.tripm.identityservice.dto.request.ApiResponse;
import com.tripm.identityservice.dto.request.PermissionRequest;
import com.tripm.identityservice.dto.request.RoleRequest;
import com.tripm.identityservice.dto.response.PermissionResponse;
import com.tripm.identityservice.dto.response.RoleResponse;
import com.tripm.identityservice.service.PermissionService;
import com.tripm.identityservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .build();
    }

}
