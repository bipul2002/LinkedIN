package com.elasticsearch.elasticsearch.elastisearchRepository;

import com.elasticsearch.elasticsearch.elasticEntity.ExperienceElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceElasticRepository extends ElasticsearchRepository<ExperienceElasticsearch,Long> {
}
