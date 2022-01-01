package io.github.mrspock182.neo4j.domain;

public class CreditStepDto {
    private final String name;
    private final String description;
    private final Double weight;

    public CreditStepDto(
            String name,
            String description,
            Double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
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
}
