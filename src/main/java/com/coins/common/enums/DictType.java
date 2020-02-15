package com.coins.common.enums;

/**
 * 
 * 数据字典表类型
 */
public enum DictType  implements EnumInterface {
    MENU_COLUMN("菜谱栏目",1),
    MENU_TEMPLET("菜谱模板",2),
    DISH_PUSH_TIME("急推菜时间",3),
    MAKE_TIME("制作时长",4),
    DISH_TASTE("菜品口味",5),
    APP_VERSION("APP版本",6),
    ;

    private String description = "";
    private int value = 0;

    private DictType(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (DictType s : DictType.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static DictType fromValue(int value) {
        for (DictType s : DictType.values()) {
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