package com.assigment.enums;


public enum ResponseEnum {


    ERROR("ERROR"),
    SUCCESS("SUCCESS"),
    EXCEPTION("SYS_ERR_01"),
    EMPLOYEE_FETCHED_FAILED("EMPLOYEE_ERR_01");


    private String value;

    ResponseEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
