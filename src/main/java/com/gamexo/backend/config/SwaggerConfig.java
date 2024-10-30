package com.gamexo.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "Gamexo API",
                description = """
                 Welcome to the Gamexo API documentation.
                        
                                This API allows administrators to manage games and licenses, while clients can browse and purchase games.
                        
                                **Important**:
                                - A prepopulated `ADMIN` user exists for testing purposes:
                                  - **Email**: admin@gamexo.com
                        
                                **Security**:
                                - JWT (JSON Web Token) is used for securing all protected endpoints.
                                - After logging in, the API will generate a JWT token, which must be included in the Authorization header as a Bearer token for accessing protected resources.
                                - The token expires after 1 hour, after which users must log in again
                        """,
                version = "1.0.0",
                termsOfService = "https://gamexo.com/terms",
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        servers = {
                @Server(
                        description = "Development Server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production Server",
                        url = "https://api.gamexo.com"
                )
        },
        security = @SecurityRequirement(
                name = "JWT Token"
        )
)
@SecurityScheme(
        name = "JWT Token",
        description = "JWT token for accessing the API. It expires in 1 hour, after which a new login is required.",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        paramName = HttpHeaders.AUTHORIZATION
)
public class SwaggerConfig {
}
