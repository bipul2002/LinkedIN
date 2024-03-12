package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.Dto.SearchResultDTO;
import com.elasticsearch.elasticsearch.controller.InstitutionController;
import com.elasticsearch.elasticsearch.elasticEntity.*;

import java.util.List;

public interface SearchService {

    SearchResultDTO searchAll(String query);

    List<UserElasticsearch> searchUsers(String query);

    List<ProfileElasticsearch> searchProfiles(String query);

    List<ExperienceElasticsearch> searchExperiences(String query);

    List<PostElasticsearch> searchPosts(String query);

    List<CompanyElasticsearch> searchCompanies(String query);

    List<InstitutionElasticsearch> searchInstitutions(String query);

    List<JobPostingElasticsearch> searchPostings(String query);

    List<EducationElasticsearch> searchEducation(String query);



}
