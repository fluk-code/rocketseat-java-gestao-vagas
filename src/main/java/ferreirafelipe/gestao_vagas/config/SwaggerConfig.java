package ferreirafelipe.gestao_vagas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
            .info(
                new Info()
                    .title("Gestão de Vagas")
                    .description("API responsável pela gestão de vagas")
                    .version("1")
            )
            .schemaRequirement("JWT_Auth", createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
            .name("JWT_Auth")
            .scheme("bearer")
            .bearerFormat("JWT")
            .type(SecurityScheme.Type.HTTP)
            .in(SecurityScheme.In.HEADER);
    }
}
