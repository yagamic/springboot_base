package com.yagamic.base.domain.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/7/24.
 */
@Getter
@Setter
public class AccountDetail {
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private String username;
    private String city;
    private String county;
    private Integer roleId;
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long userProfileId;//关联用户配置文件

    private String person;
    private String department;
    private String jobPosition;
    private String phone;

}
