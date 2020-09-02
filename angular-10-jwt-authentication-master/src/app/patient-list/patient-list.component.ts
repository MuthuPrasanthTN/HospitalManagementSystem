import { Component, OnInit } from '@angular/core';
import {  PatientserviceService } from '../patientservice.service'
import { Router } from '@angular/router';


@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
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
}