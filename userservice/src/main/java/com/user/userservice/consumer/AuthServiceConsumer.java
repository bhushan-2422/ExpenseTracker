package com.user.userservice.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.user.userservice.entities.UserInfoDto;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.service.UserService;

@Service
public class AuthServiceConsumer {

    UserRepository userRepository;
    UserService userService;
    @Autowired
    AuthServiceConsumer(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @KafkaListener(topics = "user_service", groupId = "userinfo-consumer-group")
    public void listen(UserInfoDto eventObject) {
        try{
            userService.createOrUpdateUser(eventObject);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
