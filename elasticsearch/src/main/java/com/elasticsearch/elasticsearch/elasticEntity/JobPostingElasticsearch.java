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
@Document(indexName = "posting_index")
public class JobPostingElasticsearch {
    @Id
    private Long jobPostingId;

    private String title;
    private String description;
    private String location;

    @Field(type = FieldType.Nested)
    private CompanyElasticsearch company;
}
