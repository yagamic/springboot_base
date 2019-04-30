package com.yagamic.base.infrastructure.utils;

/**
 * Created by limengsheng on 2016/8/13.
 */
public enum TimeConstant {
    HOUR(3600000),
    DAY(24 * 3600000);

    private long milliseconds;

    TimeConstant(long milliseconds) {
        this.milliseconds = milliseconds;
    }
}
