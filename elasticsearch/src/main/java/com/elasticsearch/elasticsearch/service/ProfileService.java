package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.elasticEntity.ProfileElasticsearch;
import com.elasticsearch.elasticsearch.elasticEntity.UserElasticsearch;
import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile getById(Long profileId);

    Profile createProfile(Profile profile, Long userId);

    Profile updateProfile(Profile profile);

    void deleteProfile(Long profileId);
    List<Post> getPostsByProfileId(Long profileId);

    void addSkillToProfile(Long profileId, Long skillId);

    void removeSkillFromProfile(Long profileId, Long skillId);


    //elasticserach Implementation
   // ProfileElasticsearch convertToProfileElasticsearch(Profile profile,UserElasticsearch userElasticsearch);
}
