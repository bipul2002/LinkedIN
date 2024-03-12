import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import baseURL from './helper';
import { Route, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private router: Router) { }


  userData = new Subject<any>(); //Decalring new RxJs Subject

  sendUserData(data: string) {
    console.log('called');
    this.userData.next(data);
  }

  //geenrate token

  public generateToken(loginData: any) {
    return this.http.post(`${baseURL}/auth/login`, loginData);
  }

  public loginUser(token: any) {
    localStorage.setItem('token', token);
    return true;
  }


  public isLoggedIn() {
    let tokenstr = localStorage.getItem('token');
    if (tokenstr == undefined || tokenstr == '' || tokenstr == null) {
      return false;
    } else {
      return true;
    }
  }

  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  public getToken() {
    return localStorage.getItem('token');
  }

  public setUser(user: any) {
    console.log("user is " + user.name);
    localStorage.setItem('user', JSON.stringify(user));
    
  }

  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

  //current user

  public getCurrentUser(): Observable<any> {
    const token = localStorage.getItem('token');
    console.log(token);
    
    return this.http.get(`${baseURL}/users/current-user`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }






}
