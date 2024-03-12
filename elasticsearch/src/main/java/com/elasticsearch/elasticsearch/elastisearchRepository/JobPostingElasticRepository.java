package com.elasticsearch.elasticsearch.elastisearchRepository;

import com.elasticsearch.elasticsearch.elasticEntity.JobPostingElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingElasticRepository extends ElasticsearchRepository<JobPostingElasticsearch,Long> {
}
