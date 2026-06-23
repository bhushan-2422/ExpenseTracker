package com.user.userservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.umd.cs.findbugs.annotations.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class UserInfo {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
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


}
