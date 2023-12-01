package com.example.labweektuan5_20008341_nguyengiahy.services;

import com.example.labweektuan5_20008341_nguyengiahy.models.Candidate;
import com.example.labweektuan5_20008341_nguyengiahy.models.CandidateSkill;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.CandidateSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateSkillService {
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public List<CandidateSkill> findCandidateSkillByCandidate(Candidate candidate){
        return candidateSkillRepository.findCandidateSkillByCandidate(candidate);
    }
}
