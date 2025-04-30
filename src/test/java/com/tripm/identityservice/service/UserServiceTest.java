package com.tripm.identityservice.service;

import com.tripm.identityservice.dto.request.UserCreationRequest;
import com.tripm.identityservice.dto.response.UserResponse;
import com.tripm.identityservice.entity.User;
import com.tripm.identityservice.exception.AppException;
import com.tripm.identityservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired private UserService userService;

    @Mock
    private UserRepository userRepository;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private LocalDate dob;
    private User user;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(1999,3,1);
        userCreationRequest = UserCreationRequest.builder()
                .username("minhtri3010")
                .firstname("Josh")
                .lastname("Bod")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("1asdadqwecsdadqw")
                .username("minhtri3010")
                .firstname("Josh")
                .lastname("Bod")
                .dob(dob)
                .build();

        user = User.builder()
                .id("1asdadqwecsdadqw")
                .username("minhtri3010")
//                .firstname("Josh")
//                .lastname("Bod")
//                .dob(dob)
                .build();
    }


    @Test
    @Transactional
    void createUser_validRequest_success(){
        //GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        //WHEN
        var userResponse = userService.createUser(userCreationRequest);

        //THEN
        Assertions.assertEquals(userResponse.getUsername(),userCreationRequest.getUsername());
        Assertions.assertEquals(userResponse.getUsername(),userCreationRequest.getUsername());

    }



//    @Test
//    @Transactional
//    void createUser_userExisted_fail(){
//        //GIVEN
//        when(userRepository.existsByUsername(anyString())).thenReturn(true);
//
//        //WHEN
//        var exception = Assertions.assertThrows(AppException.class,
//                () -> userService.createUser(userCreationRequest));
//
//        Assertions.assertEquals(1002, exception.getErrorCode().getCode());
//    }


//    @Test
//    @WithMockUser(username = "minhtri3010")
//    void getMyInfo_valid_success(){
//        //GIVEN
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(user));
//        //WHEN
//        var userResponse = userService.getMyInfo("minhtri3010");
//        //THEN
//        Assertions.assertEquals(userResponse.getUsername(),user.getUsername());
//
//    }
}
