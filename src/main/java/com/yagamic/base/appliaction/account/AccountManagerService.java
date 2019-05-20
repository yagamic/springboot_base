package com.yagamic.base.appliaction.account;

import com.yagamic.base.domain.account.model.Account;
import com.yagamic.base.domain.account.model.BasicProgress;
import com.yagamic.base.domain.account.model.ResetAccount;
import com.yagamic.base.domain.account.model.UserInfo;
import com.yagamic.base.domain.account.repository.AccountRepository;
import com.yagamic.base.domain.model.AuctionMaster;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;
    private static Integer a = 1;

    @ApiOperation(value = "获取简历信息")
    @RequestMapping(value = "personal/detail", method = RequestMethod.GET)
    public UserInfo getDetail(@RequestParam(required = false) String mobile) {
        UserInfo userInfo = UserInfo.builder()
                .name("cz")
                .tel("18556665695")
                .delivery("顺丰")
                .addr("无锡")
                .remark(a.toString()).build();
        a++;
        System.out.println("userInfo: " + userInfo.toString());
        return userInfo;
    }
    @ApiOperation(value = "获取简历信息")
    @RequestMapping(value = "personal/detail2", method = RequestMethod.GET)
    public List<BasicProgress> getDetail2(@RequestParam(required = false) String mobile) {
        BasicProgress basicProgress = BasicProgress.builder()
                .time("今天")
                .rate("处理中")
                .status("processing")
                .operator(a.toString())
                .cost(a.toString())
                .build();
        a++;
        List<BasicProgress> list = new ArrayList<BasicProgress>();
        list.add(basicProgress);
        System.out.println("list: " + list.toString());
        return list;
    }

    @ApiOperation(value = "修改帐号")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public AuctionMaster updateAccount(@RequestParam(required = false) Long id) {//通过帐号 密码  修改密码
      return  restTemplate.getForObject("http://localhost:8080/api/auction/manager/edit?id="+id,AuctionMaster.class);

    }

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
