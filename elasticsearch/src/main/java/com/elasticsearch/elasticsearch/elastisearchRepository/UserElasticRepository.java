package com.elasticsearch.elasticsearch.elastisearchRepository;

import com.elasticsearch.elasticsearch.elasticEntity.UserElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserElasticRepository extends ElasticsearchRepository<UserElasticsearch,Long> {
}
