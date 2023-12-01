package com.example.labweektuan5_20008341_nguyengiahy.models;

import com.example.labweektuan5_20008341_nguyengiahy.models.pks.CandidateSkillID;
import jakarta.persistence.*;

@Entity
@Table(name = "candidate_skill")
@IdClass(CandidateSkillID.class)
public class CandidateSkill {
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Id
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;
    @Column(name = "more_infos", length = 1000)
    private String moreInfo;

    public CandidateSkill() {
    }

    public CandidateSkill(Skill skill, Candidate candidate, SkillLevel skillLevel, String moreInfo) {
        this.skill = skill;
        this.candidate = candidate;
        this.skillLevel = skillLevel;
        this.moreInfo = moreInfo;
    }

    public CandidateSkill(Skill skill, Candidate candidate) {
        this.skill = skill;
        this.candidate = candidate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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
        return "CandidateSkill{" +
                "skill=" + skill +
//                ", candidate=" + candidate +
                ", skillLevel=" + skillLevel +
                ", moreInfo='" + moreInfo + '\'' +
                '}';
    }
}
