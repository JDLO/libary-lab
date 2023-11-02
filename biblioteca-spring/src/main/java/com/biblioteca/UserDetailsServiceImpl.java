package com.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.User;
import com.biblioteca.repositories.UserRepository;

import java.util.*;

/**
 * Nos permite gestionar la autenticación y el acceso.
 * 
 * @author Luis Creado para obtener al usuario de la base de datos
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("El usuario no existe");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), true, true, true,
				!user.isAccountLocked(), grantedAuthorities);
//		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), grantedAuthorities);
	}
}