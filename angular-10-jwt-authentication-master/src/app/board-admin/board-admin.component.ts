import { Component, OnInit } from '@angular/core';
import {  UserserviceService } from '../userservice.service'
import { Router } from '@angular/router';
import { Ng2SearchPipeModule } from 'ng2-search-filter';


@Component({
  selector: 'app-user',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {

  title = 'custom-search-filter-example';
  searchedKeyword: string;
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
        this.router.navigate(['/board-admin'])
        
      })
    }
  }  

}