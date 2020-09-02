import { Component, OnInit } from '@angular/core';
import {  UserserviceService } from '../userservice.service'
import { Router } from '@angular/router';


@Component({
  selector: 'app-board-moderator',
  templateUrl: './board-moderator.component.html',
  styleUrls: ['./board-moderator.component.css']
})
export class BoardModeratorComponent implements OnInit {

  Employee: any = [];

  constructor(
    public restApi: UserserviceService, public router: Router
  ) { }

  ngOnInit() {
    //this.loadEmployees()
     this.restApi.getEmployees().subscribe((data: {}) => {
      this.Employee = data;
    })
  }

  // Get employees list
  loadEmployees() {
   
  }

  // Delete employee
  deleteEmployee(id) {
    if (window.confirm('Are you sure, you want to delete?')){
      this.restApi.deleteEmployee(id).subscribe(data => {
       // this.loadEmployees()
        this.router.navigate(['/borad-moderator'])
        
      })
    }
  }  

}