package com.yagamic.base.infrastructure.mybatis;

import com.yagamic.base.domain.authority.model.AuthorityRoles;
import com.yagamic.base.domain.authority.repository.AuthorityRolesRepository;
import com.yagamic.base.infrastructure.mybatis.jpa.authority.AuthorityRolesJpaRepository;
import com.yagamic.base.infrastructure.mybatis.mapper.AuthorityRolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2017/5/23.
 */
@Repository
public class AuthorityRolesMysqlRepository implements AuthorityRolesRepository {

    @Autowired
    private AuthorityRolesJpaRepository authorityRolesJpaRepository;
    @Autowired
    private AuthorityRolesMapper authorityRolesMapper;

    @Override
    public Optional<AuthorityRoles> findById(Integer id) {
        return authorityRolesJpaRepository.findById(id);
    }

    @Override
    public void addRole(AuthorityRoles authorityRoles) {

    }

    @Override
    public void deleteRole(Integer id) {

    }

    @Override
    public List<AuthorityRoles> findAllRoles() {
        return authorityRolesMapper.findRoles();
    }
}
