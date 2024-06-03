package ferreirafelipe.gestao_vagas.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ferreirafelipe.gestao_vagas.modules.company.entities.JobEntity;
import ferreirafelipe.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFIlterUserCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String description) {
       return this.jobRepository.findByDescriptionContainingIgnoreCase(description);
    }
}