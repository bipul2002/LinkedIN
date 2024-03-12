package com.elasticsearch.elasticsearch.serviceImpl;

import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.entity.Profile;
import com.elasticsearch.elasticsearch.repository.PostRepository;
import com.elasticsearch.elasticsearch.repository.ProfileRepository;
import com.elasticsearch.elasticsearch.service.PostService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ElasticsearchIndexer elasticsearchIndexer;

    @Override
    public Post createPost(Post post, Long profileId) {

        Optional<Profile> optionalProfile = profileRepository.findById(profileId);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            post.setProfile(profile);
            post.setPostedAt(LocalDateTime.now()); // Set the current timestamp


            Post savePost = postRepository.save(post);
            elasticsearchIndexer.indexPostInProfile(savePost,profileId);

            // Save the post
            return  savePost;
        }

        return  null;
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.findById(postId).ifPresent(post -> postRepository.delete(post));


    }

    @Override
    public Post UpdatePost(Post post, Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            post.setProfile(profile);
            return postRepository.save(post);
        }

        return null;
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
