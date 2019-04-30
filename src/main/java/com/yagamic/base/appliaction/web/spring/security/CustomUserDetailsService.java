package com.yagamic.base.appliaction.web.spring.security;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.repository.AccountRepository;
import com.yagamic.base.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.findByName(accountName);
        return new User(account);
    }
}
