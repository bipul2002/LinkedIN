package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create/{profileId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long profileId) {
        Post createdPost = postService.createPost(post, profileId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{postId}/profile/{profileId}")
    public ResponseEntity<Post> updatePost(@RequestBody Post updatedPost,
                                           @PathVariable Long postId,
                                           @PathVariable Long profileId) {
        Post existingPost = postService.getPostById(postId);

        if (existingPost != null) {
            existingPost.setContent(updatedPost.getContent());


            // Update the post with the new information
            Post updatedPostResult = postService.UpdatePost(existingPost, profileId);
            return new ResponseEntity<>(updatedPostResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
