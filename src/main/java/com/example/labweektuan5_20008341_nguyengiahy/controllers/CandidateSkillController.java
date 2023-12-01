package com.example.labweektuan5_20008341_nguyengiahy.controllers;

import com.example.labweektuan5_20008341_nguyengiahy.models.Candidate;
import com.example.labweektuan5_20008341_nguyengiahy.models.CandidateSkill;
import com.example.labweektuan5_20008341_nguyengiahy.models.Skill;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.CandidateRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.CandidateSkillRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.SkillRepository;
import com.example.labweektuan5_20008341_nguyengiahy.services.CandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CandidateSkillController {
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/candidates/skills")
    public ModelAndView showCandidateSkillList(
            @SessionAttribute("candidate-account") Candidate candidate) {
        ModelAndView modelAndView = new ModelAndView();
        List<CandidateSkill> candidateSkills = candidateSkillService.findCandidateSkillByCandidate(candidate);
        modelAndView.addObject("candidateSkills", candidateSkills);
        modelAndView.setViewName("skills/candidateSkills");
        return modelAndView;
    }

    @GetMapping("/candidates/skills/delete")
    public ModelAndView delete(
            @RequestParam("skill-id") long skillID,
            @RequestParam("can-id") long canID) {
        ModelAndView modelAndView = new ModelAndView();
        candidateSkillRepository.delete(new CandidateSkill(skillRepository.findById(skillID).get(),candidateRepository.findById(canID).get()));
        modelAndView.setViewName("redirect:/candidates/skills");
        return modelAndView;
    }

    @PostMapping ("/candidates/skills/add")
    public ModelAndView add(
            @ModelAttribute("candidateSkill") CandidateSkill candidateSkill,
            @ModelAttribute("skill") Skill skill,
            @SessionAttribute("candidate-account") Candidate candidate) {
        ModelAndView modelAndView = new ModelAndView();
        candidateSkill.setSkill(skillRepository.findById(skill.getId()).get());
        candidateSkill.setCandidate(candidateRepository.findById(candidate.getId()).get());
        candidateSkillRepository.save(candidateSkill);
        modelAndView.setViewName("redirect:/candidates/skills");
        return modelAndView;
    }
}
