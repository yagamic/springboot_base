package com.yagamic.base.infrastructure.mybatis.jpa;

import com.yagamic.base.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);






}
