package com.yagamic.base.domain.authority.repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
public interface AuthorityRolePrivilegeRepository {
    List<Integer> findByRoleId(Integer id);
}
