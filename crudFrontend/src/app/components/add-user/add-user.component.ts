import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { Employee } from './../../Employee';

@Component({
  selector: 'app-adduser',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AdduserComponent implements OnInit {
  form!: FormGroup;
  data!: any;
  user!: Employee;
  enteredvalue = '';
  constructor(private service: AppService,
    private fb: FormBuilder,
     private router: Router) { }
  
  
  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      designation: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobileno: ['', [Validators.required, Validators.pattern("^((\\+88-?)|0)?[0-9]{10}$"), Validators.maxLength(11), Validators.minLength(10)]],
      // nid: ['', [Validators.required, Validators.pattern("(^[0-9]{10}$) | (^[0-9]{15}$) | (^[0-9]{17}$)")]]
      nid: ['', [Validators.required, Validators.pattern(/^(?:\01)?(?:\d{10}|\d{13}|\d{17})$/)]]
      // nid: ['']
    })
  }

  get mobileno() {
    return this.form.controls.mobileno;
    // return this.form.controls;
  }

  get email() {
    // return this.form.controls;
    return this.form.controls.email;
  }

  get nid() {
    // return this.form.controls;
    return this.form.controls.nid;
  }

  OnlyNumbers(event: { which: any; keyCode: any; }): boolean {
    const charCode = (event.which)? event.which : event.keyCode;

    if(charCode > 31 && (charCode<48 || charCode>57))
    {
      console.log('charCode ' + charCode + 'is restricted');
      return false;
    }

    return true;
  }

  re_route(): void {
    this.router.navigate(['/']);
  }
  
  submit(){
    this.data = this.form.value
    console.log(this.data)

    this.service.addEmployee(this.data).subscribe(data => {
      if(data){
        console.log("test    " + data);
        this.re_route();
      }
    })
    
  }
}
