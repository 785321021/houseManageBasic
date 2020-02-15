package com.coins.common.enums;

/**
 * 用户职位
 */
public enum UserPosition implements EnumInterface {

	president(1091, "总经理"),
    Director(1092, "经理"),
    FinancialExecutive(1093, "财务主管"),
    CashierDirector(1095, "收银主管"),
    Chef(1097, "厨师长"),
    Kitchener(1098, "厨师"),
	Cashier(1099, "收银员"),
	pWaiter(1100, "点菜员"),
	dWaiter(1101, "传菜员"),
	Greeter(1102, "迎宾员"),
	Networker(1103, "网管")
	
	;

    private String description = "";
    private int value = 0;

    private UserPosition(int _value, String _desc) {
        this.description = _desc;
        this.value = _value;
    }

    // 普通方法
    public static String getDescription(int _value) {
        for (UserPosition s : UserPosition.values()) {
            if (s.value == _value) {
                return s.description;
            }
        }
        return null;
    }

    /**
     * 获取枚举
     *
     * @param value
     * @return
     */
    public static UserPosition fromValue(int value) {
        for (UserPosition s : UserPosition.values()) {
            if (s.value == value) {
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
