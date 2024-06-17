package ferreirafelipe.gestao_vagas.modules.candidate.useCases;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import ferreirafelipe.gestao_vagas.exceptions.JobNotFoundException;
import ferreirafelipe.gestao_vagas.exceptions.UserNotFoundException;
import ferreirafelipe.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import ferreirafelipe.gestao_vagas.modules.candidate.entities.CandidateEntity;
import ferreirafelipe.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import ferreirafelipe.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import ferreirafelipe.gestao_vagas.modules.company.entities.JobEntity;
import ferreirafelipe.gestao_vagas.modules.company.repositories.JobRepository;
import lombok.experimental.var;

@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should NOT be able to apply job with candidate when candidate is NOT FOUND")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try{
          applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should NOT be able to apply job when Job is NOT FOUND")
    public void should_not_be_able_to_apply_job_when_job_not_found() {
        UUID idCandidate = UUID.randomUUID();
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of((candidateEntity)));

        try{
            applyJobCandidateUseCase.execute(idCandidate, null);
          } catch (Exception e) {
              assertThat(e).isInstanceOf(JobNotFoundException.class);
          }
    }

    @Test
    public void should_be_able_to_create_a_new_apply_job() {
        UUID idCandidate = UUID.randomUUID();
        UUID idJob = UUID.randomUUID();
        
        ApplyJobEntity applyJob = ApplyJobEntity.builder()
            .candidateId(idCandidate)
            .jobId(idJob)
            .build();
        applyJob.setId(UUID.randomUUID());


        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity());
        when(jobRepository.findById(idCandidate)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(Optional.of((ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .id(UUID.randomUUID())
        .build())));

        ApplyJobEntity result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrPropertiy("id");
        assertNotNull(result.getId());
 
    }
}