package ferreirafelipe.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ferreirafelipe.gestao_vagas.exceptions.UserFoundException;
import ferreirafelipe.gestao_vagas.modules.company.entities.CompanyEntity;
import ferreirafelipe.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository.findByUserNameOrEmail(companyEntity.getName(), companyEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    var passwordEncoded = passwordEncoder.encode(companyEntity.getPassword());
    companyEntity.setPassword(passwordEncoded);

    return this.companyRepository.save(companyEntity);
  }
}
