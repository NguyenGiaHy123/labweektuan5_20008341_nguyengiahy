package com.example.labweektuan5_20008341_nguyengiahy.controllers;

import com.example.labweektuan5_20008341_nguyengiahy.enums.UserType;
import com.example.labweektuan5_20008341_nguyengiahy.models.Address;
import com.example.labweektuan5_20008341_nguyengiahy.models.Company;
import com.example.labweektuan5_20008341_nguyengiahy.models.Job;
import com.example.labweektuan5_20008341_nguyengiahy.models.User;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.AddressRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.CompanyRepository;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.UserRepository;
import com.example.labweektuan5_20008341_nguyengiahy.services.CompanyService;
import com.example.labweektuan5_20008341_nguyengiahy.services.JobService;
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
@SessionAttributes("company-account")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobService jobService;

    @GetMapping("/login-company")
    public ModelAndView loginCompany(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("companies/loginCompany");
        return modelAndView;
    }

    @PostMapping("/companies/check-login")
    public String checkLogin(Model model,
                             @ModelAttribute("user") User user) {
        Company company = (Company) companyService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (company!=null){
            model.addAttribute("company-account", company);
            return "redirect:/companies/info";
        }
        else {
            return "redirect:/login-company";
        }
    }

    @GetMapping("/register-company")
    public ModelAndView registerCompany(){
        ModelAndView modelAndView = new ModelAndView();
        Company company = new Company();
        company.setAddress(new Address());
        company.setUser(new User());
        modelAndView.addObject("company", company);
        modelAndView.addObject("address", company.getAddress());
        modelAndView.addObject("user", company.getUser());
        modelAndView.addObject("countries", CountryCode.values());
        modelAndView.setViewName("companies/registerCompany");
        return modelAndView;
    }

    @PostMapping("/companies/add")
    public String addCompany(
            @ModelAttribute("company") Company company,
            @ModelAttribute("address") Address address,
            @ModelAttribute("user") User user) {
        addressRepository.save(address);
        company.setAddress(address);
        user.setUserType(UserType.COMPANY_USER);
        userRepository.save(user);
        company.setUser(user);
        companyRepository.save(company);
        return "redirect:/login-company";
    }

    @GetMapping ("/companies/info")
    public ModelAndView candidateInfo(
            @SessionAttribute("company-account") Company company) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("company", company);
        modelAndView.addObject("address", company.getAddress());
        modelAndView.addObject("user", company.getUser());
        modelAndView.addObject("countries", CountryCode.values());
        modelAndView.setViewName("companies/companyInformation");
        return modelAndView;
    }

    @PostMapping("/companies/update")
    public ModelAndView updateCompany(
            Model model,
            @ModelAttribute("company") Company company,
            @ModelAttribute("address") Address address,
            @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Company> company1 = companyRepository.findById(company.getId());
        if (company1.isPresent()){
            Optional<Address> address1 = addressRepository.findById(company.getAddress().getId());
            if (address1.isPresent()){
                address1.get().setNumber(address.getNumber());
                address1.get().setStreet(address.getStreet());
                address1.get().setCity(address.getCity());
                address1.get().setCountry(address.getCountry());
                address1.get().setZipcode(address.getZipcode());
                addressRepository.save(address1.get());
                company1.get().setAddress(address1.get());
            }
            Optional<User> user1 = userRepository.findById(user.getUsername());
            if(user1.isPresent()){
                user1.get().setUserType(UserType.COMPANY_USER);
                user1.get().setPassword(user.getPassword());
                userRepository.save(user1.get());
                company1.get().setUser(user1.get());
            }
            company1.get().setName(company.getName());
            company1.get().setAbout(company.getAbout());
            company1.get().setEmail(company.getEmail());
            company1.get().setPhone(company.getPhone());
            company1.get().setWebURL(company.getWebURL());
            companyRepository.save(company1.get());
        }
        model.addAttribute("company-account", company1.get());
        modelAndView.setViewName("redirect:/companies/info");
        return modelAndView;
    }

    @GetMapping("/companies/jobs")
    public String showJobOfCompany(Model model,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @SessionAttribute("company-account") Company company) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Job> jobPage = jobService.findJobsByCompanyPaginated(currentPage - 1, pageSize, company);

        model.addAttribute("jobPage", jobPage);
        int totalPage = jobPage.getTotalPages();
        if (totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "jobs/listJobOfCompany";
    }

    @GetMapping("/logout-company")
    public ModelAndView logoutCompany(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.removeAttribute("company-account");
        modelAndView.setViewName("redirect:/login-company");
        return modelAndView;
    }
}
