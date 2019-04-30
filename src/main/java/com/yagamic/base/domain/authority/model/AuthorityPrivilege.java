package com.yagamic.base.domain.authority.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityPrivilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date createDate;
    private Date updateDate;
    private Integer parentPrivilegeId;//父级权限id
    private Boolean enable;//是否可用
    private String privilegeName;//权限名字
    private String privilegeDescription;//权限描述

    @Transient
    private List<AuthorityRoles> authorityRolesList;
    @Transient
    private List<AuthorityPrivilegeChildren> children;

}
