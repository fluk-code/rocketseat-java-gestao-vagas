package ferreirafelipe.gestao_vagas.modules.company.repositories;

import java.util.UUID;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ferreirafelipe.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);

}
