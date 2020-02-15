package com.coins.common.enums;

public enum DishItemClassEnum implements EnumInterface {
    Dish(1, "菜品"),
    Drink(2, "饮品"),
    Dishes(3, "餐具"),
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
    
    private DishItemClassEnum(int _value, String _desc) {
        this.description = _desc;
        this.value = _value;
    }
    
    public static DishItemClassEnum valueOfType(int value) {
        for (DishItemClassEnum type : DishItemClassEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
