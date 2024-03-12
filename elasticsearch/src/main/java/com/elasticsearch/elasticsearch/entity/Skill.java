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
@DynamicUpdate
@DynamicInsert
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    private String name;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore

//    @JoinTable(
//            name = "profile_skill",
//            joinColumns = @JoinColumn(name = "skill_id"),
//            inverseJoinColumns = @JoinColumn(name = "profile_id")
//    )
    private List<Profile> profiles;

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", name='" + name + '\'' +
                '}';
    }
}
