package Workspace.io.BaseDemo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Workspace.io.BaseDemo.Entities.User;
import Workspace.io.BaseDemo.Repositroy.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor

@Service
public class UserService {
	
	
	
	UserRepository userRepository;

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User save(User newUser) {
		// TODO Auto-generated method stub
		return userRepository.save(newUser);
	}

	public User getOneUser(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).orElse(null);
	}
	;
	
	public User updateUser(Long userId, User newUser) {
	
		Optional<User> user =userRepository.findById(userId);
		if (user.isPresent())
		{
		User foundUser=user.get();	
		foundUser.setUsername(newUser.getUsername());
		foundUser.setPassWord(newUser.getPassWord());
		userRepository.save(foundUser);
		return foundUser;
		}
	else 
		return null;
		
	
	}

	public void deleteById(Long userId) {
		
		userRepository.deleteById(userId);	
	}

	public User getOneUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(userName);
	}
	
	
	
}
