package ferreirafelipe.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ferreirafelipe.gestao_vagas.exceptions.UserFoundException;
import ferreirafelipe.gestao_vagas.modules.candidate.entities.CandidateEntity;
import ferreirafelipe.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {

    this.candidateRepository.findByUserNameOrEmail(candidateEntity.getUserName(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    var passwordEncoded = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(passwordEncoded);

    return this.candidateRepository.save(candidateEntity);
  }
}
