package com.user.userservice.service;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.userservice.entities.UserInfo;
import com.user.userservice.entities.UserInfoDto;
import com.user.userservice.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto){
        Function<UserInfo, UserInfo> updatingUser = user -> {
            user.setEmail(userInfoDto.getEmail());
            user.setFirstName(userInfoDto.getFirstName());
            user.setLastName(userInfoDto.getLastName());
            user.setPhoneNumber(userInfoDto.getPhoneNumber());
            return userRepository.save(user);
        };

        Supplier<UserInfo> createUser = () -> {
            return userRepository.save(userInfoDto.transformToUserInfo());
        };

        UserInfo user = userRepository.findByUserId(userInfoDto.getUserId()).map(updatingUser).orElseGet(createUser);
        return new UserInfoDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }
}
