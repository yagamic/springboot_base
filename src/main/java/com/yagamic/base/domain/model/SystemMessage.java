package com.yagamic.base.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import lombok.*;

import javax.persistence.*;
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
public class SystemMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private String content;
    private String detailContent;
    private Date createDate;
    private Date updateDate;
    private Long createBy;
    private String owner;
    @Enumerated(value = EnumType.STRING)
    private MessageType messageType;
    private Long receiver;


}
