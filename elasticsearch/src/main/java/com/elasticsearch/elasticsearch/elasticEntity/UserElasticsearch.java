package com.elasticsearch.elasticsearch.elasticEntity;

import com.elasticsearch.elasticsearch.entity.Profile;
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
@Document(indexName = "user_index")
public class UserElasticsearch {
    @Id
    private Long userId;
    private String name;


}
