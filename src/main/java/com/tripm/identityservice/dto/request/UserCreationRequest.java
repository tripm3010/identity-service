package com.tripm.identityservice.dto.request;

import com.tripm.identityservice.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3 , message = "USERNAME_INVALID")
    String username;
    String firstname;
    String lastname;

    @Size(min = 8,message = "PASSWORD_INVALID")
    String password;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
}