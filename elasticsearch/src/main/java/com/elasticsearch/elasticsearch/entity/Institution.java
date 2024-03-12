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
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long institutionId;

    private String name;
    private String industry;
    private String  websiteURL;
    private String description;
    private String institutionImageUrl;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<Post> institutionPosts;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private User admin;

    @ManyToMany
    @JsonIgnore
    private List<User> followers;


}
