package io.github.mrspock182.neo4j.domain;

import java.util.List;

public class NodeDto {
    private final String node;
    private final List<String> subNode;

    public NodeDto(String node, List<String> subNode) {
        this.node = node;
        this.subNode = subNode;
    }

    public String getNode() {
        return node;
    }

    public List<String> getSubNode() {
        return subNode;
    }
}
