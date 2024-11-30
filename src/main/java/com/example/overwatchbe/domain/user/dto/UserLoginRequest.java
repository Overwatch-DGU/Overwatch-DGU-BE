package com.example.overwatchbe.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class UserLoginRequest {
    @NotNull
    private String email;
    private String password;
}
