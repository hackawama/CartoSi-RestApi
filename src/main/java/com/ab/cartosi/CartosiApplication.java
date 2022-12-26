package com.ab.cartosi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ab.cartosi.entities.Role;
import com.ab.cartosi.service.AccountService;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class CartosiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartosiApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService aService) {
		return args -> {
			aService.intialStrucutres();
			Role aRole = aService.loadRoleByName("ADMIN");
			if (aRole == null) {
				System.out.println("___________CREATION ADMIN ROLE __________");
				Role admin = new Role();
				admin.setRole("ADMIN");
				aService.addNewRole(admin);

			}

			Role uRole = aService.loadRoleByName("USER");
			if (uRole == null) {
				System.out.println("___________CREATION ORPSIC ROLE __________");
				Role user = new Role();
				user.setRole("USER");
				aService.addNewRole(user);
			}
			System.out.println(System.getProperty("user.dir"));
		};

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
