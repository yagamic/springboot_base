package com.yagamic.base.domain.account.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserInfo {

    private String name;

    private String tel;
    private String delivery;
    private String addr;
    private String remark;

}
