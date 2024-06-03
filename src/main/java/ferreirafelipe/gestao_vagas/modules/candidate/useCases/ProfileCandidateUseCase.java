package ferreirafelipe.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ferreirafelipe.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import ferreirafelipe.gestao_vagas.modules.candidate.entities.CandidateEntity;
import ferreirafelipe.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class ProfileCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    CandidateEntity candidateEntity = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("User not found");
        });

    ProfileCandidateResponseDTO profileCandidateResponseDTO = ProfileCandidateResponseDTO
        .builder()
        .description(candidateEntity.getDescription())
        .email(candidateEntity.getEmail())
        .userName(candidateEntity.getEmail())
        .name(candidateEntity.getName())
        .id(candidateEntity.getId())
        .build();

    return profileCandidateResponseDTO;
  }
}
