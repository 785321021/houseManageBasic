package com.coins.common.enums;

public enum SyncTypeEnum implements EnumInterface {
    CORP("门店", 1),
    EMPLOYEE("员工", 2),
    SalePlan("销售方案", 3),
    ITEM("菜品", 4),
    ITEMCLASS("菜品类别", 5),
    PAYWAY("支付方式", 6),
    AREA("消费区域", 7),
    MO("制作方式", 8),
    MOCLASS("制作方式类别", 9),
    CANCELREASON("退单原因", 10),
    REMARK("整单备注", 11),
    RISE("起菜方式", 12),
    BILL("订单", 13),
    BILLITEM("订单品项", 14),
    BILLPAY("订单支付信息", 15),
    CARDTYPE("会员卡类型", 16),
    ITEMSM("品项套餐明细",17),
    ITEMRP("可替换品项明细",18)
    ;

    private String description = "";
    private int value = 0;

    private SyncTypeEnum(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (SyncTypeEnum s : SyncTypeEnum.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static SyncTypeEnum fromValue(int value) {
        for (SyncTypeEnum s : SyncTypeEnum.values()) {
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