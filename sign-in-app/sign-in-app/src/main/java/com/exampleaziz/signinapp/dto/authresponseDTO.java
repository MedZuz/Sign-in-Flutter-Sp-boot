package com.exampleaziz.signinapp.dto;

import lombok.Data;

@Data
public class authresponseDTO {

    private String accessToken;
    private String tokenType = "bearer ";

    public authresponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
