import { Component } from '@angular/core';
import { HeaderHome } from "../../components/home-header/header";
import { NgOptimizedImage } from '@angular/common';
import { FooterHome } from "../../components/home-footer/footer";

@Component({
  selector: 'app-home-landing-page',
  imports: [HeaderHome, FooterHome, NgOptimizedImage],
  templateUrl: './home-landing-page.html',
  styleUrl: './home-landing-page.css',
})
export class HomeLandingPage {}
