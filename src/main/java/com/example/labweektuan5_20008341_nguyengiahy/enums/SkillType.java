package com.example.labweektuan5_20008341_nguyengiahy.enums;

public enum SkillType {
    TECHNICAL_SKILL(0), SOFT_SKILL(1), UNSPECIFIC(2);
    private final int code;

    SkillType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
