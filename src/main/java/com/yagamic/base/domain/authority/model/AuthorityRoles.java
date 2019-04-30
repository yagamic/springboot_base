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
public class AuthorityRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date createDate;
    private Date updateDate;
    private Integer parentRoleId;//上一层角色id
    private String roleName;//角色名字
    private String roleDescription;//角色描述
    private String roleLevel;//角色等级
    private Integer roleLevelIndex;//角色等级指数

    //private Boolean controlNext;//是否可以修改下一层的权限(省级新建账号默认不可以)
    @Transient
    private String nickName;
    @Transient
    private List<AuthorityPrivilege> privilegeList;
    @Transient
    private String companyName;
    @Transient
    private String privileges;
    @Transient
    private Integer itemId;
    @Transient
    private Integer roleCount;//该角色关联了几个账号
    @Transient
    private String appKey;
    @Transient
    private String secret;
}
