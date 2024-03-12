package com.elasticsearch.elasticsearch.serviceImpl;

import com.elasticsearch.elasticsearch.Dto.SearchResultDTO;
import com.elasticsearch.elasticsearch.elasticEntity.*;
import com.elasticsearch.elasticsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {


    @Autowired
    ElasticsearchOperations elasticsearchOperations;


    @Override
    public SearchResultDTO searchAll(String query) {

        List<UserElasticsearch> users = searchUsers(query);
        List<ProfileElasticsearch> profiles = searchProfiles(query);
        List<ExperienceElasticsearch> experiences = searchExperiences(query);
        List<PostElasticsearch> posts = searchPosts(query);
        List<CompanyElasticsearch> companies = searchCompanies(query);
        List<InstitutionElasticsearch> institutions = searchInstitutions(query);
        List<EducationElasticsearch> educations = searchEducation(query);
        List<JobPostingElasticsearch> postings = searchPostings(query);

        return new SearchResultDTO(users,profiles,educations,experiences,posts,postings,companies,institutions);


    }

    @Override
    public List<UserElasticsearch> searchUsers(String query) {

        Criteria criteria = new Criteria("name").is(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<UserElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery,UserElasticsearch.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfileElasticsearch> searchProfiles(String query) {

        Criteria criteria = new Criteria("headline").is(query)
                .or("summary").is(query)
                .or("location").is(query)
                .or("industry").is(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<ProfileElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery, ProfileElasticsearch.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());



    }

    @Override
    public List<ExperienceElasticsearch> searchExperiences(String query) {

        Criteria criteria = new Criteria("title").is(query)
                .or("company").is(query)
                .or("location").is(query)
                .or("description").is(query);

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<ExperienceElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery, ExperienceElasticsearch.class);


        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostElasticsearch> searchPosts(String query) {
        Criteria criteria = new Criteria("content").is(query)
                .or("company.name").is(query)
                .or("institution.name").is(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<PostElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery, PostElasticsearch.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<CompanyElasticsearch> searchCompanies(String query) {
        Criteria criteria = new Criteria("name").is(query)
                .or("industry").is(query)
                .or("description").is(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<CompanyElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery, CompanyElasticsearch.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<InstitutionElasticsearch> searchInstitutions(String query) {
        Criteria criteria = new Criteria("name").is(query)
                .or("industry").is(query)
                .or("description").is(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<InstitutionElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery, InstitutionElasticsearch.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<JobPostingElasticsearch> searchPostings(String query) {

        Criteria criteria = new Criteria("title").is(query)
                .or("description").is(query)
                .or("location").is(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<JobPostingElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery, JobPostingElasticsearch.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());


    }

    @Override
    public List<EducationElasticsearch> searchEducation(String query) {
        Criteria criteria = new Criteria("school").is(query)
                .or("degree").is(query)
                .or("fieldOfStudy").is(query)
                .or("description").is(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<EducationElasticsearch> searchHits = elasticsearchOperations.search(criteriaQuery, EducationElasticsearch.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }
}
