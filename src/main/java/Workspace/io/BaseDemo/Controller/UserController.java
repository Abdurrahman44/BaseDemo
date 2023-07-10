package Workspace.io.BaseDemo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Workspace.io.BaseDemo.Entities.User;
import Workspace.io.BaseDemo.Services.UserService;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/getAll")
	public List<User> getAllProducts() {
	    return userService.getAllUsers();
	}	
	@PostMapping
	public User creaUser(@RequestBody User newUser) {
		
		return userService.save(newUser);
	}
	@GetMapping("/{UserId}")
	public User getOneUser(@PathVariable Long userId) {
		
		return userService.getOneUser(userId);
	}
	@PutMapping("/{UserId}")
	public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser) {
		
			return userService.updateUser(userId,newUser);
		
	}
	@DeleteMapping("/{UserId}")
	public void deletOneUser(@PathVariable Long userId) {
		
		userService.deleteById(userId);
	}
	

}
