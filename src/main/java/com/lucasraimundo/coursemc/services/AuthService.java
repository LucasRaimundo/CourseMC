package com.lucasraimundo.coursemc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.repositories.ClientRepository;
import com.lucasraimundo.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		if(client == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}
		
		String newPass = newPassword();
		client.setPassword(pe.encode(newPass));
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0;i<10;i++) {
			vet[i] = ramdomChar();
		}
		return new String(vet);
		
	}

	private char ramdomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0 ) { //generate digit 
			return (char) (rand.nextInt(10) + 48);
		} else if(opt==1) { //generate uppercase letter
			return (char) (rand.nextInt(26) + 65);
		} else { // generate lowercase
			return (char) (rand.nextInt(26) + 97);
		}
	}
	
	
}
