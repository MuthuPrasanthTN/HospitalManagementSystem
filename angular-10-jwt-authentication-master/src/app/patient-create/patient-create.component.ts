
import { Component, OnInit, Input } from '@angular/core';
import {  PatientserviceService } from '../patientservice.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-create',
  templateUrl: './patient-create.component.html',
  styleUrls: ['./patient-create.component.css']
})
export class PatientCreateComponent implements OnInit {

  @Input() patientDetails = {dob:'', firstname:'', lastname:'', email:'', address:'', phone:'', sex:''}

  constructor(
    public restApi: PatientserviceService, 
    public router: Router
  ) { }

  ngOnInit() { }

  addPatient() {
    this.restApi.createPatient(this.patientDetails).subscribe((data: {}) => {
      this.router.navigate(['/user'])
    })
  }

}
