package com.yagamic.base.domain.authority.repository;


import com.yagamic.base.domain.authority.model.Authority;

/**
 * Created by Administrator on 2017/5/23.
 */
public interface AuthorityRepository {
    Authority findByUsername(String username);
    Authority save(Authority authority);
}
