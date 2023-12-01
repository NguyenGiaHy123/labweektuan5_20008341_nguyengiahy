package com.example.labweektuan5_20008341_nguyengiahy.controllers;

import com.example.labweektuan5_20008341_nguyengiahy.models.Job;
import com.example.labweektuan5_20008341_nguyengiahy.models.JobSkill;
import com.example.labweektuan5_20008341_nguyengiahy.models.SkillLevel;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.JobRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.JobSkillRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JobSkillController {
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/jobs/skills/delete")
    public ModelAndView delete(
            @RequestParam("skill-id") long skillID,
            @RequestParam("job-id") long jobID) {
        ModelAndView modelAndView = new ModelAndView();
        jobSkillRepository.delete(new JobSkill(skillRepository.findById(skillID).get(),jobRepository.findById(jobID).get()));
        modelAndView.setViewName("redirect:/show-update-job-form/"+jobID);
        return modelAndView;
    }

    @GetMapping("/show-add-job-skill-form/{id}")
    public ModelAndView showAddExperienceForm(
            @PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        JobSkill jobSkill = new JobSkill();
        modelAndView.addObject("jobSkill", jobSkill);
        modelAndView.addObject("job", jobRepository.findById(id).get());
        modelAndView.addObject("skills", skillRepository.findSkillsNotBelongToJob(id));
        modelAndView.addObject("skillLevels", SkillLevel.values());
        modelAndView.setViewName("skills/jobSkillAddForm");
        return modelAndView;
    }

    @PostMapping("/jobs/skills/add")
    public ModelAndView add(
            @ModelAttribute("jobSkill") JobSkill jobSkill,
            @ModelAttribute("job") Job job) {
        ModelAndView modelAndView = new ModelAndView();
        jobSkill.setSkill(skillRepository.findBySkillName(jobSkill.getSkill().getSkillName()));
        jobSkill.setJob(jobRepository.findById(job.getId()).get());
        jobSkillRepository.save(jobSkill);
        modelAndView.setViewName("redirect:/show-update-job-form/"+job.getId());
        return modelAndView;
    }
}
