package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.Dto.SearchResultDTO;
import com.elasticsearch.elasticsearch.elasticEntity.CompanyElasticsearch;
import com.elasticsearch.elasticsearch.elasticEntity.InstitutionElasticsearch;
import com.elasticsearch.elasticsearch.elasticEntity.ProfileElasticsearch;
import com.elasticsearch.elasticsearch.elasticEntity.UserElasticsearch;
import com.elasticsearch.elasticsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/users")
    public ResponseEntity<List<UserElasticsearch>> searchUserByName(@RequestParam(name = "name") String name){

        List<UserElasticsearch> users = searchService.searchUsers(name);

        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return  new ResponseEntity<>(users,HttpStatus.OK);
        }

    }

    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileElasticsearch>> searchUserByProfile(@RequestParam(name = "query") String query){

        List<ProfileElasticsearch> profiles = searchService.searchProfiles(query);

        if (profiles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return  new ResponseEntity<>(profiles,HttpStatus.OK);
        }

    }

    //company

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyElasticsearch>> searchUserByCompanyName(@RequestParam(name = "query") String query){

        List<CompanyElasticsearch> companies = searchService.searchCompanies(query);

        if (companies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return  new ResponseEntity<>(companies,HttpStatus.OK);
        }

    }

    //Institutions

    @GetMapping("/institutions")
    public ResponseEntity<List<InstitutionElasticsearch>> searchUserByInstitutionName(@RequestParam(name = "query") String query){

         List<InstitutionElasticsearch> institutions = searchService.searchInstitutions(query);

        if (institutions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return  new ResponseEntity<>(institutions,HttpStatus.OK);
        }

    }

    //search for ALl content
    @GetMapping("/All")
    public ResponseEntity<SearchResultDTO> searchAll(@RequestParam(name = "query") String query){

        SearchResultDTO results = searchService.searchAll(query);

        if (results==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return  new ResponseEntity<>(results,HttpStatus.OK);
        }

    }
}
