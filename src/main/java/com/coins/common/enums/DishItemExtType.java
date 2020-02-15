package com.coins.common.enums;

/**
 * 
 * @author DELL
 *
 */
public enum DishItemExtType implements EnumInterface {
    image("缩略图", 1),
    video("视频", 2),
    recommendImage("推荐图", 3),
    ;

    private String description = "";
    private int value = 0;

    DishItemExtType(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (DishItemExtType s : DishItemExtType.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static DishItemExtType fromValue(int value) {
        for (DishItemExtType s : DishItemExtType.values()) {
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
