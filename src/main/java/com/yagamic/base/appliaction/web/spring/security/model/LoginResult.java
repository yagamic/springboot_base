package com.yagamic.base.appliaction.web.spring.security.model;

import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class LoginResult {
    private String message;
    private String taken;

    public LoginResult(String message, String taken) {
        this.message = message;
        this.taken = taken;
    }

    public LoginResult(String message) {
        this.message = message;
    }


}
