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

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HomePageComponent
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

  constructor( 
    private http: HttpClient,
    private categoryService: CategoryService,
  ) {}

  ngOnInit(): void {
    this.appState$ = this.categoryService.categories$
    .pipe(
      map(response => {
        if (response['data']['categories']) {
          this.categories = response['data']['categories'];
        }
        this.categories = response['data']['categories'];
        return { dataState: DataState.LOADED_STATE, appData: response }
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) => {
        return of({ dataState: DataState.ERROR_STATE, error })
      })
    );
  }
   
  }
