package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Company;
import com.elasticsearch.elasticsearch.entity.JobPosting;
import com.elasticsearch.elasticsearch.entity.Post;


import java.util.List;

public interface CompanyService {

    Company createCompany(Company company);
    void deleteCompany(Long companyId);
    Company updateCompany(Company company);
    Company getCompanyById(Long companyId);
    List<Company> getAllCompanies();

    //posting related services

    JobPosting createJobPosting(Long companyId, JobPosting jobPosting);
    void deleteJobPosting(Long companyId, Long jobPostingId);
    List<JobPosting> getAllJobPostingsByCompanyId(Long companyId);

    //service related to the company post and operations
    Company createCompany(Company company, Long userId);
    void followCompany(Long companyId, Long userId);
    void unfollowCompany(Long companyId, Long userId);

    Post createPost(Long companyId, Post post);
    void deletePost(Long companyId, Long postId);
    List<Post> getAllPostsByCompanyId(Long companyId);


}
