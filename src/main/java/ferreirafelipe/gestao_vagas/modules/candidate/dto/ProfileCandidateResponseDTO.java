package ferreirafelipe.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

  private UUID id;

  @Schema(
    example = "some@mail.com"
  )
  private String email;
  @Schema(
    example = "Some Name"
  )
  private String name;
  @Schema(
    example = "some"
  )
  private String userName;
  @Schema(
    example = "Programador Backend"
  )
  private String description;

}
