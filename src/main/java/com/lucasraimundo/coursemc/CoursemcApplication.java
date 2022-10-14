package com.lucasraimundo.coursemc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasraimundo.coursemc.domain.Category;
import com.lucasraimundo.coursemc.repositories.CategoryRepository;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner {
	
	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		
		
	}

}
