package com.elasticsearch.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String name;
    private String industry;
    private String  websiteURL;
    private String description;
    private String companyImageUrl;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<JobPosting> jobPostings;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Post> companyPosts;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private User admin;

    @ManyToMany
    @JsonIgnore
    private List<User> followers;


}
