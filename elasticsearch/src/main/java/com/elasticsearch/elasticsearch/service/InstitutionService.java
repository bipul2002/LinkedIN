package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Company;
import com.elasticsearch.elasticsearch.entity.Institution;
import com.elasticsearch.elasticsearch.entity.JobPosting;
import com.elasticsearch.elasticsearch.entity.Post;

import java.util.List;

public interface InstitutionService {

    //service related to the Institution post and operations
    Institution createInstitution(Institution institution, Long userId);
    void followInstitution(Long institutionId, Long userId);
    void unfollowInstitution(Long institutionId, Long userId);

    Post createPost(Long institutionId, Post post);
    void deletePost(Long institutionId, Long postId);
    List<Post> getAllPostsByInstitutionId(Long institutionId);

}
