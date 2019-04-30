package com.yagamic.base.infrastructure.mybatis.mapper;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.model.AccountDetail;
import com.yagamic.base.domain.account.model.OrganizationAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by limengsheng on 2016/7/21.
 */

public interface AccountMapper {
    /*   void addAccount(Account account);

       void updateAccounts(@Param("accountIds") List<Long> accountIds);

       void addAccountBalance(Map<String, Object> params);

       void useAccountBalance(Map<String, Object> params);*/
    void addAccount(Account account);

    Account findByUsername(@Param("username") String username);

    String findByCompanyNameAndName(@Param("company") String company, @Param("name") String name);

    Integer findOrganizationAccountCount(@Param("keyword") String keyword);

    List<OrganizationAccount> findOrganizationAccount(@Param("keyword") String keyword,
                                                      @Param("offset") int page,
                                                      @Param("size") int size);

    List<Account> findAllAccounts(Map<String, Object> params);

    AccountDetail findDetailById(@Param("id") Long id);

    String findCompanyByUsername(@Param("name") String name);

    String findRealNameByUsername(@Param("name") String name);

    List<Account> findImAccounts();
}
