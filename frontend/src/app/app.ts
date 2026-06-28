import { Component, SecurityContext, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}

interface user{
  name:string;
  email:string;
  role:'ROLE_USER'| 'ROLE_ADMIN';
}