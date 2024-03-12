package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Experience;

public interface ExperienceService {
    Experience createExperience(Experience experience, Long profileId);

    void deleteExperience(Long experienceId);

    Experience updateExperience(Experience experience, Long profileId);

    Experience getExperienceById(Long experienceId);
}
