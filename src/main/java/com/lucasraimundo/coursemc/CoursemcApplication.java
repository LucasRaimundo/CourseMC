package com.lucasraimundo.coursemc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasraimundo.coursemc.services.S3Service;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		s3Service.uploadFile("C:\\Users\\lucas\\Desktop\\Photos\\Ilume\\test.jpg");
	}

}
