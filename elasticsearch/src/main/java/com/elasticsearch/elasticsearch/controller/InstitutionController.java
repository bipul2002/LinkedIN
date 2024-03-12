package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Institution;
import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.entity.User;
import com.elasticsearch.elasticsearch.repository.InstitutionRepository;
import com.elasticsearch.elasticsearch.repository.UserRepository;
import com.elasticsearch.elasticsearch.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Institution>  createInstitution(@RequestBody Institution institution, @PathVariable("userId") Long userId){
        Institution institution1 = institutionService.createInstitution(institution,userId);

        User admin = userRepository.findByUserId(userId).orElse(null);
        if(admin!=null){
            return  new ResponseEntity<>(institution1,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }
    @PostMapping("/{institutionId}/follow/{userId}")
    public  ResponseEntity<String> followInstitution(@PathVariable("institutionId") Long institutionId, @PathVariable("userId") Long userId){
        Institution institution = institutionRepository.findByInstitutionId(institutionId).orElse(null);
        User follower = userRepository.findByUserId(userId).orElse(null);
        if(institution!=null && follower!=null){
            institutionService.followInstitution(institutionId,userId);
            return new ResponseEntity<>("user successfully follow the institution",HttpStatus.OK);

        }

        return new ResponseEntity<>("either user or institutionId provided is not available",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{institutionId}/unfollow/{userId}")
    public  ResponseEntity<String> unfollowInstitution(@PathVariable("institutionId") Long institutionId, @PathVariable("userId") Long userId){
        institutionService.unfollowInstitution(institutionId,userId);
        return new ResponseEntity<>("user successfully unfollow the institution",HttpStatus.OK);
    }


    @PostMapping("/createPost/{institutionId}")
    public ResponseEntity<Post> createPost(@PathVariable("institutionId") Long institutionId, @RequestBody Post post){
        Institution institution = institutionRepository.findByInstitutionId(institutionId).orElse(null);
        if(institution!=null){
            institutionService.createPost(institutionId,post);
            return  new ResponseEntity<>(post,HttpStatus.CREATED);

        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/{institutionId}/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("institutionId") Long institutionId, @PathVariable("postId") Long postId) {
        institutionService.deletePost(institutionId, postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/{institutionId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByInstitutionId(@PathVariable("institutionId") Long institutionId) {
        List<Post> posts = institutionService.getAllPostsByInstitutionId(institutionId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}
