package Workspace.io.BaseDemo.WebSecurity.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Workspace.io.BaseDemo.Entities.User;
import Workspace.io.BaseDemo.Repositroy.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Service

public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);

		return JwtUserDetails.creater(user);
	}

	public org.springframework.security.core.userdetails.UserDetails loadUserById(Long id) {

		User user = userRepository.findById(id).get();
		return JwtUserDetails.creater(user);
	}

	








}
