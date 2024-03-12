package com.elasticsearch.elasticsearch.serviceImpl;

import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
import com.elasticsearch.elasticsearch.entity.Experience;
import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.repository.ExperienceRepository;
import com.elasticsearch.elasticsearch.repository.ProfileRepository;
import com.elasticsearch.elasticsearch.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ElasticsearchIndexer elasticsearchIndexer;
    @Override
    public Experience createExperience(Experience experience, Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            experience.setProfile(profile);

            Experience saveExperience = experienceRepository.save(experience);

            elasticsearchIndexer.indexExperience(saveExperience,profileId);






            // Save the experience
            return saveExperience;
        }

        return null;
    }

    @Override
    public void deleteExperience(Long experienceId) {
        experienceRepository.findById(experienceId).ifPresent(experience -> experienceRepository.delete(experience));

    }

    @Override
    public Experience updateExperience(Experience experience, Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            experience.setProfile(profile);

            // Save the updated experience
            return experienceRepository.save(experience);
        }

        return null;
    }

    @Override
    public Experience getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId).orElse(null);
    }
}
