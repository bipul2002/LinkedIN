package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.entity.Profile;

public interface PostService {

    Post  createPost(Post post, Long ProfileId);

    void deletePost(Long postId);

    Post UpdatePost(Post post ,Long profileId);

    Post getPostById(Long postId);
}
