import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouterOutlet } from '@angular/router';
import { Observable } from 'rxjs';
import { AppState } from './interface/app-state';
import { CustomResponse } from './interface/custom-response';
import { DataState } from './enum/data-state.enum';
import { Category } from './interface/category';
import { Page } from './enum/page.enum';
import { HomePageComponent } from './pages/home/home-page.component';
import { CategoryPageComponent } from './pages/category/category-page.component';
import { MatButtonModule } from '@angular/material/button';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faSackDollar } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HomePageComponent,
    CategoryPageComponent,
    MatButtonModule,
    FontAwesomeModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'app-frontend';
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  readonly Page = Page;
  categories: Array<Category> = [];
  currentPage: Page = Page.HOME_PAGE;

  faSackDollar = faSackDollar;

  inCategory: Category = {id: 1, name: "PLACEHOLDER", budget: 0, purchases: []};

  constructor( 
    private http: HttpClient,
  ) {}

  ngOnInit(): void {
    
  }

  switchContext() {
    if (this.currentPage == Page.HOME_PAGE) {
      this.currentPage = Page.CATEGORY_PAGE;
    }
    else {
      this.currentPage = Page.HOME_PAGE;
    }
  }

  handleCatPageEvent(cat: Category) {
    this.currentPage = Page.CATEGORY_PAGE;
    this.inCategory = cat;
  }
   
  }
