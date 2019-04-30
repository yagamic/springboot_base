package com.yagamic.base.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
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
public class UploadFiles implements Serializable {
 /*   @EmbeddedId
    private UploadFilesId id;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String fileName;
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long ownerId;

    private String realName;
    private Date createDate;
    private String fileType;



/*
    @Embeddable
    @Getter
    @Setter
    public static class UploadFilesId implements Serializable {
        private Long ownerId;
        private String fileName;
    }*/
}
