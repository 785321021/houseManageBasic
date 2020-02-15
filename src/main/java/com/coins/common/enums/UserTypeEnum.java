package com.coins.common.enums;

/**
 * Created by seba on 16/7/13.
 */
public enum UserTypeEnum implements EnumInterface {
    Normal("普通注册用户", 1),
    Discovery_Official("发现官方账户", 2),
    Vest("马甲号", 3),
    StoreAnonymousUser("线下店匿名用户", 4);


    private String description = "";
    private int value = 0;

    UserTypeEnum(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (UserTypeEnum s : UserTypeEnum.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static UserTypeEnum fromValue(int value) {
        for (UserTypeEnum s : UserTypeEnum.values()) {
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
