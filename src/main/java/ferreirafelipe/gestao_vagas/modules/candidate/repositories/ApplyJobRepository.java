
package ferreirafelipe.gestao_vagas.modules.candidate.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ferreirafelipe.gestao_vagas.modules.company.entities.JobEntity;

public interface ApplyJobRepository extends JpaRepository<JobEntity, UUID> {}