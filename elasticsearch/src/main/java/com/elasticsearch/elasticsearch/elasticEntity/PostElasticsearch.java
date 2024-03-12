package com.elasticsearch.elasticsearch.elasticEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "post_index")
public class PostElasticsearch {
    @Id
    private Long postId;
    private String content;

    @Field(type = FieldType.Nested)
    private ProfileElasticsearch profile;

    @Field(type = FieldType.Nested)
    private CompanyElasticsearch company;

    @Field(type = FieldType.Nested)
    private InstitutionElasticsearch institution;
}
