package ferreirafelipe.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ferreirafelipe.gestao_vagas.exceptions.UserFoundException;
import ferreirafelipe.gestao_vagas.modules.company.entities.CompanyEntity;
import ferreirafelipe.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/company")
@Tag(name = "Empresa", description = "Cadastro de empresas")
public class CompanyController {

  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok(result);
    } catch (UserFoundException err) {
      return ResponseEntity.badRequest().body(err.getMessage());
    } catch (Exception err) {
      return ResponseEntity.internalServerError().body(err.getMessage());
    }
  }

}
