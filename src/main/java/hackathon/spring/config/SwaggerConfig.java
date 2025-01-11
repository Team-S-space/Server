package hackathon.spring.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI UMCstudyAPI() {
        Info info = new Info()
                .title("해보러가자")
                .description("7th Hackathon S")
                .version("1.0.0");

        String jwtSchemeName = "JWT TOKEN";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("Bearer")
                        .bearerFormat("JWT"));
        // 로컬 + 배포 서버(여러 개 보고 싶은 경우)
        return new OpenAPI()
                .info(info)
                .servers(Arrays.asList(
                        new Server().url("http://3.38.222.221:8080").description("Develop server"),
                        new Server().url("http://localhost:8080").description("Local server")))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
