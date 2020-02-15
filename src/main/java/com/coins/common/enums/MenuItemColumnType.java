package com.coins.common.enums;

public enum MenuItemColumnType implements EnumInterface {
    Recommend(1, "推荐"),
    Drink(10, "饮品"),
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
    
    private MenuItemColumnType(int _value, String _desc) {
        this.description = _desc;
        this.value = _value;
    }
    
    public static MenuItemColumnType valueOfType(int value) {
        for (MenuItemColumnType type : MenuItemColumnType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
