import { Component } from '@angular/core';
import { RegisterHeader } from "../../components/register-header/register-header";
import { RegisterFooter } from "../../components/register-footer/register-footer";

@Component({
  selector: 'app-register',
  imports: [RegisterHeader, RegisterFooter],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {}
