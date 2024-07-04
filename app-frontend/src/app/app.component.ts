import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterOutlet } from '@angular/router';
import { Observable, catchError, map, of, startWith } from 'rxjs';
import { CategoryService } from './service/category.service';
import { AppState } from './interface/app-state';
import { CustomResponse } from './interface/custom-response';
import { DataState } from './enum/data-state.enum';
import { Category } from './interface/category';
import { Page } from './enum/page.enum';
import { HomePageComponent } from './pages/home/home-page.component';
import { CategoryPageComponent } from './pages/category/category-page.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HomePageComponent,
    CategoryPageComponent
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

  inCategory: Category = {id: 1, name: "sample", budget: 1500};

  constructor( 
    private http: HttpClient,
    private categoryService: CategoryService,
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

  openCatPage(cat: Category) {
    this.currentPage = Page.CATEGORY_PAGE;
    this.inCategory = cat;
  }
   
  }
