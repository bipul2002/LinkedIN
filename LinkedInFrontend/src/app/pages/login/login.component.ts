import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData = {
    email: '',
    password: '',
  };
  constructor(
    private snack: MatSnackBar,
    private _login: LoginService,
    private router: Router
  ) {}

  submitForm(){
    //validation

    if (this.loginData.email.trim() == '' || this.loginData.email == null) {
      this.snack.open('email is required!!', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.loginData.password.trim() == '' ||
      this.loginData.password == null
    ) {
      this.snack.open('password is required!!', '', {
        duration: 3000,
      });
      return;
    }

      //request server to generate token
      this._login.generateToken(this.loginData).subscribe(
        (data:any)=> {
          console.log('success');
          console.log(data);

          //login
          this._login.loginUser(data.jwtToken);
          console.log('login');

          this._login.getCurrentUser().subscribe(
            (user:any)=> {
              this._login.setUser(user);
              console.log(user);

              this.router.navigate(['/profile',user.name]);
              
            }
          );
        },
        (error)=> {
          console.log('error');
          this.snack.open('Invalid Details !! Try again', '', {
            duration: 3000,
          });
        }
      );
  }
  //clear function
  clear() {
    console.log('clear is working');

    this.loginData = {
      email: '',
      password: '',
    };
  }



  

}
