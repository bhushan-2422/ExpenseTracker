package com.user.userservice.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.userservice.entities.UserInfoDto;

public class UserInfoDeserializer implements Deserializer<UserInfoDto>{

    @Override
    public UserInfoDto deserialize(String arg0, byte[] arg1) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDto user = null;
        try{
             user = objectMapper.readValue(arg1, UserInfoDto.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
    
}
