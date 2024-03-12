package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.JobPosting;
import com.elasticsearch.elasticsearch.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobPostings")
public class JobPostingController {


    @Autowired
    private JobPostingService jobPostingService;

    @PostMapping("/create/{companyId}")
    public ResponseEntity<JobPosting> createJobPosting(@RequestBody JobPosting jobPosting,@PathVariable("companyId") Long companyId) {
        JobPosting createdJobPosting = jobPostingService.createJobPosting(jobPosting,companyId);
        return new ResponseEntity<>(createdJobPosting, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{jobPostingId}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable("jobPostingId") Long jobPostingId) {
        jobPostingService.deleteJobPosting(jobPostingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<JobPosting> updateJobPosting(@RequestBody JobPosting updatedJobPosting) {
        JobPosting updatedJobPostingResult = jobPostingService.updateJobPosting(updatedJobPosting);
        return new ResponseEntity<>(updatedJobPostingResult, HttpStatus.OK);
    }

    @GetMapping("/get/{jobPostingId}")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable("jobPostingId") Long jobPostingId) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(jobPostingId);
        return new ResponseEntity<>(jobPosting, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }

    @GetMapping("/getAllByCompanyId/{companyId}")
    public ResponseEntity<List<JobPosting>> getAllJobPostingsByCompanyId(@PathVariable("companyId") Long companyId) {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostingsByCompanyId(companyId);
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }

    @PutMapping("/update/{jobPostingId}/{companyId}")
    public ResponseEntity<JobPosting> updateJobPosting(@RequestBody JobPosting updatedJobPosting,
                                                       @PathVariable("jobPostingId") Long jobPostingId,
                                                       @PathVariable("companyId") Long companyId) {
        JobPosting updatedJobPostingResult = jobPostingService.updateJobPosting(updatedJobPosting, jobPostingId, companyId);

        if (updatedJobPostingResult != null) {
            return new ResponseEntity<>(updatedJobPostingResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // or handle differently based on your requirements
        }
    }
}
