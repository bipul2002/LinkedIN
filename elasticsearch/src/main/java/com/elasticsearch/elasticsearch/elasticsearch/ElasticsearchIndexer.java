package com.elasticsearch.elasticsearch.elasticsearch;

import com.elasticsearch.elasticsearch.elasticEntity.*;
import com.elasticsearch.elasticsearch.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchIndexer {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private ElasticsearchConvertor elasticsearchConvertor;

    public void indexUser(User user) {
        UserElasticsearch userElasticsearch = elasticsearchConvertor.convertToUserElasticsearch(user);
        IndexCoordinates indexCoordinates = IndexCoordinates.of("user_index");

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(userElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery, indexCoordinates);
    }


    //for profile

    public void indexProfile(Profile profile, Long userId) {
        UserElasticsearch userElasticsearch = new UserElasticsearch(userId, profile.getUser().getName());
        ProfileElasticsearch profileElasticsearch = elasticsearchConvertor.convertToProfileElasticsearch(profile, userElasticsearch);

        IndexCoordinates indexCoordinates = IndexCoordinates.of("profile_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(profileElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery, indexCoordinates);
    }


    //for company

    public  void indexCompany(Company company, Long userId){
        UserElasticsearch userElasticsearch = new UserElasticsearch(userId, company.getAdmin().getName());
        CompanyElasticsearch companyElasticsearch = elasticsearchConvertor.convertToCompanyElasticsearch(company,userElasticsearch);


        IndexCoordinates indexCoordinates = IndexCoordinates.of("company_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(companyElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);
    }


    // for jobPosting

    public  void indexJobPosting(JobPosting jobPosting,Long companyId){
        CompanyElasticsearch companyElasticsearch = new CompanyElasticsearch(companyId,jobPosting.getCompany().getName(),jobPosting.getCompany().getDescription(),jobPosting.getCompany().getIndustry());
        JobPostingElasticsearch jobPostingElasticsearch = elasticsearchConvertor.convertToJobPostingElasticsearch(jobPosting,companyElasticsearch);


        IndexCoordinates indexCoordinates = IndexCoordinates.of("posting_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(jobPostingElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);

    }

    //for education

    public  void  indexEducation(Education education,Long profileId){
        ProfileElasticsearch profileElasticsearch = new ProfileElasticsearch(education.getProfile().getProfileId(),education.getProfile().getIndustry(),education.getProfile().getLocation());
        EducationElasticsearch educationElasticsearch = elasticsearchConvertor.convertToEducationElasticsearch(education,profileElasticsearch);


        IndexCoordinates indexCoordinates = IndexCoordinates.of("education_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(educationElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);
    }

    //for experience
    public void indexExperience(Experience experience,Long profileId){
        ProfileElasticsearch profileElasticsearch = new ProfileElasticsearch(experience.getProfile().getProfileId(),experience.getProfile().getIndustry(),experience.getProfile().getLocation());
        ExperienceElasticsearch experienceElasticsearch = elasticsearchConvertor.convertToExperienceElasticsearch(experience,profileElasticsearch);

        IndexCoordinates indexCoordinates = IndexCoordinates.of("experience_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(experienceElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);


    }

    //for Institution

    public  void indexInstitution(Institution institution, Long userId){
        UserElasticsearch userElasticsearch = new UserElasticsearch(userId, institution.getAdmin().getName());
        InstitutionElasticsearch institutionElasticsearch = elasticsearchConvertor.convertToInstitutionElasticsearch(institution,userElasticsearch);


        IndexCoordinates indexCoordinates = IndexCoordinates.of("institution_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(institutionElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);
    }

   //for post in profile
    public void  indexPostInProfile(Post post, Long profileId){
        ProfileElasticsearch profileElasticsearch = new ProfileElasticsearch(profileId,post.getProfile().getLocation(),post.getProfile().getIndustry());
        PostElasticsearch postElasticsearch = elasticsearchConvertor.convertToPostElasticsearch(post,profileElasticsearch,null,null);


        IndexCoordinates indexCoordinates = IndexCoordinates.of("post_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(postElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);
    }

    //for post in company
    public void  indexPostInCompany(Post post, Long companyId){
        CompanyElasticsearch companyElasticsearch = new CompanyElasticsearch(companyId,post.getCompany().getName(),post.getCompany().getIndustry(),post.getCompany().getDescription());
        PostElasticsearch postElasticsearch = elasticsearchConvertor.convertToPostElasticsearch(post,null,companyElasticsearch,null);


        IndexCoordinates indexCoordinates = IndexCoordinates.of("post_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(postElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);
    }

    //for post in institution
    public void  indexPostInInstitution(Post post, Long institutionId){
        InstitutionElasticsearch institutionElasticsearch = new InstitutionElasticsearch(institutionId,post.getInstitution().getName(),post.getInstitution().getIndustry());
        PostElasticsearch postElasticsearch = elasticsearchConvertor.convertToPostElasticsearch(post,null,null,institutionElasticsearch);


        IndexCoordinates indexCoordinates = IndexCoordinates.of("post_index");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(postElasticsearch)
                .build();

        elasticsearchOperations.index(indexQuery,indexCoordinates);
    }







}
