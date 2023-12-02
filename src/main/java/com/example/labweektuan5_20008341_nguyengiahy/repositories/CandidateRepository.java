package com.example.labweektuan5_20008341_nguyengiahy.repositories;

import com.example.labweektuan5_20008341_nguyengiahy.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByUserUsernameAndUserPassword(String username, String password);

    @Query("SELECT c FROM Candidate c WHERE c.id IN (SELECT cs.candidate.id FROM CandidateSkill cs WHERE cs.skill.id  IN (SELECT js.skill.id FROM JobSkill js WHERE js.job.id = :job))")
    List<Candidate> findProposedCandidates(@Param("job") long jobID);
}
