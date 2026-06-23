package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

import org.example.entities.UserInfo;
import org.example.eventProducer.UserInfoEvent;
import org.example.eventProducer.UserInfoProducer;
import org.example.models.UserInfoDto;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@AllArgsConstructor
@Data
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserInfoProducer userInfoProducer;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    public UserDetails loadUserByUsername(String username) {
        log.debug("entering into loadByUsername method..");
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("username not found..");
            throw new UsernameNotFoundException("could not found user..");
        }
        log.info("user authenticated succesfully..");
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto) {
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signUpUser(UserInfoDto userInfoDto) {
        log.debug("entering into signUpUser method..");
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if (Objects.nonNull(checkIfUserAlreadyExist(userInfoDto))) {
            return false;
        }

        String userId = UUID.randomUUID().toString();
        userRepository
                .save(new UserInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(), new HashSet<>()));
        log.info("user signup succesfull..");

        userInfoProducer.sendMessageToKafka(DtoToEvent(userInfoDto, userId));
        return true;
    }

    private UserInfoEvent DtoToEvent(UserInfoDto userInfoDto, String id) {
        UserInfoEvent userInfoEvent = UserInfoEvent.builder().userId(id).firstName(userInfoDto.getFirstName())
                .lastName(userInfoDto.getLastName()).email(userInfoDto.getEmail())
                .phoneNumber(userInfoDto.getPhoneNumber()).build();
                return userInfoEvent;
    }

}
