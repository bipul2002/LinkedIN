package com.elasticsearch.elasticsearch.elastisearchRepository;

import com.elasticsearch.elasticsearch.elasticEntity.PostElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostElasticRepository extends ElasticsearchRepository<PostElasticsearch,Long> {
}
