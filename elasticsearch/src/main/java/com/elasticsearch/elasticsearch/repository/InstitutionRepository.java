package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<Institution,Long> {

    Optional<Institution> findByInstitutionId(Long institutionId);




}
