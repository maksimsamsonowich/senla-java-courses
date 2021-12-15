package com.github.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthenticationRequestDto {

    private String email;

    private String password;

}
