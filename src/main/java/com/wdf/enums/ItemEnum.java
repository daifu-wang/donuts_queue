package com.wdf.enums;

public enum ItemEnum {

    DONUT(1,"donut"),
    CAKE(2,"cake");

    private Integer type;
    private String name;

    ItemEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
