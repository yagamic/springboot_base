package com.yagamic.base.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yagamic.base.appliaction.util.LongToStringSerializer;
import lombok.*;

import javax.persistence.Transient;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionMaster {

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;

    private String auctionName;// varchar(100),#拍品名称
    private Integer auctionStatus;// int(1),#拍品状态：0 等待拍卖，1 正在拍卖，2 拍卖结束,3 支付完成,4 留拍
    private Integer audit;// 是否允许拍卖 0 待审核 ，1 审核通过 2 审核未通过
    private String failReason;//审核失败理由

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long belongTo;//归属 为null 则是管理员上架
    private String description;// varchar(100),#描述
    private Date startDate;//  date,#拍卖开始时间
    private Date endDate;//	 date,#拍卖结束时间
    private Double minPrice;//   double(16,2),#最低起拍价
    private Double minMarkup;//	 double(16,2),#最低加价
    private Double currentPrice;// double(16,2),#当前价格
    private String auctionPlace;//  varchar(100),#拍卖地点
    private Double expertValuationPriceMin;//,#专家估价价格
    private Double expertValuationPriceMax;//,#专家估价价格
    private String picture;
    private Date insertDate;// date,
    private Date updateDate;// date
    private Boolean isPay;//是否付款()

    @Transient
    private String username;//竞拍归属人名字
    @Transient
    private Double myPrice = 0D;//我的出价

    @Transient
    private String startDateStr;//  date,#拍卖开始时间
    @Transient
    private String endDateStr;
    @Transient
    private Long endLong;//结束时间的毫秒数
    @Transient
    private String yourPrice;//你的出价
    @Transient
    private Double nextMinPrice;//下次最低出价
    @Transient
    private Double price1;//快速出价1
    @Transient
    private Double price2;//快速出价2
    @Transient
    private Double price3;//快速出价3
    @Transient
    private Boolean isShootOut;//是否拍出


}