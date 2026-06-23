package com.user.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.umd.cs.findbugs.annotations.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDto {

    @NonNull
    @JsonProperty("user_id")
    private String userId;
    @NonNull
    @JsonProperty("first_name")
    private String firstName;
    @NonNull
    @JsonProperty("last_name")
    private String lastName;
    @NonNull
    @JsonProperty("email")
    private String email;
    @NonNull
    @JsonProperty("phone_number")
    private String phoneNumber;

    public UserInfo transformToUserInfo(){
        return UserInfo.builder().userId(userId).firstName(firstName).lastName(lastName).email(email).phoneNumber(phoneNumber).build();
    }

}
