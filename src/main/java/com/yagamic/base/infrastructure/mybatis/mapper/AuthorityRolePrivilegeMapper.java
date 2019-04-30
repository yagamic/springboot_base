package com.yagamic.base.infrastructure.mybatis.mapper;

import com.yagamic.base.domain.authority.model.AuthorityRolePrivilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface AuthorityRolePrivilegeMapper {
    //AuthorityRolePrivilege save(AuthorityRolePrivilege authorityRolePrivilege);
    void insert(AuthorityRolePrivilege authorityRolePrivilege);

    void deleteByRoleId(@Param("id") Integer id);

    List<Integer> findByRoleId(@Param("id") Integer id);
}
