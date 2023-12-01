package com.example.labweektuan5_20008341_nguyengiahy.controllers;

import com.example.labweektuan5_20008341_nguyengiahy.models.Candidate;
import com.example.labweektuan5_20008341_nguyengiahy.models.CandidateSkill;
import com.example.labweektuan5_20008341_nguyengiahy.models.Skill;
import com.example.labweektuan5_20008341_nguyengiahy.models.SkillLevel;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.SkillRepository;
import com.example.labweektuan5_20008341_nguyengiahy.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class SkillController {
    @Autowired
    private SkillService skillService;
    @Autowired
    private SkillRepository skillRepository;
    @GetMapping("/skills")
    public ModelAndView showExperienceList(
            @SessionAttribute("candidate-account") Candidate candidate) {
        ModelAndView modelAndView = new ModelAndView();
        List<Skill> skills = skillService.findSkillsNotBelongToCandidate(candidate.getId());
        modelAndView.addObject("skills", skills);
        modelAndView.setViewName("skills/skills");
        return modelAndView;
    }

    @GetMapping("/show-add-candidate-skill-form/{id}")
    public ModelAndView showAddExperienceForm(
            @PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        CandidateSkill candidateSkill = new CandidateSkill();
        modelAndView.addObject("candidateSkill", candidateSkill);
        modelAndView.addObject("skill", skillRepository.findById(id).get());
        modelAndView.addObject("skillLevels", SkillLevel.values());
        modelAndView.setViewName("skills/candidateSkillAddForm");
        return modelAndView;
    }
}
