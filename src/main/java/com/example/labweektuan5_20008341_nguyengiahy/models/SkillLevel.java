package com.example.labweektuan5_20008341_nguyengiahy.models;

public enum SkillLevel {
    BEGINER(0),
    IMTERMEDIATE(1),
    ADVANCED(2),
    PROFESSIONAL(3),
    MASTER(4);

    private int value;

    SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
