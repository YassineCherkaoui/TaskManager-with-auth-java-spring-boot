package com.github.julioevencio.apitask.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.julioevencio.apitask.services.TokenJwtService;
import com.github.julioevencio.apitask.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final TokenJwtService tokenJwtService;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Value("${cors.originPatterns}")
	private String corsOriginPatterns;

	public SecurityConfig(TokenJwtService tokenJwtService, UserDetailsServiceImpl userDetailsServiceImpl) {
		this.tokenJwtService = tokenJwtService;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
				.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins(corsOriginPatterns)
				.allowCredentials(true);
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(tokenJwtService, userDetailsServiceImpl);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests()
					.requestMatchers(HttpMethod.POST, "/api/auth/login", "/api/auth/register").permitAll()
					.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
					.requestMatchers(HttpMethod.GET, "/api/users/me").authenticated()
					.requestMatchers(HttpMethod.GET, "/api/users").hasAuthority("ROLE_ADMIN")
					.requestMatchers("/api/tasks/**").authenticated()
					.anyRequest().denyAll()
				.and()
				.cors()
				.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
	}

}
