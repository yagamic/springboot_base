package com.yagamic.base.infrastructure.mybatis.mapper;


import com.yagamic.base.domain.authority.model.AuthorityRoles;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public interface AuthorityRolesMapper {
    List<AuthorityRoles> findRoles();
}
