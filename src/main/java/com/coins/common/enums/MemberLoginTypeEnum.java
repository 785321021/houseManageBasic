package com.coins.common.enums;

public enum MemberLoginTypeEnum implements EnumInterface {
    Arrive("到店开台", 0),
    Leave("结账离开", 1),
    ;

    private String description = "";
    private int value = 0;

    private MemberLoginTypeEnum(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (MemberLoginTypeEnum s : MemberLoginTypeEnum.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static MemberLoginTypeEnum fromValue(int value) {
        for (MemberLoginTypeEnum s : MemberLoginTypeEnum.values()) {
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