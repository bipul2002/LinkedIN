package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.entity.Groups;
import com.elasticsearch.elasticsearch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupsRepository extends JpaRepository<Groups,Long> {

    List<Groups> findByAdmin(User admin);

    List<Groups> findByMembers(User user);
}
