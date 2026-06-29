import { Component } from '@angular/core';
import { NgOptimizedImage } from "@angular/common";

@Component({
  selector: 'header-home',
  standalone: true,
  imports: [NgOptimizedImage],
  providers:[],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class HeaderHome {}
