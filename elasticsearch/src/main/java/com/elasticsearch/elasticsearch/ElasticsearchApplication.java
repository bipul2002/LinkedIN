package com.elasticsearch.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages ="com.elasticsearch.elasticsearch.serviceImpl")
//@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.elasticsearch.elastisearchRepository.elasticsearch")
//@EnableJpaRepositories(basePackages = "com.elasticsearch.elasticsearch.repository.jpa")
public class ElasticsearchApplication{
    @Autowired
	private ApplicationContext applicationContext;
	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

}
