package com.sochoeun.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Sochoeun",
                        email = "sochoeun@gmail.com",
                        url = "https://socheoun.com"
                ),
                title = "OpenApi Specification - Sochoeun",
                description = "OpenApi Documentation for Backend-API",
                version = "1.0",
                license = @License(
                        name = "License name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Term Of Service"
        ),
        servers = {
                @Server(
                        description = "Local DEV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD DEV",
                        url = "https://southearth.com"
                )
        }
)
public class OpenApiConfigure {

}
