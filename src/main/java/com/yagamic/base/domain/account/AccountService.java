package com.yagamic.base.domain.account;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Account> findAccountList(String search, int page, int size, String startDate, String endDate) {
        List<Account> list = accountRepository.findAllAccounts(search, page, size, startDate, endDate);
        return null;
    }

    public String findCompanyByUsername(String username) {
        String companyName=null;
//        Long userProfileId = accountRepository.findByName(username).getUserProfileId();
//        UserProfile userProfile = userProfileRepository.findById(userProfileId);
//        companyName = userProfile.getCompany();
        return companyName;
    }
}
