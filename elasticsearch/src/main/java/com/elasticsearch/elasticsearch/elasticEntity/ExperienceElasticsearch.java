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
@Document(indexName = "experience_index")
public class ExperienceElasticsearch {
    @Id
    private Long experienceId;
    private String title;
    private String company;
    private String location;
    private String description;

    @Field(type = FieldType.Nested)
    private ProfileElasticsearch profile;


}
