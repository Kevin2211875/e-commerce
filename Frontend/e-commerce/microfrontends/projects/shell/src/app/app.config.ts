import { ApplicationConfig } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';

import { provideClientHydration } from '@angular/platform-browser';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { NavbarInferiorComponent } from './navbar-inferior/navbar-inferior.component';
import { ProductComponent } from './product/product.component';
import { provideHttpClient } from '@angular/common/http';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component:HomeComponent},
  { path: 'navbar', component:NavbarComponent},
  { path: 'navbarInferior', component:NavbarInferiorComponent},
  { path: 'login', component:LoginComponent},
  { path: 'product', component:ProductComponent }
];


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), 
    provideClientHydration(),
    provideHttpClient()
  ]
};
