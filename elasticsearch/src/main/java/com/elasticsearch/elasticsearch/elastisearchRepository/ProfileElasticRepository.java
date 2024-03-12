package com.elasticsearch.elasticsearch.elastisearchRepository;

import com.elasticsearch.elasticsearch.elasticEntity.ProfileElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileElasticRepository extends ElasticsearchRepository<ProfileElasticsearch,Long> {
}
