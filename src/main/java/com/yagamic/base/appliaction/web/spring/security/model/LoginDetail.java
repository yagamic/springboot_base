package com.yagamic.base.appliaction.web.spring.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;



@Getter
@Setter
public class LoginDetail implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }
}
