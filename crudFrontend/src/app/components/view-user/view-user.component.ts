import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { NgxUiLoaderService } from "ngx-ui-loader";

@Component({
  selector: 'app-viewusers',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewusersComponent implements OnInit {

  employees: any[] | undefined     ///Employee[]
  url: string = "http://localhost:8080/";

  constructor(private service: AppService, private router: Router,
    private ngxService: NgxUiLoaderService) { 
   
  }

  showVal(): void
  {
    // this.service.getEmployee().subscribe(data => {
    //   this.employees = data;
    // })

    this.ngxService.start(); // start foreground spinner of the master loader with 'default' taskId
    // Stop the foreground loading after 5s
    this.service.getEmployee().subscribe(data => {
      if(data)
      {
        this.employees = data;
        this.ngxService.stop();
      }
      
    });
  }


  ngOnInit(): void {

    this.showVal();
  }

  deleteEmployee(id: number){
    this.service.deleteEmployee(id).subscribe(data => {
      this.employees = this.employees?.filter(employee => employee.id !== id);
    })
    
      setTimeout(()=>{
        window.location.reload();
      }, 100);
  
  }

  updateEmployee(id: number){
    this.router.navigate(['update', id]);
  }

}