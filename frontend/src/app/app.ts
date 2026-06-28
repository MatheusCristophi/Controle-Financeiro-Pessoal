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
  balance:bigint;
}

/*function showPerfil():void{
  perfil.innerHTML = `
  <h3>${name}</h3>
  `
}*/

let perfil = document.getElementById("perfil-user");
