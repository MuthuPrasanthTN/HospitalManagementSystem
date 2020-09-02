package com.nikhil.springboot2securityjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.springboot2securityjwt.models.ERole;
import com.nikhil.springboot2securityjwt.models.Role;
import com.nikhil.springboot2securityjwt.models.User;
import com.nikhil.springboot2securityjwt.pojo.requests.SignupRequest;
import com.nikhil.springboot2securityjwt.pojo.response.MessageResponse;
import com.nikhil.springboot2securityjwt.repositories.RoleRepository;
import com.nikhil.springboot2securityjwt.repositories.UserRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UserRepository repo;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/users")
	@ResponseBody()
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getcustomer() {
		
		return repo.findAll();
	}
	
	@GetMapping("/users/{cid}")
	@ResponseBody()
	public  Optional<User> getcustomerById(@PathVariable("cid")Long id) {
		
		Optional<User> cust= repo.findById(id);
		return cust;
	}

	@PostMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser( @RequestBody SignupRequest request){
		if(repo.existsByUsername(request.getUsername())) {
			return  ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Username already exists!"));
		}
		
		if(repo.existsByEmail(request.getEmail())) {
			return  ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Email already in use!"));
		}
		
		// Create new user's account
		User user = new User(request.getUsername(), 
				request.getEmail(),encoder.encode(request.getPassword()),request.getFirstname(),request.getLastname(),
				request.getPhone(),true);
		
	
		
		Set<String> strRoles = request.getRole();
		Set<Role> roles = new HashSet<Role>();
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role not found!"));
			roles.add(userRole);
		}else {
			strRoles.forEach(role  -> {
				switch (role) {
				case "admin": 
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role not found!"));
					roles.add(adminRole);
					break;
					
				case "mod": 
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role not found!"));
					roles.add(modRole);
					break;

				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role not found!"));
					roles.add(userRole);
					break;
				}
			});
		}
		user.setRoles(roles);
		repo.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User Registered Successfully!"));
		
	}
	
	@PutMapping("/users/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	  public ResponseEntity<User> updateTutorial(@PathVariable("id") Long id, @RequestBody User user) {
	    Optional<User> userData = repo.findById(id);

	    if (userData.isPresent()) {
	    	User _user = userData.get();
	      
	    	_user.setUsername(user.getUsername());
	    	_user.setFirstname(user.getFirstname());
	    	_user.setLastname(user.getLastname());
	    	_user.setEmail(user.getEmail());
	    	_user.setPassword(user.getPassword());
	    	_user.setPhone(user.getPhone());
	    	_user.setActive(user.isActive());
	      return new ResponseEntity<>(repo.save(_user), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@DeleteMapping("/users/{cid}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deletecustomer(@PathVariable("cid")Long id) {
		
		User cust=repo.getOne(id);
		repo.delete(cust);
		return "deleted succesfully";
	}
	
}

