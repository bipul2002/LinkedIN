import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';

const routes: Routes = [
  {
    path: 'signup',
    component: SignupComponent,
    pathMatch: 'full',
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path: 'profile/:name', // Dynamic route for the profile page with a parameter
    component: ProfileComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
