package com.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class UserSwaggerConfig   {
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("user service").apiInfo(apiInfo()).select()
				.paths(regex("/api/v1/user.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("User Service")
				.description("Sample Documentation Generateed Using SWAGGER2 for our user service Rest API")
			.build();
	}
	
	
	 
}
