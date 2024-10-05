package com.tripm.identityservice.controller;

import com.nimbusds.jose.JOSEException;
import com.tripm.identityservice.dto.request.ApiResponse;
import com.tripm.identityservice.dto.request.AuthenticationRequest;
import com.tripm.identityservice.dto.request.IntrospectRequest;
import com.tripm.identityservice.dto.request.LogoutRequest;
import com.tripm.identityservice.dto.response.AuthenticationResponse;
import com.tripm.identityservice.dto.response.IntrospectRespone;
import com.tripm.identityservice.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectRespone> authenticate(@RequestBody IntrospectRequest  request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectRespone>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> authenticate(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }
}
