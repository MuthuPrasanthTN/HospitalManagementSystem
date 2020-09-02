package com.nikhil.springboot2securityjwt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.springboot2securityjwt.models.Patient;
import com.nikhil.springboot2securityjwt.repositories.PatientRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientController {

		@Autowired
		PatientRepository repo;
		
		@GetMapping("/patients")
		@ResponseBody()
		public List<Patient> getcustomer() {
			
			return repo.findAll();
		}
		
		@GetMapping("/patients/{cid}")
		@ResponseBody()
		public  Optional<Patient> getcustomerById(@PathVariable("cid")int id) {
			
			Optional<Patient> cust= repo.findById(id);
			return cust;
		}

		@PostMapping("/patients")
		public Patient addcustomer(@RequestBody Patient customer) {
			
			repo.save(customer);
			return customer;
		}
		
		@PutMapping("/patients/{id}")
		  public ResponseEntity<Patient> updateTutorial(@PathVariable("id") int id, @RequestBody Patient user) {
		    Optional<Patient> userData = repo.findById(id);

		    if (userData.isPresent()) {
		    	Patient _user = userData.get();
		      
		    	_user.setFirstname(user.getFirstname());
		    	_user.setLastname(user.getLastname());
		    	_user.setEmail(user.getEmail());
		    	_user.setDob(user.getDob());
		    	_user.setPhone(user.getPhone());
		    	_user.setAddress(user.getAddress());
		    	_user.setSex(user.getSex());
		      return new ResponseEntity<>(repo.save(_user), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
		
		@DeleteMapping("/patients/{cid}")
		public String deletecustomer(@PathVariable("cid")int id) {
			
			Patient cust=repo.getOne(id);
			repo.delete(cust);
			return "deleted succesfully";
		}
		
	



}
