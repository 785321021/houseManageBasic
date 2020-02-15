package com.coins.common.enums;
public enum CacheKeyEnum implements EnumInterface {
    DictInfo("dictInfo",1),
    ;

    private String description = "";
    private int value = 0;

    private CacheKeyEnum(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (CacheKeyEnum s : CacheKeyEnum.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static CacheKeyEnum fromValue(int value) {
        for (CacheKeyEnum s : CacheKeyEnum.values()) {
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