package com.yagamic.base.domain.authority.repository;


import com.yagamic.base.domain.authority.model.AuthorityPrivilege;
import com.yagamic.base.domain.authority.model.AuthorityPrivilegeChildren;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public interface AuthorityPrivilegeRepository {

    List<AuthorityPrivilege> findAll();

    AuthorityPrivilege findById(Integer id);

    List<AuthorityPrivilege> findRolePrivilege(Integer roleId);

    List<AuthorityPrivilegeChildren> findByParentId(Integer id);

    List<AuthorityPrivilegeChildren> findEnableByParentId(Integer id);

    List<Integer> findCheckPrivilege(Integer id);

    void save(AuthorityPrivilege authorityPrivilege);


}
