package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Groups;
import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.entity.Skill;
import com.elasticsearch.elasticsearch.entity.User;

import java.util.List;

public interface SkillService {
    Skill createSkill(Skill skill);

    void deleteSkill(Long skillId);

    Skill updateSkill(Skill skill);

    Skill getSkillById(Long skillId);

    List<Skill> getAllSkills();

    Profile assignSkillToProfile(Long skillId, Long profileId);


}
