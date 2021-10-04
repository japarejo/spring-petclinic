package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario=userRepo.findByUsername(username);
		if (usuario!=null) {
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			for(Authorities auth:usuario.getAuthorities())
				list.add(new SimpleGrantedAuthority(auth.getAuthority()));
			return new org.springframework.security.core.userdetails.User(usuario.getUsername(),usuario.getPassword(),list);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
