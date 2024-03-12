package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Education;
import com.elasticsearch.elasticsearch.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
public class EducationController  {
    @Autowired
    private EducationService educationService;

    @PostMapping("/create/{profileId}")
    public ResponseEntity<Education> createEducation(@RequestBody Education education,
                                                     @PathVariable("profileId") Long profileId) {
        Education createdEducation = educationService.createEducation(education, profileId);

        if (createdEducation != null) {
            return new ResponseEntity<>(createdEducation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{educationId}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long educationId) {
        educationService.deleteEducation(educationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{profileId}")
    public ResponseEntity<Education> updateEducation(@RequestBody Education updatedEducation,
                                                     @PathVariable Long profileId) {
        Education updatedEducationResult = educationService.UpdateEducation(updatedEducation, profileId);

        if (updatedEducationResult != null) {
            return new ResponseEntity<>(updatedEducationResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{educationId}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long educationId) {
        Education education = educationService.getEducationById(educationId);

        if (education != null) {
            return new ResponseEntity<>(education, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
