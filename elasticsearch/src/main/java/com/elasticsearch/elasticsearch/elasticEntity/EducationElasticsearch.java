package com.elasticsearch.elasticsearch.elasticEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "education_index")
public class EducationElasticsearch {
    @Id
    private Long educationId;
    private String school;
    private String degree;
    private String fieldOfStudy;
    private String description;

    @Field(type = FieldType.Nested)
    private ProfileElasticsearch profile;

}
