package com.example.labweektuan5_20008341_nguyengiahy.services;

import com.example.labweektuan5_20008341_nguyengiahy.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyRepository findByUsernameAndPassword(String username, String password){
        return (CompanyRepository) companyRepository.findByUserUsernameAndUserPassword(username,password);
    }
}
