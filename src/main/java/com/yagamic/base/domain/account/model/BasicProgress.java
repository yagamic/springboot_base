package com.yagamic.base.domain.account.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BasicProgress {

    private String time;
    private String rate;
    private String status;
    private String operator;
    private String cost;
}
