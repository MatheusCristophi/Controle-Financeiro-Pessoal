import { Component } from '@angular/core';
import { LoginHeader } from '../../components/login-header/login-header';
import { LoginFooter } from '../../components/login-footer/login-footer';

@Component({
  selector: 'app-login',
  imports: [LoginHeader, LoginFooter],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {}
