package com.yagamic.base.domain.authority.repository;


import com.yagamic.base.domain.authority.model.AuthorityRoles;

import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2017/5/23.
 */
public interface AuthorityRolesRepository {

    Optional<AuthorityRoles> findById(Integer id);

    void addRole(AuthorityRoles authorityRoles);

    void deleteRole(Integer id);

    List<AuthorityRoles> findAllRoles();



}
