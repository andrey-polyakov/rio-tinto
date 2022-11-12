package com.avinty.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class HrApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }


    private static final String HTTPS = "HTTPS";
    private static final String SERVICES_TITLE = "HR Services";
    private static final String CONTACT_PERSON = "HR Services support team";
    private static final String VERSION = "1.0.0";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("All")
                .protocols(Stream.of(HTTPS).collect(Collectors.toCollection(HashSet::new)))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathProvider(new DefaultPathProvider())
                .apiInfo(apiInfo());
    }

    private static ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(SERVICES_TITLE)
                .contact(new Contact(CONTACT_PERSON, "mailto: support@test.com", "support@test.com"))
                .version(VERSION)
                .description("HR services test application")
                .build();
    }
}
