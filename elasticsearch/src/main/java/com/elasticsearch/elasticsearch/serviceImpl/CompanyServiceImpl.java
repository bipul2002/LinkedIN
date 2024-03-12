package com.elasticsearch.elasticsearch.serviceImpl;

import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
import com.elasticsearch.elasticsearch.entity.*;
import com.elasticsearch.elasticsearch.repository.CompanyRepository;
import com.elasticsearch.elasticsearch.repository.JobPostingRepository;
import com.elasticsearch.elasticsearch.repository.PostRepository;
import com.elasticsearch.elasticsearch.repository.UserRepository;
import com.elasticsearch.elasticsearch.service.CompanyService;
import jakarta.transaction.Transactional;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobPostingRepository jobPostingRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ElasticsearchIndexer elasticsearchIndexer;
    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    public void deleteCompany(Long companyId) {
        companyRepository.findById(companyId).ifPresent(company -> {
            // Delete associated job postings before deleting the company
            jobPostingRepository.deleteAllByCompany(company);
            companyRepository.delete(company);
        });


    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findByCompanyId(companyId).orElse(null);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public JobPosting createJobPosting(Long companyId, JobPosting jobPosting) {
        companyRepository.findByCompanyId(companyId).ifPresent(jobPosting::setCompany);
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public void deleteJobPosting(Long companyId, Long jobPostingId) {
        companyRepository.findById(companyId).ifPresent(company -> {
            // Delete job posting only if it belongs to the specified company
            jobPostingRepository.deleteByJobPostingIdAndCompany(jobPostingId, company);
        });

    }

    @Override
    public List<JobPosting> getAllJobPostingsByCompanyId(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {
            return jobPostingRepository.findAllByCompany(company);
        }
        return null;
    }

    @Override
    public Company createCompany(Company company, Long userId) {

        User admin = userRepository.findByUserId(userId).orElse(null);
        if(admin!=null){
            company.setAdmin(admin);
            admin.getCompaniesAdmin().add(company);

            Company savedCompany = companyRepository.save(company);

            elasticsearchIndexer.indexCompany(savedCompany,userId);



            return savedCompany;

        }
        return null;
    }

    @Override
    public void followCompany(Long companyId, Long userId) {

        List<User> followers = null;

        Company company = companyRepository.findByCompanyId(companyId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);


        assert company != null;
        followers = company.getFollowers();
        followers.add(user);
        company.setFollowers(followers);
        companyRepository.save(company);


    }

    @Override
    public void unfollowCompany(Long companyId, Long userId) {
        Company company = companyRepository.findByCompanyId(companyId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        List<User> followers = null;
        assert company!=null;
        followers = company.getFollowers();
        followers.remove(user);
        company.setFollowers(followers);
        companyRepository.save(company);


    }

    @Override
    public Post createPost(Long companyId, Post post) {

        Optional<Company> company = companyRepository.findByCompanyId(companyId);

        if (company.isPresent()) {
            Company company1 = company.get();
            post.setCompany(company1);
            post.setPostedAt(LocalDateTime.now()); // Set the current timestamp

            Post savePost = postRepository.save(post);

            elasticsearchIndexer.indexPostInCompany(savePost,companyId);

            // Save the post
            return savePost;
        }

        return  null;
    }

    @Override
    public void deletePost(Long companyId, Long postId) {
        Company company = companyRepository.findByCompanyId(companyId).orElse(null);
        Post post = postRepository.findByPostId(postId).orElse(null);
        if(company!=null && post!=null){
            post.setCompany(null);
            postRepository.delete(post);
        }else {
            throw  new ResourceNotFoundException("company or Post Id may not be present");
        }

    }

    @Override
    public List<Post> getAllPostsByCompanyId(Long companyId) {

        Company company = companyRepository.findByCompanyId(companyId).orElseThrow(()-> new ResourceNotFoundException("company id"+ companyId + " "+ "not found"));
        return postRepository.findAllByCompany(company);
    }
}
