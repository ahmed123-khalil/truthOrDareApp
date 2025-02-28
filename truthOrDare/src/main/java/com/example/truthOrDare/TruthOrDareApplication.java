package com.example.truthOrDare;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class TruthOrDareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruthOrDareApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
		PasswordEncoder passwordEncoder = passwordEncoder();
		return args -> {
			String username = "admin";
			if(!jdbcUserDetailsManager.userExists(username)) {
				jdbcUserDetailsManager.createUser(
							User.withUsername("admin").password(passwordEncoder.encode("Asmae.16/03")).roles("ADMIN").build()
						);
				System.out.println("user Created successfully");
			}else {
				System.out.println("user already exist");
			}
		};
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
