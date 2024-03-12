package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.entity.Company;
import com.elasticsearch.elasticsearch.entity.Institution;
import com.elasticsearch.elasticsearch.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByPostId(Long postId);

    List<Post> findAllByCompany(Company company);

    List<Post> findByInstitution(Institution institution);

}

