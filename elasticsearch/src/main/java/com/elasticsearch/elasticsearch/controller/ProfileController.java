package com.elasticsearch.elasticsearch.controller;


import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long profileId) {
        Profile profile = profileService.getById(profileId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, @PathVariable Long userId) {
        Profile createdProfile = profileService.createProfile(profile, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    @PutMapping("/update")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        Profile updatedProfile = profileService.updateProfile(profile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/delete/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long profileId) {
        try {
            profileService.deleteProfile(profileId);
            return new ResponseEntity<>("Profile deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting profile: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{profileId}/posts")
    public ResponseEntity<List<Post>> getPostsByProfileId(@PathVariable Long profileId) {
        List<Post> posts = profileService.getPostsByProfileId(profileId);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/add-skill/{profileId}/{skillId}")
    public ResponseEntity<Void> addSkillToProfile(@PathVariable Long profileId, @PathVariable Long skillId) {
        profileService.addSkillToProfile(profileId, skillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/remove-skill/{profileId}/{skillId}")
    public ResponseEntity<Void> removeSkillFromProfile(@PathVariable Long profileId, @PathVariable Long skillId) {
        profileService.removeSkillFromProfile(profileId, skillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
