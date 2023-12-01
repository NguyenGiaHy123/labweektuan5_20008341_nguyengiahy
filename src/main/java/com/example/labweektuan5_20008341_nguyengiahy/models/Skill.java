package com.example.labweektuan5_20008341_nguyengiahy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long id;
    @Column(name = "skill_name", nullable = false, length = 150)
    private String skillName;
    @Column(name = "skill_type", nullable = false)
    private SkillType type;
    @Column(name = "skill_desc", nullable = false, length = 1000)
    private String skillDescription;
    @OneToMany(mappedBy = "skill")
    private List<JobSkill> jobSkills;

    public Skill() {
    }

    public Skill(long id, String skillName, SkillType type, String skillDescription, List<JobSkill> jobSkills) {
        this.id = id;
        this.skillName = skillName;
        this.type = type;
        this.skillDescription = skillDescription;
        this.jobSkills = jobSkills;
    }

    public Skill(String skillName, SkillType type, String skillDescription, List<JobSkill> jobSkills) {
        this.skillName = skillName;
        this.type = type;
        this.skillDescription = skillDescription;
        this.jobSkills = jobSkills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public List<JobSkill> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(List<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                ", type=" + type +
                ", skillDescription='" + skillDescription + '\'' +
                ", jobSkills=" + jobSkills +
                '}';
    }
}
