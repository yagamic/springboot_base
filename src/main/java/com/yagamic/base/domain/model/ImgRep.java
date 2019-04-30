package com.yagamic.base.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/5/18.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private String fileName;
    private String fileType;
    private String plateNo;
    private String plateColor;


}
