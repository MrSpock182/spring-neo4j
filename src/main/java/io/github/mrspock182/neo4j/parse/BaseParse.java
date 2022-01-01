package io.github.mrspock182.neo4j.parse;

public interface BaseParse<I, O> {
    O cast(I value);
}
