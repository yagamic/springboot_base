package com.yagamic.base.infrastructure.mybatis;

import com.yagamic.base.domain.authority.model.Authority;
import com.yagamic.base.domain.authority.repository.AuthorityRepository;
import com.yagamic.base.infrastructure.mybatis.jpa.authority.AuthorityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/26.
 */
@Repository
public class AuthorityMysqlRepository implements AuthorityRepository {
    @Autowired
    private AuthorityJpaRepository authorityJpaRepository;
    @Override
    public Authority findByUsername(String username) {
        return authorityJpaRepository.findByUsername(username);
    }

    @Override
    public Authority save(Authority authority) {
        return authorityJpaRepository.save(authority);
    }
}
