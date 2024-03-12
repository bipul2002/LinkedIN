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
@Document(indexName = "profile_index")
public class ProfileElasticsearch {

    @Id
    private Long profileId;  // Use the same identifier as the JPA entity


    @Field(type = FieldType.Text)
    private String headline;

    @Field(type = FieldType.Text)
    private String summary;

    @Field(type = FieldType.Text)
    private String location;

    @Field(type = FieldType.Text)
    private String industry;

    @Field(type = FieldType.Text)
    private String profileImageUrl;

    @Field(type = FieldType.Nested)
    private UserElasticsearch user;

    public ProfileElasticsearch(Long profileId, String location, String industry) {
        this.profileId = profileId;
        this.location = location;
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "ProfileElasticsearch{" +
                "profileId=" + profileId +
                ", headline='" + headline + '\'' +
                ", summary='" + summary + '\'' +
                ", location='" + location + '\'' +
                ", industry='" + industry + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}
