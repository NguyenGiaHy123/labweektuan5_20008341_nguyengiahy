package com.example.labweektuan5_20008341_nguyengiahy.controllers;

import com.example.labweektuan5_20008341_nguyengiahy.models.*;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.JobRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.JobSkillRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.SkillRepository;
import com.example.labweektuan5_20008341_nguyengiahy.services.CandidateService;
import com.example.labweektuan5_20008341_nguyengiahy.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/jobs")
    public String showJobListPaging(Model model,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Job> jobPage = jobService.findAll(currentPage - 1, pageSize, "id", "asc");

        model.addAttribute("jobPage", jobPage);
        int totalPage = jobPage.getTotalPages();
        if (totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "jobs/listJob";
    }

    @GetMapping("/jobs/detail/{id}")
    public ModelAndView showJobDetail(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Job job = jobRepository.findById(id).get();
        modelAndView.addObject("job", job);
        modelAndView.addObject("company", job.getCompany());
        modelAndView.addObject("jobSkills", job.getJobSkills());
        modelAndView.setViewName("jobs/jobDetail");
        return modelAndView;
    }



    @GetMapping("/jobs/delete/{id}")
    public ModelAndView delete(
            @PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        jobRepository.deleteById(id);
        modelAndView.setViewName("redirect:/companies/jobs");
        return modelAndView;
    }

    @GetMapping("/show-update-job-form/{id}")
    public ModelAndView showUpdateJobForm(
            @PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Job> job = jobRepository.findById(id);
        modelAndView.addObject("job", job.get());
        modelAndView.setViewName("jobs/jobUpdateForm");
        return modelAndView;
    }

    @PostMapping("/jobs/update")
    public ModelAndView update(
            @ModelAttribute("job") Job job,
            @SessionAttribute("company-account") Company company) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Job> job1 = jobRepository.findById(job.getId());
        if (job1.isPresent()){
            job1.get().setName(job.getName());
            job1.get().setDescription(job.getDescription());
            job1.get().setCompany(company);
            jobRepository.save(job1.get());
        }
        modelAndView.setViewName("redirect:/show-update-job-form/"+job.getId());
        return modelAndView;
    }

    @GetMapping("/show-add-job-form")
    public ModelAndView showAddJobForm() {
        ModelAndView modelAndView = new ModelAndView();
        Job job = new Job();
        List<Skill> skills = skillRepository.findAll();
        modelAndView.addObject("job", job);
        modelAndView.addObject("skills", skills);
        modelAndView.setViewName("jobs/jobAddForm");
        return modelAndView;
    }

    @PostMapping ("/jobs/add")
    public ModelAndView add(
            @ModelAttribute("job") Job job,
            @RequestParam(value = "skillsOfJob" , required = false) long[] skillsOfJob,
            @SessionAttribute("company-account") Company company) {
        ModelAndView modelAndView = new ModelAndView();
        job.setCompany(company);
        jobRepository.save(job);
        for (long skill: skillsOfJob){
            JobSkill jobSkill = new JobSkill(skillRepository.findById(skill).get(),job,SkillLevel.BEGINER,"None");
            jobSkillRepository.save(jobSkill);
        }
        modelAndView.setViewName("redirect:/companies/jobs");
        return modelAndView;
    }


}
