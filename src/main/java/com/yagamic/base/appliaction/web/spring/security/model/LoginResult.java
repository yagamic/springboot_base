package com.yagamic.base.appliaction.web.spring.security.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResult {
    private String message;
    private String currentAuthority;
    private String taken;

    public LoginResult(String message, String currentAuthority, String taken) {
        this.message = message;
        this.currentAuthority = currentAuthority;
        this.taken = taken;
    }

    public LoginResult(String message) {
        this.message = message;
    }


}
