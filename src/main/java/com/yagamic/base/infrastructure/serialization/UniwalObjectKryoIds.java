package com.yagamic.base.infrastructure.serialization;

/**
 * kryo中类型要对应一个值，为避免重复，统一由此枚举提供
 * 添加新类型时要追加到后边，不要插入，否则可能造成原序列化的对象无法反序列化
 * 不要删除类型，可以修改
 * Created by xiemeilong on 15-8-20.
 */
public enum UniwalObjectKryoIds {
    inventory,
    release,
    customers;


    public int getValue() {
        return ordinal()+1987;
    }  //避免碰撞
}
