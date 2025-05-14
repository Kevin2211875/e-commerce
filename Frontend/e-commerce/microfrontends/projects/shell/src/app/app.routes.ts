import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ProductComponent } from './product/product.component';


export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'navbar', component:NavbarComponent},
  { path: 'login', component:LoginComponent},
  { path: 'signup', component:SignUpComponent},
  { path: 'product/:id', component: ProductComponent }
];
