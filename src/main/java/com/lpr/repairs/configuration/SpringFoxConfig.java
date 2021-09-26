package com.lpr.repairs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
//        .apiInfo(apiInfo());
  }

//  private ApiInfo apiInfo() {
//    return new ApiInfoBuilder()
//        .title("")
//        .description("")
//        .license("License")
//        .licenseUrl("https://opensource.org/licenses/")
//        .build();
//  }
}