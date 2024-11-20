package com.ssafy.trip.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("팀심심알고 EnjoyTrip API")
                .description("<h3> 관통프로젝트에서 사용하는 RestApi에 대한 문서를 제공한다.</h3>")
                .version("1.0.0");
    }

}