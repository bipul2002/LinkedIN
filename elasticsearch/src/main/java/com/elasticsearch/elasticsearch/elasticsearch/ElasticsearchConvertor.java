package com.elasticsearch.elasticsearch.elasticsearch;

import com.elasticsearch.elasticsearch.elasticEntity.*;
import com.elasticsearch.elasticsearch.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchConvertor {

    public UserElasticsearch convertToUserElasticsearch(User user) {
        return new UserElasticsearch(
                user.getUserId(),
                user.getName()
        );
    }

    //profile converter

    public ProfileElasticsearch convertToProfileElasticsearch(Profile profile, UserElasticsearch userElasticsearch) {
        if (profile != null) {
            return new ProfileElasticsearch(
                    profile.getProfileId(),
                    profile.getHeadline(),
                    profile.getSummary(),
                    profile.getLocation(),
                    profile.getIndustry(),
                    profile.getProfileImageUrl(),
                    userElasticsearch
                    // Map other fields as needed
            );
        } else {
            return new ProfileElasticsearch();
        }
    }

    //company convertor

    public CompanyElasticsearch convertToCompanyElasticsearch(Company company,UserElasticsearch userElasticsearch){
        if(company!=null){
            return new CompanyElasticsearch(
                    company.getCompanyId(),
                    company.getName(),
                    company.getIndustry(),
                    company.getWebsiteURL(),
                    company.getDescription(),
                    company.getCompanyImageUrl(),
                    userElasticsearch
            );
        } else {
            return new CompanyElasticsearch();
        }
    }

    //jobPostingConvertor

    public JobPostingElasticsearch convertToJobPostingElasticsearch(JobPosting jobPosting,CompanyElasticsearch companyElasticsearch){
         if(jobPosting!=null){
             return new JobPostingElasticsearch(
                     jobPosting.getJobPostingId(),
                     jobPosting.getTitle(),
                     jobPosting.getDescription(),
                     jobPosting.getLocation(),
                     companyElasticsearch
             );
         } else {
             return new JobPostingElasticsearch();
         }
    }


    //Education convertor
    public EducationElasticsearch convertToEducationElasticsearch(Education education, ProfileElasticsearch profileElasticsearch){
        if(education!=null){
            return new EducationElasticsearch(
                    education.getEducationId(),
                    education.getSchool(),
                    education.getDegree(),
                    education.getFieldOfStudy(),
                    education.getDescription(),
                    profileElasticsearch
            );
        } else {
            return new EducationElasticsearch();
        }
    }

    //Experience convertor

    public ExperienceElasticsearch convertToExperienceElasticsearch(Experience experience, ProfileElasticsearch profileElasticsearch){
        if(experience!=null){
            return new ExperienceElasticsearch(
                    experience.getExperienceId(),
                    experience.getTitle(),
                    experience.getCompany(),
                    experience.getLocation(),
                    experience.getDescription(),
                    profileElasticsearch
            );
        } else {
            return new ExperienceElasticsearch();
        }
    }

    //Institution convertor

    public InstitutionElasticsearch convertToInstitutionElasticsearch(Institution institution,UserElasticsearch userElasticsearch){
        if(institution!=null){
            return new InstitutionElasticsearch(
                    institution.getInstitutionId(),
                    institution.getName(),
                    institution.getIndustry(),
                    institution.getWebsiteURL(),
                    institution.getDescription(),
                    institution.getInstitutionImageUrl(),
                    userElasticsearch
            );
        } else {
            return new InstitutionElasticsearch();
        }
    }


    public  PostElasticsearch convertToPostElasticsearch(Post post,ProfileElasticsearch profileElasticsearch,CompanyElasticsearch companyElasticsearch,InstitutionElasticsearch institutionElasticsearch){

        if(post!=null){
            return new PostElasticsearch(
                    post.getPostId(),
                    post.getContent(),
                    profileElasticsearch,
                    companyElasticsearch,
                    institutionElasticsearch

            );
        } else  {
            return new PostElasticsearch();
        }

    }




}
