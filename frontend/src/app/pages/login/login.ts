import { Component } from '@angular/core';
import { LoginHeader } from '../../components/login-header/login-header';
import { LoginFooter } from '../../components/login-footer/login-footer';
import { NgOptimizedImage } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [LoginHeader, LoginFooter, NgOptimizedImage],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {}
