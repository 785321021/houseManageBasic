package com.coins.common.enums;

public enum AppTypeEnum  implements EnumInterface{
	FEND_MANAGE("局气管理后台", 1),
    ;

    private String description = "";
    private int value = 0;

    private AppTypeEnum(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (AppTypeEnum s : AppTypeEnum.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static AppTypeEnum fromValue(int value) {
        for (AppTypeEnum s : AppTypeEnum.values()) {
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