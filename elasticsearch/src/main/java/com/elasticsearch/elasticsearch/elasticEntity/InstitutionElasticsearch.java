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
@Document(indexName = "institution_index")
public class InstitutionElasticsearch {

    @Id
    private Long institutionId;

    private String name;
    private String industry;
    private String  websiteURL;
    private String description;
    private String institutionImageUrl;


    public InstitutionElasticsearch(Long institutionId,String name,String industry) {
        this.institutionId = institutionId;
        this.name = name;
        this.industry = industry;
    }

    @Field(type = FieldType.Nested)
    private UserElasticsearch user;


}
