package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.entity.Company;
import com.elasticsearch.elasticsearch.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.spi.LocaleNameProvider;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting,Long> {

    Optional<JobPosting> findByJobPostingId(Long jobPostingId);
    void deleteAllByCompany(Company company);

    void deleteByJobPostingIdAndCompany(Long jobPostingId, Company company);

    List<JobPosting> findAllByCompany(Company company);
}
