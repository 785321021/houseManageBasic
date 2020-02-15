package com.coins.common.enums;

public enum ShiftEnum implements EnumInterface {
    Morning(1, "早市"), 
    Noon(2, "午市"), 
    Evening(3, "晚市"), 
    ;
    
    private int value = 0;
    private String description = "";
    
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
    
    private ShiftEnum(int _value, String _desc) {
        this.description = _desc;
        this.value = _value;
    }
    
    public static ShiftEnum valueOfType(int value) {
        for (ShiftEnum type : ShiftEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
