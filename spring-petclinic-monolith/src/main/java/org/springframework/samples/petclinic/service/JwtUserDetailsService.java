package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService {
	@Autowired
	UserRepository userRepo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		org.springframework.samples.petclinic.model.User usuario=userRepo.findByUsername(username);
		if (usuario!=null) {
			List<GrantedAuthority> authorities=new ArrayList<>();
			for(Authorities authority:usuario.getAuthorities())
				authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
			return new User(usuario.getUsername(),usuario.getPassword(),authorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
