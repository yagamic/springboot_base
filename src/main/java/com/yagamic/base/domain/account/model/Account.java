package com.yagamic.base.domain.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import com.yagamic.base.domain.authority.model.AuthorityRoles;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/17.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private Date createDate;
    private Date updateDate;
    private Boolean admin;
    private String area;
    private String email;
    private Boolean enable;
    private String password;
    private String username;
    private String realName;
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long userProfileId;//关联用户配置文件

    @Transient
    private AuthorityRoles authorityRoles;
    @Transient
    private String nickName;
    @Transient
    private String company;
    @Transient
    private Boolean remember;
}
