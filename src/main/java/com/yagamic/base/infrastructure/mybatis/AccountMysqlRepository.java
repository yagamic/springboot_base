package com.yagamic.base.infrastructure.mybatis;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.model.AccountDetail;
import com.yagamic.base.domain.account.model.OrganizationAccount;
import com.yagamic.base.domain.account.repository.AccountRepository;
import com.yagamic.base.infrastructure.mybatis.jpa.AccountJpaRepository;
import com.yagamic.base.infrastructure.mybatis.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by limengsheng on 2016/7/19.
 */
@Repository
public class AccountMysqlRepository implements AccountRepository {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    private AccountMapper accountMapper;

    @Override
    public Integer findOrganizationAccountCount(String keyword) {
        return accountMapper.findOrganizationAccountCount(keyword);
    }

    @Override
    public List<OrganizationAccount> findOrganizationAccount(String keyword, int page, int size) {
        return accountMapper.findOrganizationAccount(keyword, (page - 1) * size, size);
    }

    @Override
    public Account findByName(String username) {
        return accountJpaRepository.findByUsername(username);//accountMapper.findByUsername(username);//accountJpaRepository.findByUsername(username);
    }

    @Override
    public String findByCompanyNameAndName(String company, String name) {
        return accountMapper.findByCompanyNameAndName(company, name);
    }

    @Override
    public Account findByUsername(String username) {
        return accountJpaRepository.findByUsername(username);
    }

    @Override
    public Account findById(Long id) {
        return accountJpaRepository.getOne(id);
    }


    public List<Account> findAllAccounts(String search, int page, int size, String startDate, String endDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("search", search);
        param.put("offset", (page - 1) * size);
        param.put("size", size);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        return accountMapper.findAllAccounts(param);
    }

    @Override
    public void save(Account account) {
        accountMapper.addAccount(account);
    }

    @Override
    public void update(Account account) {
        accountJpaRepository.save(account);
    }

    @Override
    public AccountDetail findDetailById(Long id) {
        return accountMapper.findDetailById(id);
    }

    @Override
    public String findCompanyByUsername(String name) {
        return accountMapper.findCompanyByUsername(name);
    }

    @Override
    public String findRealNameByUsername(String name) {
        return accountMapper.findRealNameByUsername(name);
    }

    @Override
    public List<Account> findImAccounts() {
        return accountMapper.findImAccounts();
    }

   /* @Autowired
    private AccountMapper accountMapper;*/



  /*  @Override
    public void save(Account account) {
        accountJpaRepository.save(account);
    }

    @Override
    public void saveAll(List<Account> accounts) {
        for (Account account : accounts) {
            accountMapper.addAccount(account);
        }
    }

    @Override
    public void disableAll(List<Long> accountIds) {
        accountMapper.updateAccounts(accountIds);
    }

    @Override
    public Account findById(Long accountId) {
        return accountJpaRepository.findOne(accountId);
    }

    @Override
    public Account findByName(String accountName) {
        return accountJpaRepository.findByAccountName(accountName);
    }

    @Override
    public Account findByAccountId(Long accountId) {
        return accountJpaRepository.findOne(accountId);
    }

    @Override
    public List<Account> findByCompanyName(String companyName) {
        return accountJpaRepository.findByCustomerName(companyName);
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        return accountJpaRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Account> findByCustomerId(Long customerId, int page, int size) {
        Sort s = new Sort(Sort.Direction.DESC, "CreateDate");
        Pageable p = new PageRequest(page - 1, size, s);
        return accountJpaRepository.findByCustomerId(customerId, p);
    }

    @Override
    public List<Account> findByCustomerIdWithSlaveType(Long customerId, int page, int size) {
        Sort s = new Sort(Sort.Direction.ASC, "CreateDate");
        Pageable p = new PageRequest(page - 1, size, s);
        return accountJpaRepository.findByCustomerIdAndAccountType(customerId, AccountType.slave, p);
    }

    @Override
    public List<Account> findByCustomerNameWithMasterType(String customerName, int page, int size) {
        Pageable pageable = new PageRequest((page - 1) * size, size);
        if (customerName != null) {
            return this.accountJpaRepository.findByNameAndAccountType(
                    customerName.toUpperCase(), customerName.toUpperCase(), AccountType.master, pageable);
        }
        return accountJpaRepository.findByAccountType(AccountType.master, pageable);
    }

    @Override
    public void addAccountBalance(Map<String, Object> params) {
        accountMapper.addAccountBalance(params);
    }

    @Override
    public void useAccountBalance(Map<String, Object> params) {
        accountMapper.useAccountBalance(params);
    }

    @Override
    public CustomerStatistics getAllCustomerCount() {
        return accountMapper.getAllCountOfCustomer();
    }

    @Override
    public List<Account> findAllCustomer(String customerName, String accountName, String accountRealName, int page, int size) {
        Map<String, Object> param = new HashMap<>();
        param.put("customerName", customerName);
        param.put("accountName", accountName);
        param.put("accountRealName", accountRealName);
        param.put("offset", (page - 1) * size);
        param.put("size", size);
        return accountMapper.findAllCustomer(param);
    }

    @Override
    public List<Account> findAllCustomerWithMasterType() {
        return accountJpaRepository.findByAccountType(AccountType.master);
    }*/
}
