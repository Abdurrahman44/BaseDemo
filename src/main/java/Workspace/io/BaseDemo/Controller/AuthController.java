package Workspace.io.BaseDemo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Workspace.io.BaseDemo.Entities.User;
import Workspace.io.BaseDemo.Request.UserRequest;
import Workspace.io.BaseDemo.Services.UserService;
import Workspace.io.BaseDemo.WebSecurity.Security.JwtTokenProvider;
@RestController
@RequestMapping("/auth")

public class AuthController {
	
	
	@Autowired	
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	
	private JwtTokenProvider jwtTokenProvider; 
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
		
	
	@PostMapping("/login")
	public String login (@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
		org.springframework.security.core.Authentication  auth =  authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken=jwtTokenProvider.generateJwtToken(auth);
		
		return "Bearer"+jwtToken;
		
		
	}
	@PostMapping("/register")
	public ResponseEntity<String> add(@RequestBody() UserRequest userRequest) {
		if (userService.getOneUserByUsername(userRequest.getUserName())!= null)
	   return new ResponseEntity<>("User alread creat ",HttpStatus.BAD_REQUEST);
		
		User user=new User();
		user.setUsername(userRequest.getUserName());
	     user.setPassWord(userRequest.getPassword());
	     user.setPassWord(passwordEncoder.encode(userRequest.getPassword()));
	 return new ResponseEntity<String>("User succesfully",HttpStatus.CREATED);
	}
}
