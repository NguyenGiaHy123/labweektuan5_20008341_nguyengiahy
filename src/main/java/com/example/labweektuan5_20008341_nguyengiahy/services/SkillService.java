package com.example.labweektuan5_20008341_nguyengiahy.services;

import com.example.labweektuan5_20008341_nguyengiahy.models.Skill;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findSkillsNotBelongToCandidate(long canID){
        return skillRepository.findSkillsNotBelongToCandidate(canID);
    }

    public List<Skill> findSkillsNotBelongToJob(long jobID){
        return skillRepository.findSkillsNotBelongToJob(jobID);
    }

    public Skill findBySkillName(String name){
        return skillRepository.findBySkillName(name);
    }
}
