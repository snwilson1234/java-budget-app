package io.swilson.budgetapi;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.model.Purchase;
import io.swilson.budgetapi.repo.CategoryRepo;
import io.swilson.budgetapi.repo.PurchaseRepo;
import io.swilson.budgetapi.service.implementation.CategoryServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BudgetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetapiApplication.class, args);
	}

	/*
		This allows us to run the application.
	*/

	@Bean
	CommandLineRunner run(CategoryRepo categoryRepo, PurchaseRepo purchaseRepo, CategoryServiceImpl categoryService) {
		return args -> {
			// Some sample database entries
			Category category = new Category(null, "sample", 1000, new ArrayList<>());
			Purchase purchase1 = new Purchase(null, "describe", 250, "2024-06-20", category);
			Purchase purchase2 = new Purchase(null, "egg", 300, "2024-06-21", category);
			categoryRepo.save(category);
			purchaseRepo.save(purchase1);
			purchaseRepo.save(purchase2);
		};
	}

	/*
		This configures a CORS Filter for the site to allow frontend clients to communicate with the backend api services,
		since they occur across two different ports.
	*/
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
