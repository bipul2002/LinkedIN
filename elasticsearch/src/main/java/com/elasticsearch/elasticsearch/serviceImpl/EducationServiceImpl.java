package com.elasticsearch.elasticsearch.serviceImpl;

import com.elasticsearch.elasticsearch.elasticEntity.EducationElasticsearch;
import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
import com.elasticsearch.elasticsearch.entity.Education;
import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.repository.EducationRepository;
import com.elasticsearch.elasticsearch.repository.ProfileRepository;
import com.elasticsearch.elasticsearch.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ElasticsearchIndexer elasticsearchIndexer;
    @Override
    public Education createEducation(Education education, Long ProfileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(ProfileId);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            education.setProfile(profile);

            Education saveEducation =  educationRepository.save(education);
            elasticsearchIndexer.indexEducation(saveEducation,ProfileId);


            // Save the education
            return saveEducation;
        }

        return null;
    }

    @Override
    public void deleteEducation(Long educationId) {
        educationRepository.findById(educationId).ifPresent(education -> educationRepository.delete(education));

    }

    @Override
    public Education UpdateEducation(Education education, Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            education.setProfile(profile);

            // Save the updated education
            return educationRepository.save(education);
        }

        return null;
    }

    @Override
    public Education getEducationById(Long educationId) {
        return educationRepository.findById(educationId).orElse(null);
    }
}
