package com.yagamic.base.domain.authority.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2017/7/12.
 */
@Getter
@Setter
@ToString
public class AuthorityPrivilegeChildren {
    private Integer id;
    private Integer parentPrivilegeId;
    private Boolean enable;//是否可用
    private String childrenPrivilegeName;//权限名字
    private String childrenPrivilegeDescription;//权限描述
}
