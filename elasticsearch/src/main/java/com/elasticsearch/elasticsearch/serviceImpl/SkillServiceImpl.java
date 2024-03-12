package com.elasticsearch.elasticsearch.serviceImpl;

import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.entity.Skill;
import com.elasticsearch.elasticsearch.repository.ProfileRepository;
import com.elasticsearch.elasticsearch.repository.SkillRepository;
import com.elasticsearch.elasticsearch.service.SkillService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void deleteSkill(Long skillId) {
        skillRepository.findById(skillId).ifPresent(skill -> skillRepository.delete(skill));

    }

    @Override
    public Skill updateSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Long skillId) {
        return skillRepository.findById(skillId).orElse(null);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
//    @Transactional
    public Profile assignSkillToProfile(Long skillId, Long profileId) {

        List<Skill> skills = null;
        Skill skill = skillRepository.findBySkillId(skillId).orElse(null);

        System.err.println(skill);

        Profile profile = profileRepository.findByProfileId(profileId).orElseThrow(()->new RuntimeException("Profile not found"));
        System.out.println(profile);
//        System.err.println(profile.getProfileId());

//        assert profile != null;
        skills = profile.getSkills();
        skills.add(skill);

        profile.setSkills(skills);

        profile= profileRepository.save(profile);
//        System.err.println(profile);

        profile = profileRepository.findByProfileId(profileId).orElseThrow(()->new RuntimeException("Profile not found"));
        System.out.println(profile);


        return profile;
        //
//        Skill skill = skillRepository.findById(skillId).orElse(null);
//        Profile profile = profileRepository.findById(profileId).orElse(null);
//
//        if (profile != null && skill != null) {
//            List<Skill> skills = profile.getSkills();
//            skills.add(skill);
//            profile.setSkills(skills);
//
//            // Save the profile after updating the skills list
//            return profileRepository.save(profile);
//        } else {
//            // Handle the case when either the profile or skill is not found
//            return null;
//        }
//        Skill skill = skillRepository.findById(skillId).orElse(null);
//        Profile profile = profileRepository.findById(profileId).orElse(null);
//
//        if (profile != null && skill != null) {
//            List<Skill> skills = profile.getSkills();
//            skills.add(skill);
//            profile.setSkills(skills);
//
//            // Add debug logs
//            System.out.println("Profile before save: " + profile);
//
//            // Save the profile after updating the skills list
//            profile = profileRepository.save(profile);
//
//            // Add debug logs
//            System.out.println("Profile after save: " + profile);
//            return profile;
//        } else {
//            // Handle the case when either the profile or skill is not found
//            return null;
//        }
    }
}
