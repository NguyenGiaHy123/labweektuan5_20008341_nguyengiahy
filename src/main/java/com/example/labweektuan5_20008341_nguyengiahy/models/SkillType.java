package com.example.labweektuan5_20008341_nguyengiahy.models;

public enum SkillType {
    UNSPECIFIC(0),
    TECHNICAL_SKILL(1),
    SOFT_SKILL(2);

    private int value;

    SkillType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
