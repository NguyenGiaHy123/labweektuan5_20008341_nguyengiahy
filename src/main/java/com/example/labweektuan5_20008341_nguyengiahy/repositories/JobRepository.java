package com.example.labweektuan5_20008341_nguyengiahy.repositories;

import com.example.labweektuan5_20008341_nguyengiahy.models.Company;
import com.example.labweektuan5_20008341_nguyengiahy.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j WHERE j.id IN (SELECT js.job.id FROM JobSkill js WHERE js.skill.id  IN (SELECT cs.skill.id FROM CandidateSkill cs WHERE cs.candidate.id = :candidate))")
    List<Job> findProposedJobs(@Param("candidate") long candidateID);

    List<Job> findJobsByCompany(Company company);
}
