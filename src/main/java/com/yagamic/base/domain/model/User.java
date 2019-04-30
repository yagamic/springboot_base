package com.yagamic.base.domain.model;

import com.yagamic.base.domain.account.model.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Administrator on 2017/5/19.
 */
public class User implements UserDetails {
    static final long serialVersionUID = -2401990739623520434l;

    private String accountName;
    private String password;
    private Boolean enabled;
//    @Getter
//    private Boolean remember;

    @Getter
    private Long accountId;
    @Getter
    private Boolean admin;
    @Getter
    private String area;
    @Getter
    private String email;
    @Getter
    private String realName;
    @Getter
    private Long userProfileId;

    public User() {
    }

    public User(Account account) {
        this.accountName = account.getUsername();
        this.password = account.getPassword();
        this.enabled = account.getEnable();
        this.accountId = account.getId();
        this.admin = account.getAdmin();
        this.area = account.getArea();
        this.email = account.getEmail();
        this.realName = account.getRealName();
        this.userProfileId = account.getUserProfileId();
        //this.remember = account.getRemember();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return accountName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
