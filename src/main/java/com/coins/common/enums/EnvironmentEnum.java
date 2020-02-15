package com.coins.common.enums;

/**
 * 项目
 */
public enum EnvironmentEnum  {
    DEV("dev", "开发"),
    QA("qa", "测试"),
    ONLINE("online", "线上");

    private String value = "";
    private String description = "";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private EnvironmentEnum(String _value, String _desc) {
        this.description = _desc;
        this.value = _value;
    }

    public static EnvironmentEnum valueOfType(String value) {
        for (EnvironmentEnum type : EnvironmentEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
