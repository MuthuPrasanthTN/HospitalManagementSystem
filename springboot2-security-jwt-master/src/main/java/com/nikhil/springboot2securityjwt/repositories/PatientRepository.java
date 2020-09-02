package com.nikhil.springboot2securityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;



import com.nikhil.springboot2securityjwt.models.Patient;


public interface PatientRepository extends JpaRepository<Patient,Integer> 
{

}


