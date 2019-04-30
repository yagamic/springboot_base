package com.yagamic.base.domain.account.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2017/9/21.
 */
@Getter
@Setter
@ToString
public class ResetAccount {

    String username;
    String oldPassword;
    String newPassword;

}
