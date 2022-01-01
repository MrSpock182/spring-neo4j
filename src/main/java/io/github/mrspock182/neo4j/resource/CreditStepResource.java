package io.github.mrspock182.neo4j.resource;

import io.github.mrspock182.neo4j.domain.CreditStepDto;
import io.github.mrspock182.neo4j.domain.NodeDto;
import io.github.mrspock182.neo4j.parse.BaseParse;
import io.github.mrspock182.neo4j.repository.CreditStepRepositoryWithNeo4j;
import io.github.mrspock182.neo4j.repository.dto.CreditStepOrm;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CreditStepResource {

    private final CreditStepRepositoryWithNeo4j repository;
    private final BaseParse<CreditStepDto, CreditStepOrm> parse;

    public CreditStepResource(
            CreditStepRepositoryWithNeo4j repository,
            BaseParse<CreditStepDto, CreditStepOrm> parse) {
        this.repository = repository;
        this.parse = parse;
    }

    @PostMapping("/save")
    public CreditStepOrm save(@RequestBody final CreditStepDto creditStep) {
        return repository.save(
                parse.cast(creditStep));
    }

    @PutMapping("/update")
    public CreditStepOrm update(@RequestBody final CreditStepDto creditStep) {
        Optional<CreditStepOrm> optional = repository.findByName(creditStep.getName());
        if (optional.isPresent()) {
            CreditStepOrm orm = optional.get();
            CreditStepOrm value = new CreditStepOrm(
                    creditStep.getName(),
                    creditStep.getDescription(),
                    creditStep.getWeight());
            value.setId(orm.getId());
            value.setTeammates(orm.getTeammates());
            return repository.save(value);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/add-node")
    public CreditStepOrm addNode(@RequestBody final NodeDto nodeDto) {
        Optional<CreditStepOrm> optional = repository.findByName(nodeDto.getNode());
        Optional<List<CreditStepOrm>> namesIn = repository.findByNameIn(nodeDto.getSubNode());
        if (optional.isPresent()) {
            CreditStepOrm creditStepOrm = optional.get();
            if (namesIn.isPresent()) {
                List<CreditStepOrm> listCredit = namesIn.get();
                listCredit.forEach(creditStepOrm::worksWith);
            }
            return repository.save(creditStepOrm);
        }
        return null;
    }

    @GetMapping("/find-all")
    public List<CreditStepOrm> findAll() {
        return repository.findAll();
    }

    @GetMapping("/find-node/{node}")
    public CreditStepOrm findNode(@PathVariable("node") final String node) {
        Optional<CreditStepOrm> optional = repository.findByName(node);
        return optional.orElse(null);
    }

}
