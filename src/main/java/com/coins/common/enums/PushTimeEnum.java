package com.coins.common.enums;

public enum PushTimeEnum implements EnumInterface {
	TWELVE(12, "12点"), 
	THIRTEEN(13, "13点"), 
	FOURTEEN(14, "14点"), 
	FIFTEEN(15, "15点"), 
	SIXTEEN(16, "16点"), 
	SEVENTEEN(17, "17点"), 
	EIGHTEEN(18, "18点"), 
	NINETEEN(19, "19点"), 
	TWENTY(20, "20点"), 

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
    
    private PushTimeEnum(int _value, String _desc) {
        this.description = _desc;
        this.value = _value;
    }
    
    public static PushTimeEnum valueOfType(int value) {
        for (PushTimeEnum type : PushTimeEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
