package com.yagamic.base.appliaction.web.spring.security.model;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class LoginInput {
    String username;
    String password;
    Boolean remember = false;
//    String captcha;
}
