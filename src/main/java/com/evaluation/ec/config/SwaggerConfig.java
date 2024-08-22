package com.evaluation.ec.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.AllArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class SwaggerConfig {

    private static final String APP_DESCRIPTION = "This APIs allow manage the users register challenge using Spring Boot";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group(APP_DESCRIPTION)
                .pathsToMatch("/**")
                .pathsToExclude("/admin/**")
                .build();
    }

    @Bean
    public OpenAPI getOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Users APIs");
        contact.setEmail("fabriciofabara@gmail.com");
        return new OpenAPI()
                .info(new Info()
                        .title("Users APIs")
                        .description(APP_DESCRIPTION)
                        .version("0.0.1")
                        .contact(contact)
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}