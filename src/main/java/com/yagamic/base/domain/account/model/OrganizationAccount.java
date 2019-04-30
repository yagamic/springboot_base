package com.yagamic.base.domain.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2017/7/12.
 */
@Getter
@Setter
@ToString
public class OrganizationAccount {

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private String account;
    private String city;
    private Boolean isCity;
    private String county;
    private String type;
    private Integer roleId;
    private String people;
    private String department;
    private String job;
    private String phone;
    private Boolean enable;


}
