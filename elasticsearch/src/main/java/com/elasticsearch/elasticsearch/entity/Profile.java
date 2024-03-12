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
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private String headline; // Professional headline
    private String summary; // Professional summary
    private String location;
    private String industry;
    private String profileImageUrl; // URL to the profile picture

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL)
    private List<Education> educations;


    @ManyToMany
    private List<Skill> skills;

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", headline='" + headline + '\'' +
                ", summary='" + summary + '\'' +
                ", location='" + location + '\'' +
                ", industry='" + industry + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", skills=" + skills +
                '}';
    }
}
