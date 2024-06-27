import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { CustomResponse } from '../interface/custom-response';
import { Category } from '../interface/category';


@Injectable({ providedIn: 'root'})
export class CategoryService {
    private readonly apiUrl = 'http://localhost:8080';

    // inject HttpClient into category service
    constructor(private http: HttpClient) { }

    categories$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/categories/`)
    .pipe(
        tap(console.log),
        catchError(this.handleError)
    );

    // save$ = (category: Category) => <Observable<CustomResponse>>
    // this.http.post<CustomResponse>(`${this.apiUrl}/category/save`, category)
    // .pipe(
    //     tap(console.log),
    //     catchError(this.handleError)
    // );

    handleError(handleError: any): Observable<never> {
        return throwError(() => new Error('Method not implemented.'))
    }

}