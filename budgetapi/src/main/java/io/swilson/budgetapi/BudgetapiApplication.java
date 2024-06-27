package io.swilson.budgetapi;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.repo.CategoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class BudgetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetapiApplication.class, args);
	}

	// when we start the app, we will have some things save in the db:
	@Bean
	CommandLineRunner run(CategoryRepo categoryRepo) {
		return args -> {
			categoryRepo.save(new Category(null, "Groceries", 300));
			categoryRepo.save(new Category(null, "Bills", 1000));
			categoryRepo.save(new Category(null, "Extra", 200));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
				"Accept","Jwt-Token","Authorization","Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method","Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin","Content-Type","Accept","Jwt-Token",
				"Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials","Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
