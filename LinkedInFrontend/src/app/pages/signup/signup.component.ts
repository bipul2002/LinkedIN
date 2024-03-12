import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{

  constructor(private userService:UserService,private snak: MatSnackBar,private router:Router, private toastr:ToastrService){
    for (let age = 18; age <= 100; age++) {
      this.ageOptions.push(age);
    }
  };

  public user = {
    name : '',
    email : '',
    password: '',
    address: '',
    age:0,
    phone: ''
  };

  public ageOptions: number[] = [];





  ngOnInit(): void {
    
  }


  submitForm(){

    console.log(this.user);
    if(this.user.name=="" || this.user.name==null || 
    this.user.password==null || this.user.password=="" || this.user.email==null || this.user.email=="" || 
    this.user.address==""|| this.user.address==null|| this.user.phone==""|| this.user.phone==null ||
    this.user.age==0){

      this.snak.open('email Address is required','',{
        verticalPosition: 'bottom',
        horizontalPosition: 'right',
        duration:2000
      });
      return;
    
  }

  //calling the create method

  this.userService.addUser(this.user).subscribe(
    (data)=> {
      console.log(data);
      Swal.fire('Successsfully done!!','user is registered','success');
      this.clear();
      //navigate to the login page
      this.router.navigate(['/login'])
    },
    (error)=> {
      console.log(error);
      this.snak.open('some thing went wrong','',{
        duration:2000
      })
      
    }
  );

}


clear(){
  console.log('clear is working');

  this.user = {
    name : '',
    email : '',
    password: '',
    address: '',
    age:0,
    phone: ''
  };
  
}

}
