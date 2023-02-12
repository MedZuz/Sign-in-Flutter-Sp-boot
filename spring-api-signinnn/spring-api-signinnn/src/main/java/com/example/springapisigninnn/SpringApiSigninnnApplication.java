package com.example.springapisigninnn;

import com.example.springapisigninnn.Model.Uzer;
import com.example.springapisigninnn.Repository.UzerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApiSigninnnApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiSigninnnApplication.class, args);
	}

		@Autowired
	UzerRepo uzerRepo ;

	@Override
	public void run(String... args) throws Exception {
		Uzer uz1 = new Uzer();
		uz1.setUsername("Syreun");
		uz1.setPassword("1234");
		uzerRepo.save(uz1);

	}
}
