package com.example.labweektuan5_20008341_nguyengiahy.repositories;

import com.example.labweektuan5_20008341_nguyengiahy.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByUserUsernameAndUserPassword(String username, String password);
}
