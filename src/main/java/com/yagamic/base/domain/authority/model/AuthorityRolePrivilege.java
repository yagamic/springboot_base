package com.yagamic.base.domain.authority.model;

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
public class AuthorityRolePrivilege {
    //@JsonSerialize(using = LongToStringSerializer.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private Integer privilegeId;//权限
    private Date createDate;
    private Date updateDate;

}
