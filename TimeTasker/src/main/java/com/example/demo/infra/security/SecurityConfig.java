package com.example.demo.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// Configuração do AuthenticationManager
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.inMemoryAuthentication().withUser("user")
				.password(passwordEncoder().encode("password")).roles("USER").and().withUser("admin")
				.password(passwordEncoder().encode("admin")).roles("ADMIN");

		return authenticationManagerBuilder.build();
	}

	// Configuração do UserDetailsService para gerenciamento de usuários em memória
	@Bean
	UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER").build(),
				User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build());
	}

	// Configuração do SecurityFilterChain para controlar as permissões e acesso
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/public/**").permitAll() // Libera o acesso a
																									// "/public/**"
				.anyRequest().authenticated()) // Requer autenticação para outras URLs
				.formLogin(login -> login.permitAll()); // Habilita o login padrão do Spring Security

		return http.build();
	}

	// Bean para o PasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
