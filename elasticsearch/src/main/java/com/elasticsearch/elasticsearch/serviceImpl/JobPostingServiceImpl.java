package com.elasticsearch.elasticsearch.serviceImpl;

import co.elastic.clients.elasticsearch.ml.Job;
import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
import com.elasticsearch.elasticsearch.entity.Company;
import com.elasticsearch.elasticsearch.entity.JobPosting;
import com.elasticsearch.elasticsearch.repository.CompanyRepository;
import com.elasticsearch.elasticsearch.repository.JobPostingRepository;
import com.elasticsearch.elasticsearch.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ElasticsearchIndexer elasticsearchIndexer;
    @Override
    public JobPosting createJobPosting(JobPosting jobPosting, Long companyId) {

        Company company = companyRepository.findByCompanyId(companyId).orElse(null);

        if(company!=null){
            jobPosting.setCompany(company);
            company.getJobPostings().add(jobPosting);
            JobPosting saveJobPosting = jobPostingRepository.save(jobPosting);
            elasticsearchIndexer.indexJobPosting(saveJobPosting,companyId);
        }
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public void deleteJobPosting(Long jobPostingId) {
        jobPostingRepository.findByJobPostingId(jobPostingId).ifPresent(jobPosting -> jobPostingRepository.delete(jobPosting));

    }

    @Override
    public JobPosting updateJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public JobPosting getJobPostingById(Long jobPostingId) {
        return jobPostingRepository.findByJobPostingId(jobPostingId).orElse(null);
    }

    @Override
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    @Override
    public List<JobPosting> getAllJobPostingsByCompanyId(Long companyId) {
        Company company = companyRepository.findByCompanyId(companyId).orElse(null);
        return jobPostingRepository.findAllByCompany(company);
    }

    @Override
    public JobPosting updateJobPosting(JobPosting jobPosting, Long jobPostingId,Long companyId) {

        Company company = companyRepository.findByCompanyId(companyId).orElse(null);
        JobPosting jobPosting1 = jobPostingRepository.findByJobPostingId(jobPostingId).orElse(null);
        if(company!=null && jobPosting1!=null){
            jobPosting1.setTitle(jobPosting.getTitle());
            jobPosting1.setDescription(jobPosting.getDescription());
            jobPosting1.setCompany(company);
           return  jobPostingRepository.save(jobPosting1);
        }




        return null;
    }
}
