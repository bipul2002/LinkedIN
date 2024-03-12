package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.JobPosting;

import java.util.List;

public interface JobPostingService {
    JobPosting createJobPosting(JobPosting jobPosting, Long companyId);
    void deleteJobPosting(Long jobPostingId);
    JobPosting updateJobPosting(JobPosting jobPosting);
    JobPosting getJobPostingById(Long jobPostingId);
    List<JobPosting> getAllJobPostings();
    List<JobPosting> getAllJobPostingsByCompanyId(Long companyId);

    JobPosting updateJobPosting(JobPosting jobPosting,Long jobPostingId,Long companyId);
}
