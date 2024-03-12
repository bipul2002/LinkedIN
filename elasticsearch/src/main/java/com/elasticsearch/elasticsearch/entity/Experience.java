    package com.elasticsearch.elasticsearch.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.hibernate.annotations.DynamicInsert;
    import org.hibernate.annotations.DynamicUpdate;

    import java.time.LocalDate;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @DynamicUpdate
    @DynamicInsert
    public class Experience {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long experienceId;
        private String title;
        private String company;
        private String location;
        private LocalDate startDate;
        private LocalDate endDate; // Can be null for ongoing experiences
        private String description;

        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "profile_id")
        private Profile profile;
    }
