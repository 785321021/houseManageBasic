package com.coins.common.enums;

public enum YesNoEnum implements EnumInterface {
    No(0, "否"), Yes(1, "是");
    
    private int value = 0;
    private String description = "";
    
    public String getDescription() {
        return description;
    }
    // 普通方法
    public static String getDescription(int _value) {
        for (YesNoEnum s : YesNoEnum.values()) {
            if (s.value == _value) {
                return s.description;
            }
        }
        return null;
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
    
    private YesNoEnum(int _value, String _desc) {
        this.description = _desc;
        this.value = _value;
    }
    
    public static YesNoEnum valueOfType(int value) {
        for (YesNoEnum type : YesNoEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
