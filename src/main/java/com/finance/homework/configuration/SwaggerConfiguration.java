package com.finance.homework.configuration;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.finance.homework"))
                .paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return or(
                regex("/api/.*")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Loan application REST API")
                .description("4Finance hometask")
                .contact(new Contact("Nizami Islamovs", "", "nizamiislamovs@gmail.com"))
                .version("1.0").build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
    }
}
