package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.entity.Skill;
import com.elasticsearch.elasticsearch.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/create")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId) {
        skillService.deleteSkill(skillId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill updatedSkill) {
        Skill updatedSkillResult = skillService.updateSkill(updatedSkill);
        return new ResponseEntity<>(updatedSkillResult, HttpStatus.OK);
    }

    @GetMapping("/get/{skillId}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long skillId) {
        Skill skill = skillService.getSkillById(skillId);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    //adding skill to the profile
    @PutMapping("/{skillId}/add-skill/{profileId}")
    public Profile addSkillToProfile(@PathVariable("skillId") Long skillId,@PathVariable("profileId") Long profileId){
        return skillService.assignSkillToProfile(skillId,profileId);
    }
}
