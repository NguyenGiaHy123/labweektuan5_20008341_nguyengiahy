package com.example.labweektuan5_20008341_nguyengiahy.models;

import com.example.labweektuan5_20008341_nguyengiahy.models.pks.JobSkillID;
import jakarta.persistence.*;

@Entity
@Table(name = "job_skill")
@IdClass(JobSkillID.class)
public class JobSkill {
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;
    @Column(name = "more_infos", length = 1000)
    private String moreInfo;

    public JobSkill() {
    }

    public JobSkill(Skill skill, Job job, SkillLevel skillLevel, String moreInfo) {
        this.skill = skill;
        this.job = job;
        this.skillLevel = skillLevel;
        this.moreInfo = moreInfo;
    }

    public JobSkill(Skill skill, Job job) {
        this.skill = skill;
        this.job = job;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "JobSkill{" +
                "skill=" + skill +
                ", job=" + job +
                ", skillLevel=" + skillLevel +
                ", moreInfo='" + moreInfo + '\'' +
                '}';
    }
}
