import { Component } from '@angular/core';
import { LoginHeader } from "../../components/login-header/login-header";
import { LoginFooter } from "../../components/login-footer/login-footer";

@Component({
  selector: 'app-register',
  imports: [LoginHeader, LoginFooter],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {}
