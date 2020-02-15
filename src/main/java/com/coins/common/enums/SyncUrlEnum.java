package com.coins.common.enums;

public enum SyncUrlEnum {
    CORP("门店", "CORP"),
    EMP("员工", "EMP"),
    SALESPLAN("销售方案", "SALESPLAN"),
    MEMPLAN("会员方案", "MEMPLAN"),
    ITEM("菜品", "ITEM"),
    ITEMCLASS("菜品类别", "ITEMCLASS"),
    PAYWAY("支付方式", "PAYWAY"),
    AREA("消费区域", "AREA"),
    MO("制作方式", "MO"),
    MOCLASS("制作方式类别", "MOCLASS"),
    CANCELREASON("退单原因", "CANCELREASON"),
    REMARK("整单备注", "REMARK"),
    RISE("起菜方式", "RISE"),
    BILL("订单", "BILL"),
    BILLITEM("订单品项", "BILLITEM"),
    BILLPAY("订单支付信息", "BILLPAY"),
    CARDTYPE("会员卡类型", "CARDTYPE"),
    ITEMRP("可替换品项明细", "ITEMRP"),
    ITEMSM("品项套餐明细", "ITEMSM"),
    ;

    private String description = "";
    private String value = "";

    private SyncUrlEnum(String _name, String _index) {
        this.description = _name;
        this.value = _index;
    }

    public static SyncUrlEnum fromValue(String value) {
        for (SyncUrlEnum s : SyncUrlEnum.values()) {
            if (s.getValue().equals(value)) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}