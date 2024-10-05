package com.tripm.identityservice.dto.request;

import com.tripm.identityservice.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String password;
    String firstName;
    String lastName;

    @DobConstraint( min = 18, message = "INVALID_DOB")
    LocalDate dob;
    List<String> roles;

}
