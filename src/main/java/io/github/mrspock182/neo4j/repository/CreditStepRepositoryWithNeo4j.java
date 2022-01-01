package io.github.mrspock182.neo4j.repository;

import io.github.mrspock182.neo4j.repository.dto.CreditStepOrm;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditStepRepositoryWithNeo4j extends Neo4jRepository<CreditStepOrm, Long> {
    Optional<CreditStepOrm> findByName(String name);

    Optional<List<CreditStepOrm>> findByNameIn(List<String> names);

    Optional<List<CreditStepOrm>> findByTeammatesName(String name);
}
