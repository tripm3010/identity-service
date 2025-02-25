package com.tripm.identityservice.dto.request;

import com.tripm.identityservice.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {

    String name;
    String description;

    Set<String> permissions;
}
