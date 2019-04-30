package com.yagamic.base.infrastructure.mybatis;

import com.yagamic.base.domain.authority.model.AuthorityPrivilege;
import com.yagamic.base.domain.authority.model.AuthorityPrivilegeChildren;
import com.yagamic.base.domain.authority.repository.AuthorityPrivilegeRepository;
import com.yagamic.base.infrastructure.mybatis.jpa.authority.AuthorityPrivilegeJpaRepository;
import com.yagamic.base.infrastructure.mybatis.mapper.AuthorityPrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
@Repository
public class AuthorityPrivilegeMysqlRepository implements AuthorityPrivilegeRepository {

    @Autowired
    private AuthorityPrivilegeJpaRepository authorityPrivilegeJpaRepository;
    @Autowired
    private AuthorityPrivilegeMapper authorityPrivilegeMapper;

    @Override
    public List<AuthorityPrivilege> findAll() {
        return authorityPrivilegeMapper.findAllParentPrivilege();
    }

    @Override
    public AuthorityPrivilege findById(Integer id) {
        return authorityPrivilegeJpaRepository.getOne(id);
    }

    @Override
    public List<AuthorityPrivilege> findRolePrivilege(Integer roleId) {
        return authorityPrivilegeMapper.findRolePrivilege(roleId);
    }

    @Override
    public List<AuthorityPrivilegeChildren> findByParentId(Integer id) {
        return authorityPrivilegeMapper.findAuthorityPrivilegeByParentPrivilegeId(id);
    }

    @Override
    public List<AuthorityPrivilegeChildren> findEnableByParentId(Integer id) {
        return authorityPrivilegeMapper.findEnableAuthorityPrivilegeByParentPrivilegeId(id);
    }

    @Override
    public List<Integer> findCheckPrivilege(Integer id) {
        return authorityPrivilegeMapper.findCheckPrivilege(id);
    }

    @Override
    public void save(AuthorityPrivilege authorityPrivilege) {
        authorityPrivilegeJpaRepository.save(authorityPrivilege);
    }

}
