package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Company;
import com.elasticsearch.elasticsearch.entity.JobPosting;
import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("companyId") Long companyId) {
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company updatedCompany) {
        Company updatedCompanyResult = companyService.updateCompany(updatedCompany);
        return new ResponseEntity<>(updatedCompanyResult, HttpStatus.OK);
    }

    @GetMapping("/get/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("companyId") Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/{companyId}/jobPostings/create")
    public ResponseEntity<JobPosting> createJobPosting(
            @PathVariable("companyId") Long companyId,
            @RequestBody JobPosting jobPosting) {
        JobPosting createdJobPosting = companyService.createJobPosting(companyId, jobPosting);
        return new ResponseEntity<>(createdJobPosting, HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyId}/jobPostings/delete/{jobPostingId}")
    public ResponseEntity<Void> deleteJobPosting(
            @PathVariable("companyId") Long companyId,
            @PathVariable("jobPostingId") Long jobPostingId) {
        companyService.deleteJobPosting(companyId, jobPostingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{companyId}/jobPostings/getAll")
    public ResponseEntity<List<JobPosting>> getAllJobPostingsByCompanyId(@PathVariable("companyId") Long companyId) {
        List<JobPosting> jobPostings = companyService.getAllJobPostingsByCompanyId(companyId);
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }


    //crete a company through  the userId
    @PostMapping("/create/{adminId}")
    public ResponseEntity<Company> createCompany(@RequestBody Company company, @PathVariable("adminId") Long userId) {
        Company createdCompany = companyService.createCompany(company, userId);
        if (createdCompany != null) {
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{companyId}/follow/{userId}")
    public ResponseEntity<String> followCompany(@PathVariable("companyId") Long companyId, @PathVariable("userId") Long userId) {
        companyService.followCompany(companyId, userId);
        return new ResponseEntity<>("Successfully followed the company", HttpStatus.OK);
    }

    @PostMapping("/{companyId}/unfollow/{userId}")
    public ResponseEntity<String> unfollowCompany(@PathVariable("companyId") Long companyId, @PathVariable("userId") Long userId) {
        companyService.unfollowCompany(companyId, userId);
        return new ResponseEntity<>("Successfully unfollowed the company", HttpStatus.OK);
    }


    @PostMapping("/{companyId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable("companyId") Long companyId, @RequestBody Post post) {
        return new ResponseEntity<>(companyService.createPost(companyId, post), HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyId}/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("companyId") Long companyId, @PathVariable("postId") Long postId) {
        companyService.deletePost(companyId, postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/{companyId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByCompanyId(@PathVariable("companyId") Long companyId) {
        List<Post> posts = companyService.getAllPostsByCompanyId(companyId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }






}
