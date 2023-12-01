package com.example.labweektuan5_20008341_nguyengiahy.enums;

public enum SkillLevel {
    BEGINNER(0), ADVANCED(1), PROFESSIONAL(2), INTERMEDIATE(3), MASTER(4);
    private final int code;

    SkillLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
