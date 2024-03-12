package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Experience;
import com.elasticsearch.elasticsearch.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @PostMapping("/create/{profileId}")
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience,
                                                       @PathVariable Long profileId) {
        Experience createdExperience = experienceService.createExperience(experience, profileId);

        if (createdExperience != null) {
            return new ResponseEntity<>(createdExperience, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{experienceId}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long experienceId) {
        experienceService.deleteExperience(experienceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{profileId}")
    public ResponseEntity<Experience> updateExperience(@RequestBody Experience updatedExperience,
                                                       @PathVariable Long profileId) {
        Experience updatedExperienceResult = experienceService.updateExperience(updatedExperience, profileId);

        if (updatedExperienceResult != null) {
            return new ResponseEntity<>(updatedExperienceResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{experienceId}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long experienceId) {
        Experience experience = experienceService.getExperienceById(experienceId);

        if (experience != null) {
            return new ResponseEntity<>(experience, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
