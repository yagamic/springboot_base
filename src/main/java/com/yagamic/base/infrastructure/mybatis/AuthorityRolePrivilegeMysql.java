package com.yagamic.base.infrastructure.mybatis;

import com.yagamic.base.domain.authority.repository.AuthorityRolePrivilegeRepository;
import com.yagamic.base.infrastructure.mybatis.mapper.AuthorityRolePrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
@Repository
public class AuthorityRolePrivilegeMysql implements AuthorityRolePrivilegeRepository {
    @Autowired
    private AuthorityRolePrivilegeMapper authorityRolePrivilegeMapper;

    @Override
    public List<Integer> findByRoleId(Integer id) {
        return authorityRolePrivilegeMapper.findByRoleId(id);
    }

}
