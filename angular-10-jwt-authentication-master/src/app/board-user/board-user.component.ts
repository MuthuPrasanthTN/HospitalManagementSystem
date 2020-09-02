import { Component, OnInit } from '@angular/core';
import {  PatientserviceService } from '../patientservice.service'
import { Router } from '@angular/router';


@Component({
  selector: 'app-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  title = 'custom-search-filter-example';
  searchedKeyword: string;
  Patient: any = [];

  constructor(
    public restApi: PatientserviceService, public router: Router
  ) { }

  ngOnInit() {
    //this.loadEmployees()
     this.restApi.getPatients().subscribe((data: {}) => {
      this.Patient = data;
    })
  }

  // Get employees list
  loadPatients() {
   
  }

  // Delete employee
  deletePatient(id) {
    if (window.confirm('Are you sure, you want to delete?')){
      this.restApi.deletePatient(id).subscribe(data => {
       // this.loadEmployees()
        this.router.navigate(['/user'])
        
      })
    }
  }  

}