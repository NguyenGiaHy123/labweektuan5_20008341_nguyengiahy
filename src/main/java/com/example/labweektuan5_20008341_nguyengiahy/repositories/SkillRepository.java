package com.example.labweektuan5_20008341_nguyengiahy.repositories;

import com.example.labweektuan5_20008341_nguyengiahy.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("select s from Skill s where s.id not in (select c.skill.id from CandidateSkill c where c.candidate.id = :canID)")
    List<Skill> findSkillsNotBelongToCandidate(@Param("canID") long canID);

    @Query("select s from Skill s where s.id not in (select j.skill.id from JobSkill j where j.job.id = :jobID)")
    List<Skill> findSkillsNotBelongToJob(@Param("jobID") long jobID);

    Skill findBySkillName(String name);
}
