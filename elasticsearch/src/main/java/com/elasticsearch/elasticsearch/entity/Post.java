package com.elasticsearch.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String content;
    private LocalDateTime postedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id")
    private Profile profile;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "institution_id")
    private Institution institution;

}
