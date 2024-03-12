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
@Document(indexName = "company_index")
public class CompanyElasticsearch {
    @Id
    private Long companyId;

    private String name;
    private String industry;
    private String  websiteURL;
    private String description;
    private String companyImageUrl;


    public CompanyElasticsearch(Long companyId,String name, String industry, String description) {
        this.companyId = companyId;
        this.name = name;
        this.industry = industry;
        this.description = description;
    }

    @Field(type = FieldType.Nested)
    private UserElasticsearch user;
}
