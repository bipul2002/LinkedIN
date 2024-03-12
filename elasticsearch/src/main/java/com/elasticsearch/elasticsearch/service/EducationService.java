package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Education;


public interface EducationService {

    Education createEducation(Education education, Long ProfileId);

    void deleteEducation(Long educationId);

    Education UpdateEducation(Education education ,Long profileId);

    Education getEducationById(Long educationId);
}
