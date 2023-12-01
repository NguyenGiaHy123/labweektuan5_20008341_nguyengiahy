package com.example.labweektuan5_20008341_nguyengiahy.services;

import com.example.labweektuan5_20008341_nguyengiahy.models.Company;
import com.example.labweektuan5_20008341_nguyengiahy.models.Job;
import com.example.labweektuan5_20008341_nguyengiahy.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Page<Job> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findAll(pageable);
    }

    public Page<Job> findProposedJobsPaginated(int pageNo, int pageSize, long candidateID) {
        int startItem = pageNo * pageSize;
        List<Job> list;
        List<Job> jobs = jobRepository.findProposedJobs(candidateID);

        if (jobs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, jobs.size());
            list = jobs.subList(startItem, toIndex);
        }

        Page<Job> jobPage
                = new PageImpl<>(list, PageRequest.of(pageNo, pageSize), jobs.size());

        return jobPage;
    }

    public Page<Job> findJobsByCompanyPaginated(int pageNo, int pageSize, Company company) {
        int startItem = pageNo * pageSize;
        List<Job> list;
        List<Job> jobs = jobRepository.findJobsByCompany(company);

        if (jobs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, jobs.size());
            list = jobs.subList(startItem, toIndex);
        }

        Page<Job> jobPage
                = new PageImpl<>(list, PageRequest.of(pageNo, pageSize), jobs.size());

        return jobPage;
    }
}
