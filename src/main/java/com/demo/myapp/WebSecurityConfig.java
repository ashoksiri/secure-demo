package com.demo.myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
//	        .csrf().disable()
				.authorizeHttpRequests((requests) -> {
					try {
						requests
//	            .requestMatchers().permitAll() // allow CORS option calls for Swagger UI
								.requestMatchers("/openapi/openapi.yml").permitAll().requestMatchers("/version").permitAll()
								.anyRequest().authenticated().and().oauth2ResourceServer().jwt().decoder(jwtDecoder());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				})
				.httpBasic();
		return http.build();
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)JwtDecoders.fromOidcIssuerLocation("https://login.microsoftonline.com/4c2c8480-d3f0-485b-b750-807ff693802f/v2.0");
		var tokenValidator = JwtValidators
				.createDefaultWithIssuer("https://login.microsoftonline.com/4c2c8480-d3f0-485b-b750-807ff693802f/v2.0");
		jwtDecoder.setJwtValidator(tokenValidator);
		return jwtDecoder;
	}

}
