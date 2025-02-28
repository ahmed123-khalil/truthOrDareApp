package com.example.truthOrDare.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/", true).permitAll();
		
		httpSecurity.authorizeHttpRequests().requestMatchers("img/**", "css/**", "js/**", "/forgetPassword","/sendingConfirmationCodeEmail").permitAll();
		httpSecurity.authorizeHttpRequests().anyRequest().authenticated(); 
		return httpSecurity.build();
	}
}
