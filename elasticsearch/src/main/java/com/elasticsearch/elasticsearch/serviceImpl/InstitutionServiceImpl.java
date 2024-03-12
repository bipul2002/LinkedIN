package com.elasticsearch.elasticsearch.serviceImpl;


import com.elasticsearch.elasticsearch.elasticsearch.ElasticsearchIndexer;
import com.elasticsearch.elasticsearch.entity.Institution;
import com.elasticsearch.elasticsearch.entity.Post;
import com.elasticsearch.elasticsearch.entity.User;
import com.elasticsearch.elasticsearch.repository.InstitutionRepository;
import com.elasticsearch.elasticsearch.repository.PostRepository;
import com.elasticsearch.elasticsearch.repository.UserRepository;
import com.elasticsearch.elasticsearch.service.InstitutionService;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ElasticsearchIndexer elasticsearchIndexer;
    @Override
    public Institution createInstitution(Institution institution, Long userId) {
        User admin = userRepository.findByUserId(userId).orElse(null);
        if(admin!=null){
            institution.setAdmin(admin);
            admin.getInstitutionAdmin().add(institution);

            Institution saveInstitution = institutionRepository.save(institution);

            elasticsearchIndexer.indexInstitution(saveInstitution,userId);


            return  saveInstitution;

        }
        return null;
    }

    @Override
    public void followInstitution(Long institutionId, Long userId) {
        List<User> followers = null;

        Institution institution = institutionRepository.findByInstitutionId(institutionId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);


        assert institution != null;
        followers = institution.getFollowers();
        followers.add(user);
        institution.setFollowers(followers);
        institutionRepository.save(institution);

    }

    @Override
    public void unfollowInstitution(Long institutionId, Long userId) {
        Institution institution = institutionRepository.findByInstitutionId(institutionId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        List<User> followers = null;
        assert institution!=null;
        followers = institution.getFollowers();
        followers.remove(user);
        institution.setFollowers(followers);
        institutionRepository.save(institution);

    }

    @Override
    public Post createPost(Long institutionId, Post post) {
        Optional<Institution> institution = institutionRepository.findByInstitutionId(institutionId);

        if (institution.isPresent()) {
            Institution institution1 = institution.get();
            post.setInstitution(institution1);
            post.setPostedAt(LocalDateTime.now()); // Set the current timestamp

            Post savePost = postRepository.save(post);

            elasticsearchIndexer.indexPostInInstitution(savePost,institutionId);

            // Save the post
            return savePost;
        }

        return  null;
    }

    @Override
    public void deletePost(Long institutionId, Long postId) {
        Institution institution = institutionRepository.findByInstitutionId(institutionId).orElse(null);
        Post post = postRepository.findByPostId(postId).orElse(null);
        if(institution!=null && post!=null){
            post.setInstitution(null);
            postRepository.delete(post);
        }else {
            throw  new ResourceNotFoundException("Institution or Post Id may not be present");
        }

    }

    @Override
    public List<Post> getAllPostsByInstitutionId(Long institutionId) {
        Institution institution = institutionRepository.findByInstitutionId(institutionId).orElseThrow(()-> new ResourceNotFoundException("Institution id"+ institutionId + " "+ "not found"));
        return postRepository.findByInstitution(institution);
    }
}
