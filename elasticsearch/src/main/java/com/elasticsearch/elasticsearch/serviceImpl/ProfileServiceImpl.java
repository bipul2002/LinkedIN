package com.elasticsearch.elasticsearch.serviceImpl;


import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
import com.elasticsearch.elasticsearch.entity.*;
import com.elasticsearch.elasticsearch.repository.ProfileRepository;
import com.elasticsearch.elasticsearch.repository.SkillRepository;
import com.elasticsearch.elasticsearch.repository.UserRepository;
import com.elasticsearch.elasticsearch.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations ;

    @Autowired
    private ElasticsearchIndexer elasticsearchIndexer;


    @Override
    public Profile getById(Long profileId) {
        return profileRepository.findById(profileId).orElse(null);
    }

    @Override
    public Profile createProfile(Profile profile, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // User does not have a profile, create a new one
            profile.setUser(user);
            Profile savedProfile = profileRepository.save(profile);

            // Update the user with the new profile
            user.setProfile(savedProfile);
            userRepository.save(user);

            // Index the profile in Elasticsearch
            elasticsearchIndexer.indexProfile(savedProfile,userId);

            // Return the saved profile
            return savedProfile;
        }
        return null;
    }


    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();

            // Remove the profile reference in the associated user
            if (profile.getUser() != null) {
                profile.getUser().setProfile(null);
                userRepository.save(profile.getUser());
            }

            // Delete the profile
            profileRepository.deleteById(profileId);
        } else {
            System.out.println("Profile ID is not found");
        }
    }

    @Override
    public List<Post> getPostsByProfileId(Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            return profile.getPosts();
        }

        return Collections.emptyList();
    }

    @Override
    public void addSkillToProfile(Long profileId, Long skillId) {

        Profile profile = profileRepository.findById(profileId).orElse(null);
        Skill skill = skillRepository.findById(skillId).orElse(null);

        if (profile != null && skill != null) {
            profile.getSkills().add(skill);
            profileRepository.save(profile);
        }

    }

    @Override
    public void removeSkillFromProfile(Long profileId, Long skillId) {

        List<Profile> profiles = null;
        Skill skill = skillRepository.findById(skillId).orElse(null);
        Profile profile = profileRepository.findById(profileId).orElse(null);
        assert  skill !=null;
        profiles = skill.getProfiles();
        profiles.remove(profile);
        skillRepository.save(skill);
        skill.setProfiles(profiles);
        skillRepository.save(skill);
    }
}
