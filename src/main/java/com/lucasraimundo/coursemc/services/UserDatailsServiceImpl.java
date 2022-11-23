package com.lucasraimundo.coursemc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.repositories.ClientRepository;
import com.lucasraimundo.coursemc.security.UserSS;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client cli = repo.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getPassword(), cli.getProfiles());
	}

}
