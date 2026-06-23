package org.example.models;

import org.example.entities.UserInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.micrometer.common.lang.NonNull;
import lombok.Builder;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDto extends UserInfo {
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
    
    @NonNull
    private Long phoneNumber;
    
    @NonNull
    private String email;    
}
