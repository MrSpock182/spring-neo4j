package io.github.mrspock182.neo4j.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node(value = "credit-step")
public class CreditStepOrm {
    @Id
    @GeneratedValue
    private Long id;
    private final String name;
    private final String description;
    private final Double weight;
    @JsonProperty("parameters")
    @Relationship(direction = OUTGOING)
    private Map<String, CreditStepOrm> teammates;
//    @Relationship(type = "TEAMMATE")
//    private Set<CreditStepOrm> teammates;

    public CreditStepOrm(
            String name,
            String description,
            Double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getWeight() {
        return weight;
    }

    public Map<String, CreditStepOrm> getTeammates() {
        return teammates;
    }

    public void setTeammates(Map<String, CreditStepOrm> teammates) {
        this.teammates = teammates;
    }

    public void worksWith(CreditStepOrm creditStep) {
        if (teammates == null) {
//            teammates = new HashSet<>();
            teammates = new HashMap<>();
        }
//        teammates.add(creditStep);
        teammates.put(UUID.randomUUID().toString(), creditStep);
    }

    public String toString() {
        return this.name + "'s teammates => " + Optional
                .ofNullable(this.teammates)
                .orElse(Collections.emptyMap())
                .values().stream()
                .map(CreditStepOrm::getName)
                .collect(Collectors.toList());
    }

}
