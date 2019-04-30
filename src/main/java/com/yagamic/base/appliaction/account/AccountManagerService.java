package com.yagamic.base.appliaction.account;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.model.ResetAccount;
import com.yagamic.base.domain.account.repository.AccountRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api("帐号管理")
@RestController
@RequestMapping("/api/account")
public class AccountManagerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @ApiOperation(value = "修改帐号")
    @RequestMapping(value = "/reset/password", method = RequestMethod.POST)
    public List<String> updateAccount(@RequestBody ResetAccount resetAccount) {//通过帐号 密码  修改密码


        List<String> list = new ArrayList<>();
        Account account = accountRepository.findByName(resetAccount.getUsername());
        if (account == null) {
            //帐号不存在  --正常是存在的
        } else {
            String password = resetAccount.getOldPassword();
            //要加密
            //String encodePassword = passwordEncoder.encode(password);
            if (passwordEncoder.matches(password, account.getPassword())) {
                String newP = resetAccount.getNewPassword();
                account.setPassword(passwordEncoder.encode(newP));
                accountRepository.update(account);
                list.add("SUCCESS");
            } else {
                list.add("FAIL");
            }
        }
        return list;
    }

    @ApiOperation(value = "检查帐号是否存在")
    @RequestMapping(value = "check/has", method = RequestMethod.GET)
    public List<String> checkIfHas(@RequestParam String username) {
        List<String> list = new ArrayList<>();
        Account account = accountRepository.findByName(username);
        if (account == null) {
            list.add("SUCCESS");//帐号不存在时允许新增账号
        } else {
            list.add("FAIL");
        }
        return list;
    }

    @ApiOperation(value = "检查是否登陆")
    @RequestMapping(value = "check/login", method = RequestMethod.GET)
    public Map checkLogin() {
        return new HashMap<>();
    }


    public String checkLength(String str) {
        if (str.length() < 16) {
            return str;
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(str.substring(0, 15));
            sb.append("...");
            return sb.toString();
        }
    }

}
