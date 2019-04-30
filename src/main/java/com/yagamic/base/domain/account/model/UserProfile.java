package com.yagamic.base.domain.account.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/18.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    //用户配置文件
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private Date createDate;
    private Date updateDate;
    private Integer age;
    private String company;
    private String firstName;
    private Integer gender;
    private String lastName;
    private String location;
    private String mobile;
    private String phone;
    private String qq;
    private String realName;
    private String department;
    private String jobPosition;//工作岗位
}
