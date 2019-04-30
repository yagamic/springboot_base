package com.yagamic.base.domain.authority.model;

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
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private Date createDate;
    private Date updateDate;
    private Integer roleId;
    private String authority;
    private String username;



}
