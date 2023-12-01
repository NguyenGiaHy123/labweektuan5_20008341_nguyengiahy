package com.example.labweektuan5_20008341_nguyengiahy.enums;

public enum UserType {
    COMPANY_USER(0),
    CANDIDATE_USER(1);

    private int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
