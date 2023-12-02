package com.example.labweektuan5_20008341_nguyengiahy.controllers;

import com.example.labweektuan5_20008341_nguyengiahy.enums.UserType;
import com.example.labweektuan5_20008341_nguyengiahy.models.Address;
import com.example.labweektuan5_20008341_nguyengiahy.models.Candidate;
import com.example.labweektuan5_20008341_nguyengiahy.models.User;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.AddressRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.CandidateRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.UserRepository;
import com.example.labweektuan5_20008341_nguyengiahy.services.CandidateService;
import com.neovisionaries.i18n.CountryCode;
import jakarta.servlet.http.HttpSession;
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
@SessionAttributes("candidate-account")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateServices;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/candidates")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Candidate> candidatePage = candidateServices.findAll(currentPage - 1,
                pageSize, "id", "asc");

        model.addAttribute("candidatePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/listCandidate";
    }


    @PostMapping("/candidates/add")
    public String addCandidate(
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address,
            @ModelAttribute("user") User user) {
        addressRepository.save(address);
        candidate.setAddress(address);
        user.setUserType(UserType.CANDIDATE_USER);
        userRepository.save(user);
        candidate.setUser(user);
        candidateRepository.save(candidate);
        return "redirect:/login-candidate";
    }

    @PostMapping("/candidates/update")
    public ModelAndView updateCandidate(
            Model model,
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address,
            @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Candidate> candidate1 = candidateRepository.findById(candidate.getId());
        if (candidate1.isPresent()){
            Optional<Address> address1 = addressRepository.findById(candidate.getAddress().getId());
            if (address1.isPresent()){
                address1.get().setNumber(address.getNumber());
                address1.get().setStreet(address.getStreet());
                address1.get().setCity(address.getCity());
                address1.get().setCountry(address.getCountry());
                address1.get().setZipcode(address.getZipcode());
                addressRepository.save(address1.get());
                candidate1.get().setAddress(address1.get());
            }
            Optional<User> user1 = userRepository.findById(user.getUsername());
            if(user1.isPresent()){
                user1.get().setUserType(UserType.CANDIDATE_USER);
                user1.get().setPassword(user.getPassword());
                userRepository.save(user1.get());
                candidate1.get().setUser(user1.get());
            }
            candidate1.get().setFullName(candidate.getFullName());
            candidate1.get().setDob(candidate.getDob());
            candidate1.get().setEmail(candidate.getEmail());
            candidate1.get().setPhone(candidate.getPhone());
            candidateRepository.save(candidate1.get());
        }
        model.addAttribute("candidate-account", candidate1.get());
        modelAndView.setViewName("redirect:/candidates/info");
        return modelAndView;
    }

    @PostMapping ("/candidates/check-login")
    public String checkLogin(Model model,
                             @ModelAttribute("user") User user) {
        Candidate candidate = candidateServices.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        if (candidate!=null){
            model.addAttribute("candidate-account", candidate);
            return "redirect:/candidates/inforloginuser";
        }
        else {
            return "index";
        }
    }

    @GetMapping("/candidates/inforloginuser")
    public ModelAndView candidateInfo(
            @SessionAttribute("candidate-account") Candidate candidate) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("candidate", candidate);
        modelAndView.addObject("address", candidate.getAddress());
        modelAndView.addObject("user", candidate.getUser());
        modelAndView.addObject("countries", CountryCode.values());
        modelAndView.setViewName("/candidates/inforloginuser");
        return modelAndView;
    }


    @GetMapping("/candidates/detail/{id}")
    public ModelAndView showCandidateDetail(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidate = candidateRepository.findById(id).get();
        modelAndView.addObject("candidate", candidate);
        modelAndView.addObject("candidateExperiences", candidate.getExperiences());
        modelAndView.addObject("candidateSkills", candidate.getCandidateSkills());
        modelAndView.setViewName("candidates/candidateDetail");
        return modelAndView;
    }

    @GetMapping("/logout-candidate")
    public ModelAndView logoutCandidate(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.removeAttribute("candidate-account");
        modelAndView.setViewName("redirect:/login-candidate");
        return modelAndView;
    }
}
