import { Employee } from './../../Employee';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-updateuser',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateuserComponent implements OnInit {

  // user?: Employee;
  user!: Employee;
  form!: FormGroup;
  data!: any;
  id!: any;


  constructor(private service: AppService, 
    private fb: FormBuilder,
    private route: ActivatedRoute, private router : Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getEmployeeById(this.id).subscribe(data => {
      this.user = data
      this.form.patchValue({
        name: this.user.name,
        designation: this.user.designation,
        email: this.user.email,
        mobileno: this.user.mobileno,
        nid: this.user.nid
      })
      console.log(this.user)
    })

    this.form = this.fb.group({
      id: this.id,
      name: ['', Validators.required],
      designation: ['', Validators.required],
      email: ['', Validators.required],
      mobileno: ['', Validators.required],
      nid: ['']
    })
  }

  // form = new FormGroup({
  //   name: new FormControl('', [Validators.required]),
  //   designation: new FormControl('', [Validators.required]),
  //   email: new FormControl('', [Validators.required]),
  //   mobileno: new FormControl('', [Validators.required]),
  //   nid: new FormControl('', [Validators.required])
  // })

  submit(){
    this.data = this.form.value
    console.log(this.data)
    
    this.service.updateEmployee(this.data).subscribe(data => {
      if(data)
      {
        console.log(data);
        this.router.navigate(['/']);
      }
      
    })

    ///this.router.navigate(['/']);
  }

}
