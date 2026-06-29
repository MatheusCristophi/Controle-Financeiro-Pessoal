import { Component } from '@angular/core';
import { HeaderHome } from "../header/header";
import { NgOptimizedImage } from '@angular/common';

@Component({
  selector: 'app-home-landing-page',
  imports: [HeaderHome],
  templateUrl: './home-landing-page.html',
  styleUrl: './home-landing-page.css',
})
export class HomeLandingPage {}
