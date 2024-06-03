package ferreirafelipe.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(
    example = "Felipe Ferreira",
    requiredMode = RequiredMode.REQUIRED)
  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo (userNamer) não deve conter espaço")
  @Schema(
    example = "ferreira.felipe",
    requiredMode = RequiredMode.REQUIRED
  )
  private String userName;

  @Schema(
    example = "ferreira@mai.com",
    requiredMode = RequiredMode.REQUIRED
  )
  @Email(message = "O campo (email) deve conter um email valido")
  private String email;

  @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
  @Schema(
    example = "S3cret@123",
    minLength = 10,
    maxLength = 100,
    requiredMode = RequiredMode.REQUIRED
  )
  private String password;

  @Schema(
    example = "Desenvolvedor Web Javascript/Typescript",
    requiredMode = RequiredMode.REQUIRED
  )
  private String description;

  @Schema(
    example = ""
  )
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
