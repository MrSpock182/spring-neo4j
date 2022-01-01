package io.github.mrspock182.neo4j.parse.implementation;

import io.github.mrspock182.neo4j.domain.CreditStepDto;
import io.github.mrspock182.neo4j.parse.BaseParse;
import io.github.mrspock182.neo4j.repository.dto.CreditStepOrm;
import org.springframework.stereotype.Component;

@Component
public class CreditStepRequestParse implements BaseParse<CreditStepDto, CreditStepOrm> {

    @Override
    public CreditStepOrm cast(final CreditStepDto value) {
        return new CreditStepOrm(
                value.getName(),
                value.getDescription(),
                value.getWeight()
        );
    }
}
