package ferreirafelipe.gestao_vagas.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ferreirafelipe.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import ferreirafelipe.gestao_vagas.modules.candidate.entities.CandidateEntity;
import ferreirafelipe.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import ferreirafelipe.gestao_vagas.modules.candidate.useCases.ListAllJobsByFIlterUserCase;
import ferreirafelipe.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import ferreirafelipe.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFIlterUserCase listAllJobsByFIlterUserCase;

  @PostMapping("/")
  @Operation(
    summary = "Cadastro de candidato", 
    description = "Essa função é responsavel cadastrar novos candidatos"
  )
  @ApiResponses({
    @ApiResponse(
      responseCode = "200",
      content = @Content(schema = @Schema(implementation = CandidateEntity.class))
    ),
    @ApiResponse(
      responseCode = "400",
      description = "Usuário ja cadastrado na base de dados"
    )}
  )
  public @Valid ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Perfil do candidato", 
    description = "Essa função é responsavel por buscar as informaçõe pessoais do candidato"
  )
  @ApiResponses({
    @ApiResponse(
      responseCode = "200",
      content = @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
    ),
    @ApiResponse(
      responseCode = "400",
      description = "User not found"
    )}
  )
  @SecurityRequirement(
    name = "JWT_Auth"
  )
  public ResponseEntity<Object> profile(HttpServletRequest request) {
    var candidateId = request.getAttribute("candidate_id");

    try {
      var profile = profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATE')")
   @Operation(summary = "Listagem de vagas disponiveis para o candidato", description = "Essa função é responsavel por lista todas vagas disponiveis baseada no filtro")
  @ApiResponses(
    @ApiResponse(
      responseCode = "200",
      content = {
        @Content(
          array = @ArraySchema(
            schema = @Schema(implementation = JobEntity.class)
          )
        )
      }
    )
  )
  @SecurityRequirement(
    name = "JWT_Auth"
  )
  public List<JobEntity> findJobsByFIlter(@RequestParam String filter) {
    return this.listAllJobsByFIlterUserCase.execute(filter);
  }
}
