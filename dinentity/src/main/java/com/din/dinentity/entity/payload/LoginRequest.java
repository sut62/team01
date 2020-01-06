package com.din.dinentity.entity.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * LoginRequest
 */
@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}