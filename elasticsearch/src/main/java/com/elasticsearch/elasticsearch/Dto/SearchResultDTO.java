package com.elasticsearch.elasticsearch.Dto;

import com.elasticsearch.elasticsearch.elasticEntity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDTO {

    private List<UserElasticsearch> users;
    private List<ProfileElasticsearch> profiles;
    private List<EducationElasticsearch> educations;
    private List<ExperienceElasticsearch> experiences;
    private List<PostElasticsearch> posts;
    private List<JobPostingElasticsearch> jobPostings;
    private List<CompanyElasticsearch> companies;
    private List<InstitutionElasticsearch> institutions;


}
