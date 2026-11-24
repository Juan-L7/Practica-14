package com.example.Pokemon.API.REST;

import com.example.Pokemon.API.REST.security.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokemonApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonApiRestApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistrationBean(JwtFilter jwtFilter){
		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(jwtFilter);
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

}
