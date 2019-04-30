package com.yagamic.base.infrastructure.mybatis.jpa.authority;

import com.yagamic.base.domain.authority.model.AuthorityRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Administrator on 2017/5/23.
 */
public interface AuthorityRolesJpaRepository extends JpaRepository<AuthorityRoles, Integer> {
    Optional<AuthorityRoles> findById(Integer id);


}
