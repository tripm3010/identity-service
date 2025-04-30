package com.tripm.identityservice.mapper;

import com.tripm.identityservice.dto.request.ProfileCreationRequest;
import com.tripm.identityservice.dto.request.UserCreationRequest;
import com.tripm.identityservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest userCreationRequest);


}
