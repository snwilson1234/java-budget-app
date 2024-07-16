import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouterOutlet } from '@angular/router';
import { Category } from './interface/category';
import { Page } from './enum/page.enum';
import { HomePageComponent } from './pages/home/home-page.component';
import { CategoryPageComponent } from './pages/category/category-page.component';
import { MatButtonModule } from '@angular/material/button';
import { AppHeaderComponent } from './shared/header/app-header.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    AppHeaderComponent,
    HomePageComponent,
    CategoryPageComponent,
    MatButtonModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'app-frontend';
  readonly Page = Page;
  categories: Array<Category> = [];

  constructor( 
    private http: HttpClient,
  ) {}

  ngOnInit(): void {
    
  }
   
}
