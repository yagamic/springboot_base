package com.yagamic.base.infrastructure.mybatis.jpa.authority;

import com.yagamic.base.domain.authority.model.AuthorityPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public interface AuthorityPrivilegeJpaRepository extends JpaRepository<AuthorityPrivilege, Integer> {

    //AuthorityPrivilege findById(Integer id);
    List<AuthorityPrivilege> findAuthorityPrivilegeByParentPrivilegeId(Integer parentPrivilegeId);
}
