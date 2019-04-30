package com.yagamic.base.infrastructure.mybatis.mapper;

import com.yagamic.base.domain.authority.model.AuthorityPrivilege;
import com.yagamic.base.domain.authority.model.AuthorityPrivilegeChildren;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public interface AuthorityPrivilegeMapper {
    List<AuthorityPrivilege> findAll();

    List<AuthorityPrivilege> findAllParentPrivilege();


    //AuthorityPrivilege findById(Long id);
    List<AuthorityPrivilege> findRolePrivilege(@Param("id") Integer id);

    List<AuthorityPrivilegeChildren> findAuthorityPrivilegeByParentPrivilegeId(@Param("id") Integer id);

    List<AuthorityPrivilegeChildren> findEnableAuthorityPrivilegeByParentPrivilegeId(@Param("id") Integer id);

    List<Integer> findCheckPrivilege(@Param("id") Integer id);
}
