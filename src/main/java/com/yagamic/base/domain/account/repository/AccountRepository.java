package com.yagamic.base.domain.account.repository;


import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.model.AccountDetail;
import com.yagamic.base.domain.account.model.OrganizationAccount;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public interface AccountRepository {
    Integer findOrganizationAccountCount(String keyword);

    List<OrganizationAccount> findOrganizationAccount(String keyword, int page, int size);

    Account findByName(String accountName);

    String findByCompanyNameAndName(String company, String name);

    Account findByUsername(String username);

    Account findById(Long id);

    List<Account> findAllAccounts(String search, int page, int size, String startDate, String endDate);

    void save(Account account);

    void update(Account account);

    AccountDetail findDetailById(Long id);

    String findCompanyByUsername(String name);

    String findRealNameByUsername(String name);

    List<Account> findImAccounts();
}
