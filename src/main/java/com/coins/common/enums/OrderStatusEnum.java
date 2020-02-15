package com.coins.common.enums;

public enum OrderStatusEnum implements EnumInterface {
    NoPay("未支付", 1),
    Finish("已完成", 2),
    Cancel("取消", 3),
    Diff("差异", 4),
    ;

    private String description = "";
    private int value = 0;

    private OrderStatusEnum(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (OrderStatusEnum s : OrderStatusEnum.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static OrderStatusEnum fromValue(int value) {
        for (OrderStatusEnum s : OrderStatusEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}