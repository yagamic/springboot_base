package com.yagamic.base.domain.account;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service
@Slf4j
public class AccountService {
    public final static String CRM_SESSION_USER_MAP_KEY = "henan-crm-account-session-map:";

    @Autowired
    private AccountRepository accountRepository;
//    @Autowired
//    private UserProfileRepository userProfileRepository;


    public Long loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.findByName(accountName);
        return account.getId();
    }
    public String findAuthorByUsername(String username) {
        String Author = "customer";
        Account account = accountRepository.findByName(username);
        //System.out.println(account.toString());
        if(account.getAdmin()){
            Author = "admin";
        }
        return Author;
    }

}
