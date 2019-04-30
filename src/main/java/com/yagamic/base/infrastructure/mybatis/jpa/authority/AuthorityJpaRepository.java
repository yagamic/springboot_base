package com.yagamic.base.infrastructure.mybatis.jpa.authority;

import com.yagamic.base.domain.authority.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface AuthorityJpaRepository extends JpaRepository<Authority, Long> {
    Authority findByUsername(String username);
}
