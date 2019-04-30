package com.yagamic.base.domain.authority;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.repository.AccountRepository;
import com.yagamic.base.domain.authority.repository.AuthorityPrivilegeRepository;
import com.yagamic.base.domain.authority.repository.AuthorityRepository;
import com.yagamic.base.domain.authority.repository.AuthorityRolePrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
@Service
public class AuthorityService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private AuthorityRolePrivilegeRepository authorityRolePrivilegeRepository;
    @Autowired
    private AuthorityPrivilegeRepository authorityPrivilegeRepository;

    /*
    控制账号启用和禁用
    */
    public void controlAccount(Account account, Boolean enable) {
        account.setEnable(enable);
        accountRepository.save(account);
    }

    /*
    获取角色权限
    */
    public String getPrivilege(Integer id) {
        String privilege = null;
        StringBuffer s = new StringBuffer();
        List<Integer> idList = authorityRolePrivilegeRepository.findByRoleId(id);
        for (Integer privilegeId : idList) {
            s.append(authorityPrivilegeRepository.findById(privilegeId).getPrivilegeName() + ",");
        }
        privilege = s.toString();
        privilege = privilege.substring(0, privilege.length() - 1);
        System.out.println("-----------权限:" + privilege);
        return privilege;
    }

    //
    public String getPrivilegeDescription(Integer id) {
        StringBuffer s = new StringBuffer();
        List<Integer> idList = authorityRolePrivilegeRepository.findByRoleId(id);
        for (Integer privilegeId : idList) {
            s.append(authorityPrivilegeRepository.findById(privilegeId).getPrivilegeDescription() + ",");
        }
        String privilege = s.toString();
        privilege = privilege.substring(0, privilege.length() - 1);
        //System.out.println("-----------权限:" + privilege);
        return privilege;
    }

    public void updateAuthority() {

    }


}


