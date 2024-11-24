package com.ssafy.trip.config;

import com.ssafy.trip.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
	
	private AuthorizationInterceptor authorizationInterceptor;

	public WebConfiguration(AuthorizationInterceptor authorizationInterceptor) {
		super();
		this.authorizationInterceptor = authorizationInterceptor;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry
			.addMapping("/**")
			.allowedOrigins("*")
//			.allowedOrigins("http://localhost:5173", "http://localhost:5174")
			.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
//			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
//			.allowCredentials(true)
//			.exposedHeaders("*")
			.maxAge(3600); // Pre-flight Caching
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("registered check");
		registry.addInterceptor(authorizationInterceptor)
				.excludePathPatterns(
						"/v3/api-docs/**",
						"/swagger-ui/**",
						"/swagger-resources/**",
						"/test1",
						"/member/login",
						"/member/join",
						"/member/idCheck",
						"/attraction/**",
						"/board/list"
				);
		//주의: 인터셉터에서 동일한 엔드포인트 url인데 HTTP METHOD가 다른경우 구분 불가능
	}
//	Swagger UI 실행시 404처리
//	Swagger2 일경우
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/assets/img/");
		registry.addResourceHandler("/*.html**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
